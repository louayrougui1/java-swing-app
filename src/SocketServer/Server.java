package SocketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Starting Server...");
        try {
            ServerSocket server = new ServerSocket(9002);
            System.out.println("Server on bro ☺");

            int nbClient = 0;
            while (nbClient < 4) {
                Socket s = server.accept();
                System.out.println("Client is Accepted");
                nbClient++;

                HandleClient handle=new HandleClient(s);
                handle.start();
            }
        } catch (IOException e) {
            System.out.println("Erreur Server ye 7aj: " + e.getMessage());
        }
        System.out.println("End Server ☺");
    }
}
