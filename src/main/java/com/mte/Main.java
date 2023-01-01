package com.mte;

import com.jcraft.jsch.JSch;

public class Main {
    public static void main(String[] args) throws Exception {
        Collector collector = new Collector("127.0.0.1",22,"ubuntu","ubuntu!");
        System.out.println(collector.execCommand("hostname"));
        System.out.println(collector.execCommand("whoami"));
        System.out.println(collector.execCommand("lscpu"));
        collector.close();
    }
}
