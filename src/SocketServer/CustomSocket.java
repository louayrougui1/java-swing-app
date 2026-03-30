package SocketServer;

import java.net.Socket;

public class CustomSocket {
    String id;
    Socket s;

    public CustomSocket(String id, Socket s) {
        this.id = id;
        this.s = s;
    }
}
