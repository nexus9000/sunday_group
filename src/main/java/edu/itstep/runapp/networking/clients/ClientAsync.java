package edu.itstep.runapp.networking.clients;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClientAsync {
    static Logger logger = Logger.getLogger(ClientAsync.class);
    private static final String HOST_NAME = "192.168.100.4";
    private static final int PORT_NUMBER = 5001;
    public static void main(String...arg){
        try(AsynchronousSocketChannel client = AsynchronousSocketChannel.open()){
            Future<Void> res = client.connect(new InetSocketAddress(HOST_NAME,5001));
            res.get();
            String str = "test by client";
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            Future<Integer> write = client.write(buffer);

            write.get();
            buffer.flip();
            Future<Integer> read = client.read(buffer);
            read.get();//wait connection
            System.out.println("Received by server "+ new String(buffer.array()).trim());

            buffer.clear();

        } catch(IOException io){
            io.printStackTrace();
        }catch( InterruptedException  |  ExecutionException e){
            e.printStackTrace();
        }finally{
            System.out.println("Disconecting by server!");
        }

    }
}
