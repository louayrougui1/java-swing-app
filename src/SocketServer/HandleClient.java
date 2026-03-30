package SocketServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HandleClient extends Thread {
    Socket s;

    public HandleClient(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        super.run();
        try {

            //ouvrir le socket serveur en mode ecriture
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println("Envoyer Votre ID: ");
            pw.flush();
            //ouvrir le socket Serveur en mode lecture
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String id = br.readLine();
            System.out.println("ligne: " + id);
            //sauvegarde du socket dans liste des socket
            CustomSocket custom = new CustomSocket(id, s);
            SocketManager.socketList.add(custom);
            //disc
            ReadThread rt = new ReadThread(br,id);
            rt.start();

            //WriteThread wt = new WriteThread(pw, sc);
            //wt.start();

        } catch (Exception e) {
            System.out.println("Erreur in HandleClient ye 7aj : " + e.getMessage());
        }
    }
}
