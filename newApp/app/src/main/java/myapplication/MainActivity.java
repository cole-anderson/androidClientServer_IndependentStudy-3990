package myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import myapplication.R;

//import java.util.Vector;
import java.lang.*;
import java.util.*;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    double tempPointx;
    double tempPointy;
    String outputPointx;
    String outputPointy;

    EditText xCoordinateInput;
    EditText yCoordinateInput;
    TextView outputNum;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //**************** SINGLE CLUSTER
        //.coordinates clusterT = new coordObj.coordinates();
        //clusterT.x = new Vector();
        //clusterT.y = new Vector();

        //clusterT.x.add(Double.parseDouble("5.0"));
        //clusterT.y.add(Double.parseDouble("6.0"));

        //****************

        xCoordinateInput = (EditText) findViewById(R.id.xCoordinateInput);
        yCoordinateInput = (EditText) findViewById(R.id.yCoordinateInput);
        outputNum = (TextView) findViewById(R.id.outputNum);

        //SubmitButton
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordObj.coordinates clusterT = new coordObj.coordinates();
                clusterT.x = new Vector();
                clusterT.y = new Vector();

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
                clusterT.x.add(tempPointx);
                clusterT.y.add(tempPointy);

                //OUTPUT:
                outputPointx = String.valueOf(clusterT.x.get(0));
                outputPointy = String.valueOf(clusterT.y.get(0));
                outputNum.setText(String.format("(%s,%s)", outputPointx, outputPointy));

                //outputNum.setText(String.valueOf(clusterT.x.get(0)));//dead
                //outputNum.setText(String.valueOf(outputPoint));//dead
                //double testD = parserFile.parser.tester(600.00);//dead







                //debug call&
                //showToast(String.valueOf(testD));
            }
        });
    }
    //****************************************************************************************
    //debug func&
    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}