package com.example.se2_einzelbeispiel;

import android.util.Log;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {

    String answer;
    String matNr;

    public ServerThread(String matNr) {
        this.matNr = matNr;
    }

    public void run() {

        try{

            String inputSentence = matNr;

            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

            DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outStream.writeBytes(inputSentence + "\n");

            answer = inputFromServer.readLine();
            clientSocket.close();

        } catch (Exception e) {
            answer = "Not successful.";
        }

    }

    public String getAnswer() {return answer;}

}
