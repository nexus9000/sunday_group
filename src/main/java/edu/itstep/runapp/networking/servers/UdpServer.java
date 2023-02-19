package edu.itstep.runapp.networking.servers;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer extends Server {
    private final static int PORT_NUMBER = 123;
    static Logger logger = Logger.getLogger(UdpServer.class);

    @Override
    int getPort() {
        return PORT_NUMBER;
    }

    @Override
    String getTypeServer() {
        return null;
    }

    @Override
    void startServer() throws IOException {
        try (DatagramSocket socket = new DatagramSocket(PORT_NUMBER)) {
            logger.debug("UDP Server is running on port " + socket.getPort());
            byte[] buffer = new byte[400];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);
            String client = new String(buffer,0, request.getLength());
            logger.debug(client);
            InetAddress clientIpAddr = request.getAddress();
            logger.debug(clientIpAddr);
            int clientSrcPort = request.getPort();
            String dataServer = "msg by UDP Server";
            DatagramPacket response = new DatagramPacket(dataServer.getBytes(),
                   dataServer.getBytes().length, clientIpAddr, clientSrcPort);
            socket.send(response);
        }

    }

    @Override
    void stopServer() throws IOException {

    }

    @Override
    public void run() {
         try{
             logger.debug(Thread.currentThread().getName() + " was started!");
             startServer();
         }catch(IOException io){
             logger.error(io.getMessage());
         }
    }
}
