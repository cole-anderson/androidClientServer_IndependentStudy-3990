
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
import java.io.PrintWriter;
import java.lang.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;


//import static myapplication.client.ping;
//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private Socket client;
  private Boolean ping = false;
  DataInputStream din;
  private DataOutputStream dout;
  private PrintWriter pw;
  private String message = "false";
  Boolean rect = false;

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

//    //String fileName2 = "trajectory.fnl";
//    String fileName2 = "testJ.txt";
//    traj = parseFnl(fileName2); //TODO FIX**
//    System.out.println("fnl" + traj.f.hx);
    //end delete
    /*
    Submit Button
    */
    findPoint = (Button) findViewById(R.id.findPoint);
    findPoint.setOnClickListener(new View.OnClickListener() {
      int loc = 0;
      int j = 0;

      @Override
      public void onClick(View v) {
      System.out.println("CLICK BUTTON");
        //TODO IF INSIDE MBR: (HAVE A BOOL VALUE)

//          if (rect == false)
         // {
            new Thread(new Runnable()
            {
              @Override
              public void run()
              {
                System.out.println("enter threading");
                try {
                  String str1 = "";
                  String str2 = "";
                  System.out.println("got hereTRY1");
                  String hostID = "10.0.2.2";
                  int portID = 4445;
                  if(!ping)
                  {
                    client = new Socket(hostID, portID);
                    dout = new DataOutputStream(client.getOutputStream());
                    din = new DataInputStream(client.getInputStream());
                    ping = true;
                  }
                  System.out.println("Writing");
                  str1 = "hello";
                  dout.writeUTF(str1);
                  dout.flush();

                  System.out.println("Reading");
                  str2 = din.readUTF();
                  System.out.println("From Server: " + str2);
                  str2 = "";



//                  pw = new PrintWriter(client.getOutputStream());
//                  pw.write(message);
//                  pw.flush();
//                  pw.close();
                  //client.close();
                }
                catch (IOException e) {
                  System.out.println("Exception Caught @114");
                  e.printStackTrace();
                }
              }
            }).start();

            System.out.println("exited threading");


          //}

        //TODO OUTPUT CLOSEST: (WHILE INSIDE MBR)
//        else
//        {
//
//        }




        //showToast("outside");

        //Loops through trajectory
//            if (loc < traj.f.hx.size() - 1)
//              loc++;
//            else
//              loc = 0;
//
//            //for output
//            double outVal[] = new double[2];
//            double outputAr[] = new double[2];
//            //System.out.println("length" + outVal.length);
//            int sizecc = 0;
//            int j = 0;

        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        //TODO: //TEMP VALUES FOR TESTING PURPOSES:

        //          coordinates clusterT = new coordinates();
        //          clusterT.x = new Vector();
        //          clusterT.y = new Vector();
        //
        //          clusterT.x.add(0, 1.0);
        //          clusterT.y.add(0, 1.0);
        //
        //          clusterT.x.add(1, 250.0);
        //          clusterT.y.add(1, 250.0);
        //
        //          clusterT.x.add(2, 10.0);
        //          clusterT.y.add(2, 10.0);
        //
        //          clusterT.x.add(3, 20.0);
        //          clusterT.y.add(3, 20.0);
        //
        //          clusterT.topRCornerX = 250;
        //          clusterT.topRCornerY = 250;
        //          clusterT.botLCornerX = 1.0;
        //          clusterT.botLCornerY = 1.0;
        //
        //          System.out.println("//main test" + clusterT.x.get(0));//DELETE
        //          System.out.println("//main test" + clusterT.x.get(1));//DELETE
        //          System.out.println("//main test" + clusterT.x.get(2));//DELETE
        //          System.out.println("//main test" + clusterT.x.get(3));//DELETE
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

        /*
         USER INPUT
         TODO: IMPLEMENT ARROWS INSTEAD OF ENTRIES** fnl data instead of temp points
        */
        //User input of x and y values
        //        tempPointx = Double.parseDouble(xCoordinateInput.getText().toString());
        //        tempPointy = Double.parseDouble(yCoordinateInput.getText().toString());

        //If statements to avoid app crash if submit button is pressed without user input
        //        if (xCoordinateInput.getText().toString().length() == 0)
        //        {
        //          xCoordinateInput.setText("0");
        //        }
        //        if (yCoordinateInput.getText().toString().length() == 0)
        //        {
        //          yCoordinateInput.setText("0");
        //        }
        /*
            MBR COMPARISON TODO**
        */
//            tempPointx = traj.f.lx.get(loc);
//            tempPointy = traj.f.ly.get(loc);
//            System.out.println("//loc: " + loc + tempPointx + tempPointy);
//
//            coordinates closeC = new coordinates();
//            closeC.x = new Vector();
//            closeC.y = new Vector();
//            closeC.x.clear();
//            closeC.y.clear();

        //TODO FIX FIX FIX FIX IFX IFX
        // outputAr = findClosest(closeC, closeC.x.size(), tempPointx, tempPointy);
//
//          //        if(tempPointx < clust.c[j].topRCornerX.get(j) && tempPointy < clust.c[j].topRCornerY.get(j) && tempPointx > clust.c[j].botLCornerX.get(j) && tempPointy > clust.c[j].botLCornerY.get(j))
//          //        {
//          //            showToast("INSIDE Cluster " + j + "MBR");
//          //        }
//
//          if (tempPointx > clust.c[j].topRCornerX.get(j) || tempPointy > clust.c[j].topRCornerY.get(j)) {
//            j++;
//          }
//          //showToast("INSIDE Cluster " + j + "MBR");
//
//          System.out.println("//variety: j" + j);
//          textView2.setText(String.format("(%s,%s)", tempPointx, tempPointy));
//          outputNum.setText(String.format("(%s,%s)", outputAr[0], outputAr[1]));
//
//          //old
//          if (clust.c[0].topRCornerX.get(0) < tempPointx) {
//            showToast("outside MBR RIGHT"); //Print Debug
//            outputNum.setText("OUTSIDE MBR");
//          } else if (clust.c[0].topRCornerY.get(0) < tempPointy) {
//            showToast("outside MBR TOP"); //Print Debug
//            outputNum.setText("OUTSIDE MBR");
//          } else if (clust.c[0].botLCornerX.get(0) > tempPointx) {
//            showToast("outside MBR LEFT"); //Print Debug
//            outputNum.setText("OUTSIDE MBR");
//          } else if (clust.c[0].botLCornerY.get(0) > tempPointy) {
//            showToast("outside MBR BOTTOM"); //Print Debug
//            outputNum.setText("OUTSIDE MBR");
//          }
//          //          //WHEN INSIDE MBR:
//          else {
//            //CURRENT OUTPUT
//            sizecc = clust.size; //TODO FIX
//            outVal = findClosest(clust.c[0], sizecc, tempPointx, tempPointy);
//            outputNum.setText(String.format("(%s,%s)", outVal[0], outVal[1]));
//
//
//          }
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
  public static double[] findClosest(coordinates c, int arSize, double xVal, double yVal)
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
  //TODO DELETE







  //TODO DELETE BECAUSE MOVED
  //****************************************************************************************
  /*
        Parse Function
        -parses arff file
        -stores information in an obj array of type "coordinates"
  */
  //****************************************************************************************
//  public returnClass parseArff(String fname)
//  {
//    returnClass r1 = new returnClass();
//    try
//    {
//      DataInputStream textFileStream = new DataInputStream(getAssets().open(String.format(fname)));
//      int lineID = 0;
//      boolean exitCond = false;
//      String seperated[];
//      String last = "";
//      int j = -1;
//      int inc = 0;
//      int num = 0;
//      Scanner myReader = new Scanner(textFileStream);
//      while (myReader.hasNextLine() && exitCond == false)
//      {
//        String parseTop = myReader.nextLine();
//        if (parseTop.length() == 0)
//          inc++;
//        if (inc == 2)
//        {
//          num = numCluster(last);
//          inc++;
//        }
//        if (inc == 3)
//        {
//          exitCond = true;
//        }
//        last = parseTop;
//      }
//      //Cluster Creation
//      coordinates cluster[] = new coordinates[num];
//      //THIS FOR LOOP FOR WHEN READING IN
//      for (int i = 0; i < num; i++)
//      {
//        cluster[i] = new coordinates();
//        cluster[i].x = new Vector();
//        cluster[i].y = new Vector();
//
//        cluster[i].topRCornerX = new Vector();
//        cluster[i].topRCornerY = new Vector();
//        cluster[i].botLCornerX = new Vector();
//        cluster[i].botLCornerY = new Vector();
//      }
//      r1.size = num;
//
//      String nll;
//      String xi = "0";
//      String yi = "0";
//      String ci = "0";
//      char t;
//      int ind;
//
//      while (myReader.hasNextLine())
//      {
//        String data = myReader.nextLine();
//        seperated = data.split(",");
//
//        for (String a : seperated)
//        {
//          switch (j)
//          {
//          case -1:
//            j++;
//            break;
//          case 0:
//            nll = a;
//            j++;
//            break;
//          case 1:
//            xi = a;
//            System.out.println("//x: " + xi); //Print Debug
//            j++;
//            break;
//          case 2:
//            yi = a;
//            System.out.println("//y: " + yi); //Print Debug
//            j++;
//            break;
//          case 3:
//            ci = a;
//            //parsing out the cluster num
//            t = ci.charAt(7);
//            System.out.println("?? " + t);
//            /*
//                            ACTUALLY INPUTTING DATA INTO OBJECTS
//                            */
//            ind = Integer.parseInt(String.valueOf(t));
//            System.out.println("?? " + ind);
//            cluster[ind].x.add(Double.parseDouble(xi));
//            cluster[ind].y.add(Double.parseDouble(yi));
//
//            //Print Debug
//            // if (ind == 0)
//            // {
//            //   size0 = cluster[0].x.size();
//            //   System.out.println("//x INCLUSTER: " + cluster[ind].x.get(size0 - 1));
//            //   System.out.println("//Y INCLUSTER: " + cluster[ind].y.get(size0 - 1));
//            //   System.out.println("//cluster num:" + ind);
//            // }
//            // else if (ind == 1)
//            // {
//            //   size1 = cluster[1].x.size();
//            //   System.out.println("//x INCLUSTER: " + cluster[ind].x.get(size1 - 1));
//            //   System.out.println("//Y INCLUSTER: " + cluster[ind].y.get(size1 - 1));
//            //   System.out.println("//cluster num:" + ind);
//            // }
//            //end
//
//            j = 0; //reset
//            break;
//          default:
//            System.out.println("error found parsing arff");
//          }
//        }
//      }
//      myReader.close(); //close file
//
//      /*
//            Set Values of Cluster MBRs
//            */
//      //Top Right Corner x,y and Bottom Left Corner x,y
//      for (int i = 0; i < num; i++)
//      { //RIGHT,UPPER,LEFT,LOWER
//        double temp;
//        temp = Collections.max(cluster[i].x);
//        System.out.println("temp" + temp);
//        cluster[i].topRCornerX.add(temp);
//        temp = Collections.max(cluster[i].y);
//        cluster[i].topRCornerY.add(temp);
//        //System.out.println("//topRCorner: " + i + ":" + cluster[i].topRCornerX + "," + cluster[i].topRCornerY); //Print Debug
//        temp = Collections.min(cluster[i].x);
//        cluster[i].botLCornerX.add(temp);
//        temp = Collections.min(cluster[i].y);
//        cluster[i].botLCornerY.add(temp);
//        //System.out.println("//botLCorner: " + i + ":" + cluster[i].botLCornerX + "," + cluster[i].botLCornerY); //PrintDebug
//      }
//      //System.out.println("//cluster1" + cluster[1].topRCornerX + "/" + cluster[1].topRCornerY);
//      /*
//            Set Values of SuperMBR
//            */
//      //TODO: CREATE SUPERMBRS**
//      //...........................
//
//      //Copy to Return Object
//      r1.c = new coordinates[num];
//      r1.c = cluster.clone();
//      //System.out.println("//Debug(parseArff): " + r1.c[0].x.get(0));//Print Debug
//    }
//    catch (IOException e)
//    {
//      System.out.println("Caugh Exception in parseArff");
//      System.out.println("error with file-start\n");
//      e.printStackTrace();
//      System.out.println("error with file-end\n");
//    }
//    return r1; //return obj
//  }
  //TODO DELETE BECAUSE MOVED
  //****************************************************************************************
  /*
        Helper Function
        -returns the number of clusters by parsing specific line of input file
    */
  //****************************************************************************************
//  public static int numCluster(String iN)
//  {
//    int start;
//    int end;
//    int index;
//    int num = 0;
//    /*
//        Finds last instance of } and first instance of {
//        and increments by 7 to find the value at "# {cluster #....}
//        until it reaches the final cluster # in the string
//        */
//    end = iN.lastIndexOf('}', iN.length());
//    start = iN.lastIndexOf('{', iN.length());
//
//    index = start + 8;
//    /*
//        Increments by 9 to find next cluster # until reaching the last cluster # end-1
//        */
//    while (index != (end - 1))
//    {
//      num++;
//      index = index + 9;
//    }
//    num = num + 1;
//    //System.out.println("//Debug(numCluster): " + num);//Print Debug
//    return num;
//  }
}
