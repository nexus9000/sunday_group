package edu.itstep.runapp.networking.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class TcpTimeServer extends Server{
    static Logger logger = Logger.getLogger(TcpTimeServer.class);
    private static final int PORT_NUMBER = 123;
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
        ServerSocket server = new ServerSocket(PORT_NUMBER);
        while(true){
            Socket client = server.accept();
            ClientHandler ch = new ClientHandler(client);
            Thread thr = new Thread(ch);
            thr.start();
        }
    }

    @Override
    void stopServer() throws IOException {

    }

    @Override
    public void run() {
       logger.info(Thread.currentThread().getName() + " was started!");
       try{
           startServer();
       }catch(IOException io){
           logger.error(io.getMessage());
       }
    }

}
