/*
  File Parser
  .cvs .ariff
*/
//Headers:
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

public class parser
{
  public static void main(String[] args)
  {

    /* NOTE:TEMPORARY COMMENTED OUT START
    //Declarations:

    //USER INPUT
    Scanner inPut = new Scanner(System.in);
    System.out.println("Enter .CSV format file name");
    String fileName = inPut.nextLine();
    if (fileName.charAt(fileName.length() - 4) == '.' || fileName.charAt(fileName.length() - 5) == '.')
    {
      System.out.println(".csv or ariff extension present in string\n"); //REMOVE
      parseFile(fileName);                                                //parse the csv file
    }
    else
    {
      System.out.println("n.csv extension not present in string\n"); //REMOVE
      //-------------------------------*TODO
      //HERE IS WHERE I WILL TEST TXT FILE FOR CREATING MBR
      //-------------------------------*
    }
    */
    //NOTE:TEMPORARY COMMENTED OUT END

    //TEMP
    parseFile("test.txt");
  }
  /*

  */
  // public static void findClosest(//data structure)
  // {
  //   //
  // }

  /*
    Function to parse the csv or arff file to determine the x,y coordinates
    of our points and number/which cluster those points belong to
  */
  public static void parseFile(String fname)
  {
    try
    {
      String seperated[];
      int size = 25;
      int i = 0;
      int j = 0;
      object one[] = new object[size];
      File myObj = new File(fname);
      Scanner myReader = new Scanner(myObj);
      myReader.useDelimiter(",");
      //LOOP
      while (myReader.hasNextLine())
      {
        //TODO: PARSE FILE FOR X AND Y

        System.out.println("//ITERATION" + i + "\n"); //DEBUG
        String data = myReader.nextLine();
        seperated = data.split(",", 100);
        for (String a : seperated)
        {
          //creates new object
          one[i] = new object();
          if (j % 2 == 0)
          {
            one[i].x = Double.parseDouble(a);
            System.out.println("x" + one[i].x);
          }
          else
          {
            one[i].y = Double.parseDouble(a);
            System.out.println("y" + one[i].y);
          }
          j++; //iterate between x and y
        }
        i++; //iteration through object
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
