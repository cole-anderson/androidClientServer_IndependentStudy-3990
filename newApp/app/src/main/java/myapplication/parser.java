package parserFile;

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
  public static double tester(double outout)
  {
    return outout;
  }
  /*
    Function to find the closest point to a given point
  */
  public static void findClosest(coordObj.coordinates c, int arSize)
  {
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
    pointX = 509.284;
    pointY = 980.01;
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
    System.out.println("closest x,y: " + closestX + "," + closestY);
  }
  //****************************************************************************
  /*
    Function to parse arff file to determine the x,y coordinates
    and store them in a respective data structures per cluster
  */
  public static void parseFile(String fname)
  {
    int num = 0;
    int arraySize = 0;
    coordObj.coordinates cluster = new coordObj.coordinates();
    cluster.x = new Vector();
    cluster.y = new Vector();
    try
    {
      String seperated[];
      int size = 25;
      int i = 0;
      int j = 0;
      coordObj.coordinates one[] = new coordObj.coordinates[size];
      File myObj = new File(fname);
      Scanner myReader = new Scanner(myObj);
      myReader.useDelimiter(",");
      //LOOP
      while (myReader.hasNextLine())
      {

        String data = myReader.nextLine();
        seperated = data.split(",");
        for (String a : seperated)
        {
          if (j % 2 == 0)
          {
            cluster.x.add(Double.parseDouble(a));
            System.out.println("x" + cluster.x.get(i));
          }
          else
          {
            cluster.y.add(Double.parseDouble(a));
            System.out.println("y" + cluster.y.get(i));
          }
          j++; //iterate between x and y
        }
        i++;
        //iteration through coordinates
      }                 //end file reading while
      myReader.close(); //close file
      arraySize = i;
    }
    catch (FileNotFoundException e)
    {
      System.out.println("error with file\n");
      e.printStackTrace();
    }
    findClosest(cluster, arraySize);
  }
} //end class