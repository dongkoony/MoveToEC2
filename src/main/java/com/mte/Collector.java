package com.mte;

import com.jcraft.jsch.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Collector {
    private JSch client;
    private Session session;
    private Channel channel;

    private OutputStream os;
    private InputStream ins;

    public Collector(String ipaddress,int port,String user,String password) throws Exception {
        client = new JSch();
        Properties config = new Properties();
        config.put("StrictHostKeyChecking","no");

        session = client.getSession(user,ipaddress,port);
        if(null != session){
            session.setConfig(config);
            session.setPassword(password);
            session.connect();

            System.out.println("=============="+ipaddress+" Session connected==============");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        channel = session.openChannel("shell");
        if(null != channel){
            os = channel.getOutputStream();
            ins = channel.getInputStream();

            channel.connect();

            System.out.println("=============="+ipaddress+" Channel connected==============");
        }

    }

    public void close() throws Exception{
        if(null != ins){
            ins.close();
        }
        if(null != os){
            os.close();
        }
    }

    private String exec(String command) throws Exception {
        String result = null;
        int retry = 0;

        StringBuffer script = new StringBuffer();
        script.append(command);

        if(!command.endsWith("\n")) {
            script.append("\n");
        }
        os.write(script.toString().getBytes());
        os.flush();

        do{
            if (retry++ > 10){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(null == result || result.isEmpty() );

        return result;
    }
}
