package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean online = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("Bye")) {
                            online = false;
                        }
                    }
                    if (online) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    } else {
                        out.write("HTTP/1.1 510 SERVER OFFLINE\r\n\r\n".getBytes());
                    }
                    out.flush();
                }
                if (!online) {
                    server.close();
                }
            }
        }
    }
}
