package org.example.chatapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerController {

    ServerSocket serverSocket;
    List<Socket> clients = new ArrayList<>();

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtMessage;

    public void initialize() {

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(4000);
                txtArea.appendText("Server started...\n");

                while (true) {
                    Socket socket = serverSocket.accept();
                    clients.add(socket);

                    txtArea.appendText("New client connected\n");

                    // handle each client in separate thread
                    new Thread(() -> handleClient(socket)).start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleClient(Socket socket) {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String message;

            while (true) {
                message = in.readUTF();
                txtArea.appendText("Client: " + message + "\n");

                broadcast(message);
            }

        } catch (IOException e) {
            txtArea.appendText("Client disconnected\n");
        }
    }

    private void broadcast(String message) {
        for (Socket socket : clients) {
            try {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(message);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        broadcast("Server: " + txtMessage.getText());
    }
}