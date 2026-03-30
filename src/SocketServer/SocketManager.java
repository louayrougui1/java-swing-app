package SocketServer;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class SocketManager {
    public static ArrayList<CustomSocket> socketList = new ArrayList<>();

    public static void diffuserMessage(String msg, String id) {
        for (int i = 0; i < socketList.size(); i++) {
            Socket s = socketList.get(i).s;
            try {
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                pw.println("id: " + id + " / " + msg + " AT " + new Date());
                pw.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void privateMessage(String msg, String id, String toId) {
        for (int i = 0; i < socketList.size(); i++) {

            if (Objects.equals(socketList.get(i).id, toId)) {
                Socket s = socketList.get(i).s;

                try {
                    PrintWriter pw = new PrintWriter(s.getOutputStream());
                    pw.println("id: " + id +" / "+"To Client: "+toId+ " / MSG: " + msg + "/ AT " + new Date());
                    pw.flush();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

}
