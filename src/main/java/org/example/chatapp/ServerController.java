package org.example.chatapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtMessage;

    @FXML
    void sendOnAction(ActionEvent event) {

    }

    public void initialize() {
        try {
            ServerSocket serverSocket = new ServerSocket(4000);
            Socket localSocket=serverSocket.accept();
            DataInputStream dataInputStream
                    =new DataInputStream(localSocket.getInputStream());
            txtArea.appendText(dataInputStream.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

