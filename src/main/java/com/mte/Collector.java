package com.mte;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Properties;

public class Collector {
    private JSch client;
    private Session session;

    private PrintStream stream;

    private String prompt;
    private ChannelShell channelShell;
    private ByteArrayOutputStream outputStream;

    public Collector(String ipaddress,int port,String user,String password) throws Exception {
        client = new JSch();
        Properties config = new Properties();
        config.put("StrictHostKeyChecking","no");

        session = client.getSession(user,ipaddress,port);
        if(null != session){
            session.setConfig(config);
            session.setPassword(password);
            session.connect();
        }

        outputStream = new ByteArrayOutputStream();

        channelShell = (ChannelShell) session.openChannel("shell");
        if(null != channelShell){
            channelShell.setOutputStream(outputStream);
            stream = new PrintStream(channelShell.getOutputStream());
            channelShell.connect();
        }
    }
    public void close() throws Exception{
        if(null != stream){
            stream.close();
        }
        if(null != channelShell){
            channelShell.disconnect();
        }
        if(null != session){
            session.disconnect();
        }
    }


    private String exec(String command) throws Exception {
        String result = null;
        stream.print(command+"\n");
        stream.flush();
        result = getResponse(outputStream);
        return result;
    }

    private String getResponse(ByteArrayOutputStream outputStream) throws InterruptedException {
        int retry = 5;
        String result="";
        for (int i = 1 ; i<retry; i++){
            Thread.sleep(10);
            result=outputStream.toString();
            outputStream.reset();
            return result;
        }
        return result;
    }

    public String execCommand(String command) throws Exception{
        String result = exec(command);
        result = result.substring(command.length(),result.lastIndexOf("\r\n"));
        return result;
    }
}
