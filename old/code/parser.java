/*
  Backend:
  Java program that parses a file for (x,y) datapoints and stores them in a
  coordinates object that has two parameters:
                                            vector of type double x
                                            vector of type double y
  The program is also able to find the closest point to a given point
*/
//Headers:
//
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.lang.Boolean;
import java.lang.String;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("unchecked")

public class parser
{
  public static void main(String[] args)
  {
    //Return Creations
    returnClass clust = new returnClass();
    returnClass traj = new returnClass();
    /*
    NOTE: Main is called only to test in terminal
    */
    //START PROGRAM
    long startT = System.currentTimeMillis();

    //Tests:
    //String fileName = "newclusterTest.arff";
    String fileName = "test.txt";
    clust = parseArff(fileName);
    //System.out.println("R-AFTER" + clust.size);               //PRINT DEBUG
    //System.out.println("array test: " + clust.c[0].x.get(1)); //PRINT DEBUG
    //String fileName2 = "trajectory.fnl";
    String fileName2 = "testJ.txt";
    traj = parseFnl(fileName2);
    //System.out.println("fnl " + traj.f.lx.get(0)); //PRINT DEBUG

    coordinates clusterT = new coordinates();
    clusterT.x = new Vector();
    clusterT.x = new Vector();

    clusterT.x = clust.c[0].x;
    //int ss = clust.c.length;

    //System.out.println("//cluster check" + clusterT.x);
    System.out.println("//size testtttt" + clust.size);
    //System.out.println("//array size test" + ss);

    int s = (int)traj.f.id.size();
    System.out.println("fnl id " + s); //PRINT DEBUG

    //TODO:
    /*
      1)
    */
    // boolean pause = true;
    // for (int i = 0; i < s; i++)
    // {
    //   if (pause == true)
    //   {
    //     try
    //     {
    //       Thread.sleep(2000);
    //       pause = false;
    //     }
    //     catch (InterruptedException e)
    //     {
    //       e.printStackTrace();
    //     }
    //   }
    //   else
    //   {
    //     findClosest(clust.c[0], traj.f, i);
    //     pause = true;
    //   }
    // }

    //END PROGRAM
    long endT = System.currentTimeMillis();
    long totalT = endT - startT;
    System.out.println("running time: " + totalT); //PRINT DEBUG
  }
  //****************************************************************************
  /*
    Function to find the closest point in cluster to current location
    (COMPLETE)
  */
  //****************************************************************************
  public static void findClosest(coordinates c, fnlData f, int incr)
  {
    int arSize = c.x.size();
    double pointX = f.lx.get(incr);
    double pointY = f.ly.get(incr);
    System.out.println("//Current Coordinate Location:"
                       + "(" + pointX + "," + pointY + ")");
    double closestX = 0.0;
    double closestY = 0.0;
    double tempDistX = 10000.00;
    double tempDistY = 10000.00;
    //END
    /*
    For loop that linearly searches our array for the point
    "closest" (closestX, closestY) to our "point" (pointX, pointY) point.
    */
    for (int i = 0; i < arSize; i++)
    {
      if (Math.abs(c.x.get(i) - pointX) <= tempDistX)
      {
        if (Math.abs((c.y.get(i)) - pointY) <= tempDistY)
        {
          closestX = c.x.get(i);
          closestY = c.y.get(i);
          tempDistX = Math.abs(c.x.get(i) - pointX);
          tempDistY = Math.abs(c.y.get(i) - pointY);
        }
      }
    }
    System.out.println("//Closest Coordinate In Cluster: "
                       + "(" + closestX + "," + closestY + ")"); //PRINT DEBUG
  }
  //****************************************************************************
  /*
    Function to determine the number of clusters contained in arff file
    (COMPLETE)
  */
  //****************************************************************************
  public static int numCluster(String iN)
  {
    int start;
    int end;
    int index;
    int num = 0;
    char ch;
    end = iN.lastIndexOf('}', iN.length());
    //System.out.println("end:" + end);//PRINT DEBUG
    start = iN.lastIndexOf('{', iN.length());
    //System.out.println("start" + start);//PRINT DEBUG
    index = start + 7;
    while (index != (end - 1))
    {
      num++;
      index = index + 5;
    }
    return num;
  }
  //****************************************************************************
  /*
    Function to parse fnl file to determine the trajectory of user
  */
  //****************************************************************************
  public static returnClass parseFnl(String fname)
  {
    returnClass r2 = new returnClass();
    try
    {
      String seperated[];
      File myObj = new File(fname);
      Scanner myReader = new Scanner(myObj);
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
            //System.out.println("error found parsing fnl");
          }
        }
      }
      r2.f = fnl;
      myReader.close(); //close file
    }
    catch (FileNotFoundException e)
    {
      System.out.println("error with file\n");
      e.printStackTrace();
    }
    return r2;
  }
  //****************************************************************************
  /*
    Function to parse arff file to determine the x,y coordinates
    and store them in a respective data structures per cluster
  */
  //****************************************************************************
  public static returnClass parseArff(String fname)
  {
    returnClass r1 = new returnClass();
    try
    {
      int lineID = 0;
      boolean exitCond = false;
      String seperated[];
      String last = "";
      int j = -1;
      int inc = 0;
      int num = 0;
      File myObj = new File(fname);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine() && exitCond == false)
      {
        String parseTop = myReader.nextLine();
        if (parseTop.length() == 0)
          inc++;
        if (inc == 2)
        {
          num = numCluster(last);
          inc++;
        }
        if (inc == 3)
        {
          exitCond = true;
        }
        last = parseTop;
      }
      //Cluster Creation
      coordinates cluster[] = new coordinates[num];
      for (int i = 0; i < num; i++)
      {
        cluster[i] = new coordinates();
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
      //
      //
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
      //set return values
      System.out.println("//internal size" + r1.size);
      //r1.size = size;
      r1.c = cluster;
    }
    catch (FileNotFoundException e)
    {
      System.out.println("error with file\n");
      e.printStackTrace();
    }
    return r1; //return obj
  }
} //end class
