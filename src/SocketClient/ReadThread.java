package SocketClient;

import SocketServer.SocketManager;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadThread extends Thread {
    BufferedReader br;

    public ReadThread(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        super.run();
        try {

            while (true) {
                String msg = br.readLine();
                System.out.println(msg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
