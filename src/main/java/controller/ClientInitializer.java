package controller;

import model.Client;

import java.io.IOException;
import java.net.Socket;

public class ClientInitializer {
    private static int PORT = 8000;
    private static String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {

        Client client = new Client(new Socket(HOST, PORT));
        client.startClient();
    }
}
