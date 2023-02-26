package edu.itstep.runapp.networking.servers;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class ASyncTcpServer extends Server {
    static final Logger logger = Logger.getLogger(ASyncTcpServer.class);
    private static final int PORT_NUMBER = 5001;
    private static final String HOST_NAME = "192.168.100.4";

    @Override
    int getPort() {
        return PORT_NUMBER;
    }

    @Override
    String getTypeServer() {
        return null;
    }

    @Override
    void startServer() throws IOException, InterruptedException, ExecutionException, TimeoutException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        int port = getPort();
        server.bind(new InetSocketAddress("192.168.100.4", port));
        while(true) {
            Future<AsynchronousSocketChannel> connectionPool = server.accept();
            try {
                AsynchronousSocketChannel client = connectionPool.get();
                if ((client != null) && (client.isOpen())){
                    ByteBuffer buffer = ByteBuffer.allocate(32);
                    Future<Integer> read = client.read(buffer);
                    read.get();
                    System.out.println("received by client: " + new String(buffer.array()).trim());
                    buffer.flip();
                    String str = "Test message";
                    Future<Integer> write = client.write(ByteBuffer.wrap(str.getBytes()));
                    write.get();
                    buffer.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{

            }
        }

        //client.close();
    }




    @Override
    void stopServer() throws IOException {

    }

    @Override
    public void run() {
        try {
            logger.debug(Thread.currentThread().getName() + " was started!");
            startServer();
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException exc) {
            logger.error(exc.getMessage());
        }
    }
}
