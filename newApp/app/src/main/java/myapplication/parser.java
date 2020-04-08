package parserFile;
//f1
/*
  Backend:
  Java program that parses a file for (x,y) datapoints and stores them in a
  coordinates object that has two parameters:
                                            vector of type double x
                                            vector of type double y
  The program is also able to find the closest point to a given point
*/
//Headers:
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.*;
import android.util.Log;
import java.util.ArrayList;
import android.content.res.AssetManager;

import java.util.Scanner;
@SuppressWarnings("unchecked")

public class parser
{
  public static void main()
  {
    //TODO: TRANSFER TO REAL MAIN
    //Return Creations
//    retClass.returnClass clust = new retClass.returnClass();
//    retClass.returnClass traj = new retClass.returnClass();
//    /*
//    NOTE: Main is called only to test in terminal
//    */
//    //START PROGRAM
//    long startT = System.currentTimeMillis();
//
//    //Tests:
//    //String fileName = "newclusterTest.arff";
//    String fileName = "test.txt";
//    clust = parseArff(fileName);
//    System.out.println("R-AFTER" + retClass.returnClass.size);               //PRINT DEBUG
//    //System.out.println("array test: " + clust.c[0].x.get(1)); //PRINT DEBUG
//    //String fileName2 = "trajectory.fnl";
//    String fileName2 = "testJ.txt";
//    traj = parseFnl(fileName2);
//    //System.out.println("fnl " + traj.f.lx.get(0)); //PRINT DEBUG
//
////    int s = (int)traj.f.id.size();
////    System.out.println("fnl id " + s); //PRINT DEBUG

    //TODO:
    /*
      1) SIMUATE DELAY
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
//    long endT = System.currentTimeMillis();
//    long totalT = endT - startT;
//    System.out.println("running time: " + totalT); //PRINT DEBUG
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////

  //****************************************************************************
  /*
    Function to find the closest point to a given point
  */
  public static double[] findClosest(coordObj.coordinates c, int arSize, double xVal, double yVal)
  {
    double ret[] = new double[2];
    double pointX, pointY;
    double closestX = 0.0;
    double closestY = 0.0;
    double tempDistX = 10000.00;
    double tempDistY = 10000.00;
    //User input NOTE: convert to GUI*
    // Scanner inPut = new Scanner(System.in);
    // System.out.println("Enter your coordinate x"); //TEMP
    // pointX = inPut.nextDouble();
    // System.out.println("Enter your coordinate y"); //TEMP
    // pointY = inPut.nextDouble();
    //REMOVE
    pointX = xVal;
    pointY = yVal;
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
    //Prints the next closest point
    ret[0] = closestX;
    ret[1] = closestY;
    return ret;
    //System.out.println("closest x,y: " + closestX + "," + closestY);
  }
  //****************************************************************************
  public static int numCluster(String iN)
  {
    int start;
    int end;
    int index;
    int num = 0;
    char ch;
    end = iN.lastIndexOf('}', iN.length());
    //System.out.println("end:" + end);
    start = iN.lastIndexOf('{', iN.length());
    //System.out.println("start" + start);

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
    public static retClass.returnClass parseFnl(String fname)
    {
      retClass.returnClass r2 = new retClass.returnClass();
      try
      {
        String seperated[];
        File myObj = new File(fname);
        Scanner myReader = new Scanner(myObj);
        int j = 0;
        fnlD.fnlData fnl = new fnlD.fnlData();
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

} //end class
