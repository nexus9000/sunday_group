package edu.itstep.runapp.networking.clients;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTime {
    public static final int PORT_SERVER = 123;
    static Logger logger = Logger.getLogger(ClientTime.class);
    public static void main(String...arg){
        try(Socket socket = new Socket("localhost", PORT_SERVER)){
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = "";
            Scanner sc = new Scanner(System.in);
            while(!line.equalsIgnoreCase("exit")){
                pw.println("get time");
                String reply = br.readLine();
                logger.debug(reply);
                logger.info("type exit to terminate connection");
                line = sc.nextLine();
            }
        }catch(IOException io){
            logger.error(io.getMessage());
        }finally{
            logger.debug("Session terminated!");
        }
    }
}
