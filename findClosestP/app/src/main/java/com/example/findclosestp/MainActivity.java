package com.example.findclosestp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables to Store Entries
    double xInput;
    double yInput;

    //Actual Entries
    EditText xEntry;
    EditText yEntry;

    Button findER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xEntry = (EditText) findViewById(R.id.xEntry);
        yEntry = (EditText) findViewById(R.id.yEntry);
//BROKEN STUFF
//        findER = findViewById(R.id.findER);
//        findER.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                xInput = Double.valueOf(xEntry.getText().toString());
//                yInput = Double.valueOf(yEntry.getText().toString());
//
//                showToast(String.valueOf(xInput));
//                showToast(String.valueOf(yInput));
//
//            }
//        });
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}
