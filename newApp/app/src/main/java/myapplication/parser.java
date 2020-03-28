
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
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("unchecked")

public class parser
{
  public static void main(String[] args)
  {
    //START PROGRAM
    long startT = System.currentTimeMillis();

    String fileName = "test.txt"; //FILENAME NOTE
    parseFile(fileName);

    //END PROGRAM
    long endT = System.currentTimeMillis();
    long totalT = endT - startT;
    System.out.println("running time: " + totalT);
  }
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
    Function to parse arff file to determine the x,y coordinates
    and store them in a respective data structures per cluster
  */
  public static void parseFile(String fname)
  {
    //coordObj.coordinates[]
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

      //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

      coordObj.coordinates cluster[] = new coordObj.coordinates[num];
      for (int i = 0; i < num; i++)
      {
        cluster[i] = new coordObj.coordinates();
        cluster[i].x = new Vector();
        cluster[i].y = new Vector();
        //System.out.println("i" + i);
      }

      // String seperated[];
      // int j = 0;
      // File myObj = new File(fname);
      // Scanner myReader = new Scanner(myObj);
      // myReader.useDelimiter(",");

      String nll;
      String xi = "0";
      String yi = "0";
      String ci = "0";
      char t;
      int ind;
      int size = 0;
      //int size0 = 0;
      //int size1 = 0;

      while (myReader.hasNextLine())
      {
        String data = myReader.nextLine();
        seperated = data.split(",");

        for (String a : seperated)
        {
          if (j == -1)
          {
            j++;
            continue;
          }
          /*
            Sequence of if statements that parse x, y and cluster
            number and then place those values in the proper
            object
            nll = identifier that i want to ignore
            xi = x coordinate
            yi = y coordinate
            ci = cluster number
          */
          else if (j == 0) //ignore identifer at start of each line
          {
            nll = a;
            //System.out.println("//nll:  " + nll);
            j++;
            continue;
          }
          else if (j == 1) //parses x
          {
            xi = a;
            //System.out.println("//x: " + xi);
            j++;
            continue;
          }
          else if (j == 2) //parses y
          {
            yi = a;
            //System.out.println("//y: " + yi); //
            j++;
            continue;
          }
          else if (j == 3) //parses cluster num (and assigns x,y,clusternum)
          {
            ci = a;
            //parsing out the cluster num
            t = ci.charAt(7);
            ind = Integer.parseInt(String.valueOf(t));
            /*
            ACTUALLY INPUTTING DATA INTO OBJECTS
            */

            //System.out.println("ind" + ind);

            ind = Integer.parseInt(String.valueOf(t));
            cluster[ind].x.add(Double.parseDouble(xi));
            cluster[ind].y.add(Double.parseDouble(yi));

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

            size = cluster[ind].x.size();
            System.out.println("//x INCLUSTER: " + cluster[ind].x.get(size - 1));
            System.out.println("//Y INCLUSTER: " + cluster[ind].y.get(size - 1));
            System.out.println("//cluster num:" + ind);

            lineID++;
            System.out.println("///line id: " + lineID);
            j = 0;
            continue;
          }
        }
      }
      myReader.close(); //close file



      //arraySize = i;
    }
    catch (FileNotFoundException e)
    {
      System.out.println("error with file\n");
      e.printStackTrace();
    }
    //findClosest(cluster, arraySize);

  }
} //end class
