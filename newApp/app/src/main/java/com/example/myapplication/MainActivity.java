package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String name, email;
    double xCoordinate;
    double yCoordinate;

    EditText nameInput;
    EditText emailInput;
    EditText xCoordinateInput;
    EditText yCoordinateInput;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nameInput = (EditText) findViewById(R.id.nameInput);
        //emailInput = (EditText) findViewById(R.id.emailInput);
        xCoordinateInput = (EditText) findViewById(R.id.xCoordinateInput);
        yCoordinateInput = (EditText) findViewById(R.id.yCoordinateInput);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //name = nameInput.getText().toString();
                //email = emailInput.getText().toString();
                xCoordinate = Double.valueOf(xCoordinateInput.getText().toString());
                yCoordinate = Double.valueOf(yCoordinateInput.getText().toString());

                //showToast(name);
                //showToast(email);
                //showToast(String.valueOf(xCoordinate));
                //showToast(String.valueOf(yCoordinate));
                showToast(String.valueOf(xCoordinate + yCoordinate));
            }
        });
    }
    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}