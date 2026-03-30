package SocketClient;

import SocketClient.ReadThread;
import SocketClient.WriteThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Client Starting ..");
        try {
            Socket s = new Socket("127.0.0.1", 9002);
            System.out.println("Client connected to server bro ☺");
            //ouvrir client en mode lecture
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String ligne = br.readLine();
            System.out.print(ligne);
            //ecriture de id
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            //ouvrir client en mode ecriture
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println(id);
            pw.flush();

            //disc
            ReadThread rt = new ReadThread(br);
            rt.start();

            WriteThread wt = new WriteThread(pw, sc);
            wt.start();

        } catch (IOException e) {
            System.out.println("Erreur Client ye 7aj : " + e.getMessage());
        }
    }
}
