package SocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriteThread extends Thread {
    PrintWriter pw;
    Scanner sc;

    public WriteThread(PrintWriter pw, Scanner sc) {
        this.pw = pw;
        this.sc = sc;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                pw.println(sc.nextLine());
                pw.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
