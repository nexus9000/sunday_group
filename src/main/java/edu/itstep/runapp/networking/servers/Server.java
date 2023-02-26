package edu.itstep.runapp.networking.servers;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract class Server extends Thread{
    protected int port;
    protected String typeServer;
    abstract int getPort();
    abstract String getTypeServer();
    abstract  void startServer() throws IOException, InterruptedException, ExecutionException, TimeoutException;
    abstract void stopServer() throws IOException;
    @Override
    public abstract void run();
}
