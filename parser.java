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
    //TIME CHECK
    long startT = System.currentTimeMillis();
    //USER INPUT
    // Scanner inPut = new Scanner(System.in);
    // System.out.println("Enter .CSV format file name");
    // String fileName = inPut.nextLine();
    // if (fileName.charAt(fileName.length() - 4) == '.' || fileName.charAt(fileName.length() - 5) == '.')
    // {
    //   System.out.println(".csv or ariff extension present in string\n"); //REMOVE
    //   parseFile(fileName);                                               //parse the csv file
    // }
    // else
    // {
    //   System.out.println(".csv extension not present in string\n"); //REMOVE
    //   parseFile(fileName);
    // }
    parseFile("mainnew.csv");

    long endT = System.currentTimeMillis();
    long totalT = endT - startT;
    System.out.println("running time: " + totalT);
  }
  //****************************************************************************
  /*
    Function to find the closest point to a given point
  */
  public static void findClosest(coordinates c, int arSize)
  {
    Double pointX, pointY;
    Double closestX = 0.0;
    Double closestY = 0.0;
    Double tempDistX = 10000.00;
    Double tempDistY = 10000.00;
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
    Function to parse the csv or arff file to determine the x,y coordinates
    and store them in a respective data structures per cluster
  */
  public static void parseFile(String fname)
  {
    int num = 0;
    int arraySize = 0;
    coordinates cluster = new coordinates();
    cluster.x = new Vector();
    cluster.y = new Vector();
    try
    {
      String seperated[];
      int size = 25;
      int i = 0;
      int j = 0;
      coordinates one[] = new coordinates[size];
      File myObj = new File(fname);
      Scanner myReader = new Scanner(myObj);
      myReader.useDelimiter(",");
      //LOOP
      while (myReader.hasNextLine())
      {
        // System.out.println("//ITERATIOdN" + i + "\n"); //DEBUG
        String data = myReader.nextLine();
        seperated = data.split(",", 100);
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
        i++;            //iteration through coordinates
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
