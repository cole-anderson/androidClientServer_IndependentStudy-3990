/*
  File Parser
  .cvs .ariff
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
    //Declarations:
    //USER INPUT
    Scanner inPut = new Scanner(System.in);
    System.out.println("Enter .CSV format file name");
    String fileName = inPut.nextLine();
    if (fileName.charAt(fileName.length() - 4) == '.' || fileName.charAt(fileName.length() - 5) == '.')
    {
      System.out.println(".csv or ariff extension present in string\n"); //REMOVE
      parseFile(fileName);                                               //parse the csv file
    }
    else
    {
      System.out.println(".csv extension not present in string\n"); //REMOVE
      parseFile(fileName);
    }
  }
  /*

  */
  public static void findClosest(coordinates c, int arSize)
  {
    //NOTE : MAKE COMPARISION BETWEEN OBJECTS NOT BETWEEN Double AND OBJECTS
    // Scanner inPut = new Scanner(System.in);
    // System.out.println("Enter your coordinate x"); //TEMP
    // Double pointX = inPut.nextDouble();
    // System.out.println("Enter your coordinate y"); //TEMP
    // Double pointY = inPut.nextDouble();
    // System.out.println("x" + pointX + "y" + pointY); //TEMP
    // double closestX = 0;
    // double closestY = 0;
    // double tempDistX = 10000000;
    // double tempDistY = 10000000;
    // /*
    // For loop that linearly searches our array for the point
    // "closest" (closestX, closestY) to our "point" (pointX, pointY) point.
    // */
    // // for (int i = 0; i < arSize; i++)
    // // {
    // Double tValueX = c.x.get(0);
    // Double tValueY = c.y.get(0);
    // System.out.println("x :)" + tValueX + "y :)" + tValueY);
    //   if (Math.abs((c.x.get(i)) - pointX) <= tempDistX)
    //   {
    //     if (Math.abs((c.y.get(i)) - pointY) <= tempDistY)
    //     {
    //       closestX = c.x.get(i);
    //       closestY = c.y.get(i);
    //       tempDistX = Math.abs(c.x.get(i) - pointX);
    //       tempDistY = Math.abs(c.y.get(i) - pointY);
    //     }
    //   }
    // }
    // //Prints the next closest point
    // System.out.println("closest x,y: " + closestX + "," + closestY);
  }

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
  //BUFFERFILE PARSER
  // public static void parseBuffer(String fname)
}
