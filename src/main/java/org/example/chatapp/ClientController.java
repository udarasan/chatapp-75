package org.example.chatapp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientController {

    DataOutputStream dataOutputStream;
    String message="";
    Socket remoteSocket;
    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtMessage;

    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream = new DataOutputStream(remoteSocket.getOutputStream());
        dataOutputStream.writeUTF(txtMessage.getText());
        dataOutputStream.flush();
    }

    public void initialize() {
        try {
             remoteSocket = new Socket("127.0.0.1", 4000);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

