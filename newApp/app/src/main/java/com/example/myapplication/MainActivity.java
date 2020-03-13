package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String name, email;
    double tempPointx;
    double tempPointy;
    double outputPoint;

    EditText xCoordinateInput;
    EditText yCoordinateInput;
    TextView outputNum;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xCoordinateInput = (EditText) findViewById(R.id.xCoordinateInput);
        yCoordinateInput = (EditText) findViewById(R.id.yCoordinateInput);
        outputNum = (TextView) findViewById(R.id.outputNum);

        //SubmitButton
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If statements to avoid app crash if submit button is pressed without user input
                if(xCoordinateInput.getText().toString().length() == 0)
                {
                    xCoordinateInput.setText("0");
                }
                if(yCoordinateInput.getText().toString().length() == 0)
                {
                    yCoordinateInput.setText("0");
                }
                tempPointx = Double.parseDouble(xCoordinateInput.getText().toString());
                tempPointy = Double.parseDouble(yCoordinateInput.getText().toString());
                outputPoint = tempPointx + tempPointy;
                outputNum.setText(String.valueOf(outputPoint));

                //debug call&
                //showToast(String.valueOf(tempPoint));//dead
            }
        });
    }
    //debug func&
    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}