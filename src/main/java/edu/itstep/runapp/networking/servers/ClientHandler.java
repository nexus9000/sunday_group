package edu.itstep.runapp.networking.servers;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
   static Logger logger = Logger.getLogger(ClientHandler.class);
    private Socket client;
    public ClientHandler(Socket client){
        this.client = client;
    }
    @Override
    public void run(){
        logger.info("Client with IP addr: "+ client.getInetAddress() + " "+client.getPort());
        try{
            PrintWriter out = new PrintWriter(client.getOutputStream());//write
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String command = br.readLine();
            logger.debug(command);
            if(command.equalsIgnoreCase("get time")){
                out.println(java.time.LocalDateTime.now());
                out.flush();
            }
        }catch(IOException io){
            logger.error(io.getMessage());
        }
    }
}
