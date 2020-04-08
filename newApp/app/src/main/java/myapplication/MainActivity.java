package myapplication;
//main
import static java.io.FileDescriptor.in;
import static parserFile.parser.*;
//import static parserFile.parser.parseArff;
import static parserFile.parser.parseFnl;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import coordObj.coordinates;
//import java.util.Vector;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.*;
import java.util.*;
import myapplication.R;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

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
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    xCoordinateInput = (EditText)findViewById(R.id.xCoordinateInput);
    yCoordinateInput = (EditText)findViewById(R.id.yCoordinateInput);
    outputNum = (TextView)findViewById(R.id.outputNum);

    /*
            READ FILES IN
        */

    //Return Creations
    retClass.returnClass clust = new retClass.returnClass();
    retClass.returnClass traj = new retClass.returnClass();

    testpackage test = new testpackage();
    test.t = 69;
    System.out.println(test.t);

    coordObj.coordinates clusterV[] = new coordObj.coordinates[2];

    //String fileName = "newclusterTest.arff";
    //String fileName = "test.txt";
    double va;
    double xv = 0.0;
    //va = parserFile.parser.parseArff("test.txt",xv);
      clust = testP("test.txt");
      System.out.println("xv pass" + xv);

    int test1 = coordObj.coordinates.globe; //qualifier
    //parserFile.parser.parseArff(fileName);
    xv = clust.c[0].x.get(0); //broken because parser

    outputNum.setText(String.format("(%s,%s)", "return cluster val", xv));

    //works
    //clusterV.x.add(15.0);


    //Log.i("DEBUG-DEBUG", "THIS IS A TEST OF THIS");

    //String fileName2 = "trajectory.fnl";
    String fileName2 = "testJ.txt";
    //traj = parseFnl(fileName2); TODO FIX

    //testbed
    int sizeN = 3; //clust.size;

    //        final coordObj.coordinates clusterT[] = new coordObj.coordinates[sizeN];
    //        for(int s = 0; s < sizeN; s++)
    //        {
    //            clusterT[s] = clust.c[s];
    //        }
    //outputNum.setText(String.format("(%s,%s)", sizeN, clust.c[0].x.get(0)));

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    //SubmitButton
    submitButton = (Button)findViewById(R.id.submitButton);
    submitButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v)
      {

        double outVal[] = new double[2];
        int sizecc = 0;
        //TODO DELETE
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

                        clusterT.topRCornerX = 250;
                        clusterT.topRCornerY = 250;
                        clusterT.botLCornerX = 1.0;
                        clusterT.botLCornerY = 1.0;

        //------------------------------------------------------------------
        /*
                    //USER INPUT
                /*
                 */
        //User input of x and y values
        tempPointx = Double.parseDouble(xCoordinateInput.getText().toString());
        tempPointy = Double.parseDouble(yCoordinateInput.getText().toString());

        //If statements to avoid app crash if submit button is pressed without user input
        if (xCoordinateInput.getText().toString().length() == 0)
        {
          xCoordinateInput.setText("0");
        }
        if (yCoordinateInput.getText().toString().length() == 0)
        {
          yCoordinateInput.setText("0");
        }


        /*
                    MBR COMPARISON
                */
                        if(clusterT.topRCornerX < tempPointx)
                        {
                            showToast("outside MBR RIGHT");
                            outputNum.setText("OUTSIDE MBR");
                        }
                        else if(clusterT.topRCornerY < tempPointy)
                        {
                            showToast("outside MBR TOP");
                            outputNum.setText("OUTSIDE MBR");
                        }
                        else if(clusterT.botLCornerX > tempPointx)
                        {
                            showToast("outside MBR LEFT");
                            outputNum.setText("OUTSIDE MBR");
                        }
                        else if(clusterT.botLCornerY > tempPointy)
                        {
                            showToast("outside MBR BOTTOM");
                            outputNum.setText("OUTSIDE MBR");
                        }
                        //WHEN INSIDE MBR:
                        else
                          {

                            //CURRENT OUTPUT
                            sizecc = clusterT.x.size();
                            outVal = parserFile.parser.findClosest(clusterT, sizecc, tempPointx, tempPointy);
                            //outputNum.setText(String.format("(%s,%s)", outVal[0], outVal[1]));


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
    }); //end of submit button
  }
  //****************************************************************************************
  //debug func&
  private void showToast(String text)
  {
    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
  }
  public retClass.returnClass testP(String fname)
  {

        //Double retD = 5.0;
       //System.out.println("RETD" + retD);
       //coordObj.coordinates returnArray [] = new coordObj.coordinates[1];
       retClass.returnClass r1 = new retClass.returnClass();
       try
       {
           DataInputStream textFileStream = new DataInputStream(getAssets().open(String.format("test.txt")));
           int lineID = 0;
           boolean exitCond = false;
           String seperated[];
           String last = "";
           int j = -1;
           int inc = 0;
           int num = 0;
           File myObj = new File(fname);
           //Scanner myReader = new Scanner(new File(getAssets().open(fname)));
           Scanner myReader = new Scanner(textFileStream);
           //Scanner myReader = new Scanner(myObj);
           while (myReader.hasNextLine() && exitCond == false)
           {
               String parseTop = myReader.nextLine();
               if (parseTop.length() == 0)
                   inc++;
               if (inc == 2)
               {
                   num = numCluster(last);
                   //System.out.println("last" + last + "\n");
                   inc++;
               }
               if (inc == 3)
               {
                   exitCond = true;
               }
               last = parseTop;
           }
           //System.out.println("out");

           //Cluster Creation
           coordObj.coordinates cluster[] = new coordObj.coordinates[num];
           for (int i = 0; i < num; i++)
           {
               cluster[i] = new coordObj.coordinates();
               cluster[i].x = new Vector();
               cluster[i].y = new Vector();
           }
           r1.size = num;

           int loop = 0;
           String nll;
           String xi = "0";
           String yi = "0";
           String ci = "0";
           char t;
           int ind;
           int size = 0;
           int size0 = 0;
           int size1 = 0;

           while (myReader.hasNextLine())
           {
               String data = myReader.nextLine();
               seperated = data.split(",");

               for (String a : seperated)
               {
                   switch (j)
                   {
                       case -1:
                           j++;
                           break;
                       case 0:
                           nll = a;
                           //System.out.println("//nll:  " + nll); //PRINT DEBUG
                           j++;
                           break;
                       case 1:
                           xi = a;
                           //System.out.println("//x: " + xi); //PRINT DEBUG
                           j++;
                           break;
                       case 2:
                           yi = a;
                           //System.out.println("//y: " + yi); //PRINT DEBUG
                           j++;
                           break;
                       case 3:
                           ci = a;
                           //parsing out the cluster num
                           t = ci.charAt(7);
                           ind = Integer.parseInt(String.valueOf(t));
            /*
           ACTUALLY INPUTTING DATA INTO OBJECTS
           */
                           ind = Integer.parseInt(String.valueOf(t));
                           cluster[ind].x.add(Double.parseDouble(xi));
                           cluster[ind].y.add(Double.parseDouble(yi));
                           //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                           //output jargain
                           // if (ind == 0)
                           // {
                           //   size0 = cluster[0].x.size();
                           //   System.out.println("//x INCLUSTER: " + cluster[ind].x.get(size0 - 1));
                           //   System.out.println("//Y INCLUSTER: " + cluster[ind].y.get(size0 - 1));
                           //   System.out.println("//cluster num:" + ind);
                           // }
                           // else if (ind == 1)
                           // {
                           //   size1 = cluster[1].x.size();
                           //   System.out.println("//x INCLUSTER: " + cluster[ind].x.get(size1 - 1));
                           //   System.out.println("//Y INCLUSTER: " + cluster[ind].y.get(size1 - 1));
                           //   System.out.println("//cluster num:" + ind);
                           // }
                           j = 0;
                           break;
                       default:
                           System.out.println("error found parsing arff");
                   }
               }
           }
           myReader.close(); //close file

        /*
        Set Values of Cluster MBRs
      */
           //Top Right Corner x,y and Bottom Left Corner x,y
           for (int i = 0; i < num; i++)
           {
               cluster[i].topRCornerX = Collections.max(cluster[i].x);                                                 //RIGHT BOUND
               cluster[i].topRCornerY = Collections.max(cluster[i].y);                                                 //LUPPER BOUND
               System.out.println("//topRCorner: " + i + ":" + cluster[i].topRCornerX + "," + cluster[i].topRCornerY); //PRINT DEBUG
               cluster[i].botLCornerX = Collections.min(cluster[i].x);                                                 //LEFT BOUND
               cluster[i].botLCornerY = Collections.min(cluster[i].y);                                                 //LOWER BOUND
               System.out.println("//botLCorner: " + i + ":" + cluster[i].botLCornerX + "," + cluster[i].botLCornerY); //PRINT DEBUG
           }
      /*
        Set Values of SuperMBR
      */
           //TODO %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
           //set return value
           //Log.i("Debug", "fuck");
           //Log.i("DEBUG-DEBUG", "THIS IS A TEST OF THIS");
           r1.c = cluster;

           //returnArray = cluster;
           //retD = 69.0;
           //System.out.println("RETD2" + retD);

       }
       catch (IOException e)
       {
           System.out.println("error with file-start\n");
           e.printStackTrace();
           System.out.println("error with file-end\n");
       }
       //System.out.println("RETD3" + retD);
       //return retD;
       return r1; //return obj
  }
}
