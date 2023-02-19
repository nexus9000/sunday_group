package edu.itstep.runapp.networking.clients;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    private static final String HOSTNAME_ADDR= "localhost";
    private static final int PORT_NUMBER = 123;
    static final Logger logger = Logger.getLogger(UdpClient.class);
    public  static void main(String...arg){
        try(DatagramSocket ds = new DatagramSocket()){
            InetAddress inetAddress = InetAddress.getByName(HOSTNAME_ADDR);
            byte[] buffer = new byte[400];
            String hello = "hello by client";
            DatagramPacket dp = new DatagramPacket(hello.getBytes(), hello.getBytes().length, inetAddress, PORT_NUMBER);
            ds.send(dp);
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            ds.receive(response);
            String serverResponse = new String(buffer,0, response.getLength());
            logger.debug(serverResponse);
        }catch(IOException io){
            logger.error(io.getMessage());
        }
    }
}
