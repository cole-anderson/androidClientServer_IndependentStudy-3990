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
      System.out.println("n.csv extension not present in string\n"); //REMOVE
      parseFile(fileName);
    }
  }
  /*

  */
  // public static void findClosest(//data structure)
  // {
  //   //
  // }

  /*
    Function to parse the csv or arff file to determine the x,y coordinates
    and store them in a respective data structures per cluster
  */
  public static void parseFile(String fname)
  {
    int num = 0;
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
        System.out.println("//ITERATION" + i + "\n"); //DEBUG
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
        i++; //iteration through coordinates
      }
      myReader.close(); //close file
    }
    catch (FileNotFoundException e)
    {
      System.out.println("error with file\n");
      e.printStackTrace();
    }
  }
  //BUFFERFILE PARSER
  // public static void parseBuffer(String fname)
  // {
  //   String[] input;
  //   double x;
  //   double y;
  //   try
  //   {
  //     System.out.println("filename is: " + fname); //REMOVE
  //     //PARSE THE BUFFER FILE
  //     File myObj = new File(fname);
  //     Scanner myReader = new Scanner(myObj);
  //     while (myReader.hasNextLine())
  //     {
  //       //PARSE FILE FOR # OF CLUSTERS
  //
  //       //REMOVE *----------------------------------
  //
  //       String data = myReader.nextLine();
  //       System.out.println(data); //DEBUG
  //       //-----------------------------------------*
  //     }
  //     myReader.close();
  //   }
  //   catch (FileNotFoundException e)
  //   {
  //     System.out.println("error with file\n");
  //     e.printStackTrace();
  //   }
  // }
  // public static void mbrCreate()
  // {
  //   //
  // }
}
