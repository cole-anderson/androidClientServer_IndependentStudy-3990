package myapplication;
//main
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

import static parserFile.parser.parseFile;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    //TODO FLOW
    //PARSE CLUSTER
    //USER INPUT
    //COMPARE AGAINST MBR
    //OUTPUT

    double tempPointx = 0;
    double tempPointy = 0;
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

        xCoordinateInput = (EditText) findViewById(R.id.xCoordinateInput);
        yCoordinateInput = (EditText) findViewById(R.id.yCoordinateInput);
        outputNum = (TextView) findViewById(R.id.outputNum);

        //SubmitButton
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double outVal[] = new double[2];
                int size;
                coordObj.coordinates clusterT = new coordObj.coordinates();
                clusterT.x = new Vector();
                clusterT.y = new Vector();

                //TEMP
                //clusterT.x.add(tempPointx);
                //clusterT.y.add(tempPointy);

                clusterT.x.add(0, 1.0);
                clusterT.y.add(0, 1.0);

                clusterT.x.add(1, 250.0);
                clusterT.y.add(1, 250.0);

                clusterT.x.add(2, 10.0);
                clusterT.y.add(2, 10.0);

                clusterT.x.add(3, 20.0);
                clusterT.y.add(3, 20.0);

                clusterT.topRCorner = new double [] {250.0, 250.0};
                clusterT.botLCorner = new double [] {1.0, 1.0};

                //------------------------------------------------------------------
                /*
                    //USER INPUT
                /*
                 */
                //User input of x and y values
                tempPointx = Double.parseDouble(xCoordinateInput.getText().toString());
                tempPointy = Double.parseDouble(yCoordinateInput.getText().toString());

                //If statements to avoid app crash if submit button is pressed without user input
                if(xCoordinateInput.getText().toString().length() == 0)
                {
                    xCoordinateInput.setText("0");
                }
                if(yCoordinateInput.getText().toString().length() == 0)
                {
                    yCoordinateInput.setText("0");
                }
                /*
                    MBR COMPARISON
                */
                if(coordObj.coordinates.topRCorner[0] < tempPointx)
                {
                    showToast("outside MBR RIGHT");
                    outputNum.setText("OUTSIDE MBR");
                }
                else if(coordObj.coordinates.topRCorner[1] < tempPointy)
                {
                    showToast("outside MBR TOP");
                    outputNum.setText("OUTSIDE MBR");
                }
                else if(coordObj.coordinates.botLCorner[0] > tempPointx)
                {
                    showToast("outside MBR LEFT");
                    outputNum.setText("OUTSIDE MBR");
                }
                else if(coordObj.coordinates.botLCorner[1] > tempPointy)
                {
                    showToast("outside MBR BOTTOM");
                    outputNum.setText("OUTSIDE MBR");
                }
                //WHEN INSIDE MBR:
                else {

                    //CURRENT OUTPUT
                    size = clusterT.x.size();
                    outVal = parserFile.parser.findClosest(clusterT, size, tempPointx, tempPointy);
                    outputNum.setText(String.format("(%s,%s)", outVal[0], outVal[1]));


                }
                    //TODO DELETE
                    //if(tempPointx == tempPointy)
                    //{
                    //    outputNum.setText(String.valueOf("5000"));
                    //}
                    //NOT DEAD*****
                    //outputPointx = String.valueOf(clusterT.x.get(0));
                    //outputPointy = String.valueOf(clusterT.y.get(0));
                    //outputNum.setText(String.format("(%s,%s)", outputPointx, outputPointy));

                    //outputNum.setText(String.valueOf(clusterT.x.get(0)));//dead
                    //outputNum.setText(String.valueOf(outputPoint));//dead
                    //double testD = parserFile.parser.tester(600.00);//dead

                    //debug call&
                    //showToast(String.valueOf(testD));
                //}
                }
        });
    }
    //****************************************************************************************
    //debug func&
    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}