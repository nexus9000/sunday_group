package edu.itstep.runapp.networking.servers;

import java.util.Objects;

public class GetServerFactory {
   public Server getServerInstance(String type){
       if(Objects.isNull(type)) return null;
       else if(type.equalsIgnoreCase("tcp time server")){
           return new TcpTimeServer();
       }else if (type.equalsIgnoreCase("async")){
           return  new ASyncTcpServer();
       }else if(type.equalsIgnoreCase("udp")){
           return new UdpServer();
       }
       else{
           return null;
       }
   }
}
