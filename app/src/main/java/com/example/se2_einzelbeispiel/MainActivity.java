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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        matNrInput = findViewById(R.id.matNrInput);
        answerText = findViewById(R.id.textView_ServerAnswer);

        submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage();
            }
        });
    }


    private void sendMessage() {

        matNr = matNrInput.getText().toString();

        ServerThread s = new ServerThread(matNr);
        s.start();

        try {
            s.join();
        }
        catch (InterruptedException ie) {
            Log.e("InterruptedException", ie.getMessage());
        }

        String answer = s.getAnswer();
        answerText.setText(answer);
    }
}