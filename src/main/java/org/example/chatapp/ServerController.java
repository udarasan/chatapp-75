package org.example.chatapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    ServerSocket serverSocket;
    Socket localSocket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message="";
    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtMessage;

    @FXML
    void sendOnAction(ActionEvent event) {

    }

    public void initialize() {
        new Thread(() -> {
            try {
                 serverSocket = new ServerSocket(4000);
                 txtArea.appendText("Waiting for client...\n");
                 localSocket=serverSocket.accept();
                 txtArea.appendText("Client connected...\n");
                 dataInputStream =new DataInputStream(localSocket.getInputStream());
                 while (!message.equals("exit")) {
                     message=dataInputStream.readUTF();
                     txtArea.appendText("client : " + message + "\n");
                 }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();


    }

}

