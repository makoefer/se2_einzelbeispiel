package com.example.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText matNrInput;
    String matNr;
    TextView answerText;
    Button submitButton;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matNrInput = findViewById(R.id.matNrInput);
        answerText = findViewById(R.id.textView_ServerAnswer);

        submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(@SuppressWarnings("UnusedParameters")View v) {
                sendMessage();
            }
        });

        calculateButton = findViewById(R.id.button_Calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(@SuppressWarnings("UnusedParameters")View v) {
                makeCalculation();
            }
        });
    }


    private void sendMessage() {

        matNr = matNrInput.getText().toString();

        ServerThread s = new ServerThread(matNr);
        s.start();

        try {
            s.join();
        } catch (InterruptedException ie) {
            Log.e("InterruptedException", ie.getMessage());
            s.interrupt();
        }
        String serverAnswer = s.getAnswer();
        answerText.setText(serverAnswer);
    }


    private void makeCalculation() {

        // 01617360 mod 7 = 3  -->  Quersumme als Binärzahl darstellen

        String matNrString = matNrInput.getText().toString();

        int sum = 0;
        for (int i = 0; i < matNrString.length(); i++) {
            char c = (char) (matNrString.charAt(i) - '0');

            if (c >= 0 && c <= 9) {
                sum += c;
            }
        }

        answerText.setText(Integer.toBinaryString(sum));
    }
}