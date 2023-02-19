package edu.itstep.runapp.networking.servers;

import org.apache.log4j.Logger;

public class CreateServerSocket {
    static Logger logger = Logger.getLogger(CreateServerSocket.class);
    public static void main(String...arg){
        logger.debug(Thread.currentThread().getName() + " was started");
        String tcp = "tcp time server";
        GetServerFactory gsf = new GetServerFactory();
        Server tcpTime = gsf.getServerInstance(tcp);
        Thread tcpThread = new Thread(tcpTime, "TCP-TIME THREAD");
        tcpThread.start();
        Server udp = gsf.getServerInstance("udp");
        Thread udpThread = new Thread(udp,"UDP-THREAD");
        udpThread.start();
        //Server asyncSocket = gsf.getServerInstance("async");
    }
}
