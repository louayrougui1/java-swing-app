package SocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

public class ReadThread extends Thread {
    BufferedReader br;
    String id;

    public ReadThread(BufferedReader br, String id) {
        this.br = br;
        this.id = id;
    }

    @Override
    public void run() {
        super.run();
        try {

            while (true) {
                String toId = br.readLine();
                String msg = br.readLine();
                //System.out.println("id: "+id + " / " + msg + " AT " + new Date());
                //SocketManager.diffuserMessage(msg, id);
                SocketManager.privateMessage(msg, id,toId);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
