package com.example.se2_einzelbeispiel;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {

    String answer;
    String matNr;

    public ServerThread(String matNr) {
        this.matNr = matNr;
    }

    @Override
    public void run() {

        try (Socket clientSocket = new Socket("se2-isys.aau.at", 53212);){

            String serverInput = matNr;

            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inputStreamFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outputStream.writeBytes(serverInput + "\n");

            answer = inputStreamFromServer.readLine();

        } catch (Exception e) {
            answer = "Operation not successful.";
        }
    }

    public String getAnswer() {
        return answer;
    }
}
