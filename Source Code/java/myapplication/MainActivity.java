/*
  MAIN CLIENT FILE THAT PARSES THE TEXT FILE FOR FUTURE TRAJECTORY
  AND THEN PINGS THE SERVER FOR MBRS DEPENDING ON LOCATION
*/

//Headers:
package myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.io.*;
import java.net.*;


//import static myapplication.client.ping;
//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private Socket client;
  public Boolean ping = false;
  private DataInputStream din;
  private DataOutputStream dout;
  private ObjectInputStream obji;
  private ObjectOutputStream objo;
  private PrintWriter pw;
  private String message = "false";

  public Boolean rect= true;
  public int pingid = 0;
  private int index = 0;
  public int afterVal = 0;
  private int sizecc = 0;
  private int sizeNUM = 0;
  private  double outVal[] = new double[2];
  public int loc = -1;

  //public returnClass ret = new returnClass();

  public coordinates cluster = new coordinates();//when returning coordinates
  public returnClass clustR = new returnClass();//when returning return class




  double tempPointx = 0;
  double tempPointy = 0;
  String outputPointx;
  String outputPointy;

  EditText xCoordinateInput;
  EditText yCoordinateInput;
  TextView outputNum;
  TextView textView2;

  Button findPoint;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    //LOAD TRAJECTORY: *
    final returnClass traj;
    //String fileName2 = "trajectory.fnl";
    //String fileName2 = "testJ.txt";
    //traj = parseFnl(fileName2); //TODO FIX**
    //System.out.println("//fnl" + traj.f.hx);//PRINT DEBUG

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    outputNum = (TextView) findViewById(R.id.outputNum);
    textView2 = (TextView) findViewById(R.id.textView2);

    //TODO DELETE:
    //String fileName = "clusterfnl.arff";
    //String fileName = "test.txt";
    //double xv = 0.0;
    //clust = parseArff(fileName); //TODO FIX
    //System.out.println("array 0 start (EXP 5)" + clust.c[0].x.get(0)); //DELETE
    //System.out.println("array 1 start (EXP 15)" + clust.c[1].x.get(0)); //DELETE

    //String fileName2 = "trajectory.fnl";
    String fileName2 = "testJ.txt";
    traj = parseFnl(fileName2); //TODO FIX**
    System.out.println("! Trajectory " + traj.f.lx + traj.f.ly);

//    System.out.println("fnl" + traj.f.hx);
    //end delete
    cluster.x = new Vector();
    cluster.y = new Vector();
    cluster.topRCornerX = new Vector();
    cluster.topRCornerY = new Vector();
    cluster.botLCornerX = new Vector();
    cluster.botLCornerY = new Vector();



    /*
    Submit Button
    */
    findPoint = (Button) findViewById(R.id.findPoint);
    findPoint.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        System.out.println("CLICK BUTTON");
        //TRAJECTORY
        if (loc < traj.f.hx.size()-1)
          loc++;
        else
          loc = 0;
        tempPointx = traj.f.lx.get(loc);
        tempPointy = traj.f.ly.get(loc);
        textView2.setText(String.format("(%s,%s)", tempPointx, tempPointy));

        new Thread(new Runnable()
        {
          @Override
          public void run()
          {
            //CLUSTERING
            try {
              System.out.println("----------------INSIDE TRY----------------");
              String str1 = "";
              String str2 = "";
              //System.out.println("got hereTRY1");
              String hostID = "10.0.2.2";
              int portID = 4445;

              if(ping == false)
              {
                //ONLY CALLED FIRST RUN
                client = new Socket(hostID, portID);
                dout = new DataOutputStream(client.getOutputStream());
                din = new DataInputStream(client.getInputStream());
                objo = new ObjectOutputStream(client.getOutputStream());
                obji = new ObjectInputStream(client.getInputStream());
                //ONLY CALLS ON FIRST OCCURANCE
                //SEND PING ID = 0:
                str1 = Integer.toString(pingid);
                dout.writeUTF(str1);
                dout.flush();
                pingid = 1;

                //Reading New MBR:
                cluster = (coordinates) obji.readObject(); //error
                System.out.println("CLUSTER.x: " + cluster.x);
                System.out.println("CLUSTER.y: " + cluster.y);
                System.out.println("TOPX" +cluster.topRCornerX);
                System.out.println("TOPX" +cluster.topRCornerY);
                System.out.println("TOPX" +cluster.botLCornerX);
                System.out.println("TOPX" +cluster.botLCornerY);
                rect = true;
                ping = true;
                System.out.println("//exit first iterate");
              }

              if(tempPointx > cluster.topRCornerX.get(0) || tempPointy > cluster.topRCornerY.get(0) || tempPointx < cluster.botLCornerX.get(0) || tempPointy < cluster.botLCornerY.get(0))
              {
                //outputNum.setText("Requesting new MBR from Server. Please Wait 5 Seconds Before Finding Closest");
                System.out.println("FALSE");
                rect = false;
              }

              //IFOUTSIDE MBR
              if (rect == false)
              {
                //IF OUTSIDE MBR QUERY FOR NEW MBR

                //SEND PING ID = 0:
                pingid = 0;
                str1 = Integer.toString(pingid);
                dout.writeUTF(str1);
                dout.flush();

                //Reading New MBR:
                cluster = (coordinates) obji.readObject(); //error
                System.out.println("CLUSTER.x: " + cluster.x);
                System.out.println("CLUSTER.y: " + cluster.y);
                System.out.println("TOPX" +cluster.topRCornerX);
                System.out.println("TOPX" +cluster.topRCornerY);
                System.out.println("TOPX" +cluster.botLCornerX);
                System.out.println("TOPX" +cluster.botLCornerY);
                rect = true;
                pingid = 1;
              }
              //IF INSIDE MBR
              if(rect == true)
              {
                System.out.println("TRUE");
                //IF INSIDE MBR KEEP LOOPING THROUGH COMPARING
                outVal = findClosest(cluster, tempPointx, tempPointy);
//                  outputNum.setText(String.format("(%s,%s)", outVal[0], outVal[1]));
              }

              //MBR Determiner
            }
            catch (IOException| ClassNotFoundException e) {
              System.out.println("Exception Caught @114");
              e.printStackTrace();
            }
          }
        }).start();

        if (rect == true) {
          //Display Closest Point in Cluster

          System.out.println(outVal[0]+ " - " + outVal[1]);
          outputNum.setText(String.format("(%s,%s)", outVal[0], outVal[1]));
        }
        else if (rect == false) {
          System.out.println("//REQUESTING NEW MBR DUE TO BOUNDS");
          outputNum.setText("Requesting new MBR from Server. Please Wait 5 Seconds Before Finding Closest");
          //loc --;
        }

      }//end onclick
    }); //end of submit button
  }//end on create


  //****************************************************************************************
  /*
    Debug Function
    -used to output text during GUI development
  */
  //****************************************************************************************
  public void showToast(String text)
  {
    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
  }
  //PARSEFNL ON MOBILE DEVICE
  //****************************************************************************************
  /*
        Parse Function
        -parses fnl file
        -stores information in an obj of type "fnl"
  */
  //****************************************************************************************
  public returnClass parseFnl(String fname)
  {
    returnClass r2 = new returnClass();
    try
    {
      DataInputStream textFileStream = new DataInputStream(getAssets().open(String.format(fname)));
      String seperated[];
      Scanner myReader = new Scanner(textFileStream);
      int j = 0;
      fnlData fnl = new fnlData();
      fnl.lx = new Vector();
      fnl.ly = new Vector();
      fnl.hx = new Vector();
      fnl.hy = new Vector();
      fnl.id = new Vector();

      while (myReader.hasNextLine())
      {
        String data = myReader.nextLine();
        seperated = data.split(":");

        for (String a : seperated)
        {
          switch (j)
          {
            case 0:
              fnl.lx.add(Double.parseDouble(a));
              //System.out.println("//lx: " + fnl.lx.get(0)); //PRINT DEBUG
              j++;
              break;
            case 1:
              fnl.ly.add(Double.parseDouble(a));
              //System.out.println("//ly: " + fnl.ly.get(0)); //PRINT DEBUG
              j++;
              break;
            case 2:
              fnl.hx.add(Double.parseDouble(a));
              //System.out.println("//hx: " + fnl.hx.get(0)); //PRINT DEBUG
              j++;
              break;
            case 3:
              fnl.hy.add(Double.parseDouble(a));
              //System.out.println("//hx: " + fnl.hy.get(0)); //PRINT DEBUG
              j++;
              break;
            case 4:
              //ignore the 0
              j++;
              break;
            case 5:
              fnl.id.add(Double.parseDouble(a));
              //System.out.println("//id: " + fnl.id.get(0)); //PRINT DEBUG
              j = 0;
              break;
            default:
              System.out.println("error found parsing fnl");
          }
        }
      }
      r2.f = fnl;
      myReader.close(); //close file
    }
    catch (IOException e)
    {
      System.out.println("Caugh Exception in parseFnl");
      System.out.println("error with file-start\n");
      e.printStackTrace();
      System.out.println("error with file-end\n");
    }
    return r2; //return obj
  }
  //****************************************************************************************
  /*
        Primary Function
        -finds the closest point in cluster to current trajectory position
        -returns array with x index0, y index1 representing closest point in cluster
    */
  //FIND CLOSEST ON MOBILE DEVICE
  //****************************************************************************************
  public static double[] findClosest(coordinates c, double xVal, double yVal)
  {

    double ret[] = new double[2];
    double closestX = c.x.get(0);
    double closestY = c.y.get(0);
    double closestDistance;

    closestDistance = dist(xVal, yVal, closestX, closestY);

    //Sequence through Vector
    for(int i = 0; i < c.x.size(); i++)
    {
      double distT = dist(xVal, yVal, c.x.get(i), c.y.get(i));

      if(distT < closestDistance && distT != 0.0)
      {
        closestDistance = distT;
        closestX = c.x.get(i);
        closestY = c.y.get(i);
      }
    }
    System.out.println("");
    System.out.println("CURRENT:"  +  xVal + "," + yVal);
    System.out.println("CLOSEST:"  + closestX + "," + closestY);
    System.out.println("");
    ret[0] = closestX;
    ret[1] = closestY;
    return ret;
  }
  //****************************************************************************************
  /*
    Helper Function
    -euclidean computation
    -returns distance between points
  */
  //****************************************************************************************
  public static double dist(double x1, double y1, double x2, double y2)
  {
    double ret;
    double x = Math.pow(x2-x1, 2);
    double y = Math.pow(y2-y1, 2);
    ret = Math.sqrt(x + y);
    return ret;

  }
}