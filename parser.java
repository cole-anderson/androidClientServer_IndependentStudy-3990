/*
  File Parser
  .cvs .ariff
*/
//Headers:
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class parser
{
  public static void main(String[] args)
  {
    //USER INPUT
    Scanner inPut = new Scanner(System.in);
    System.out.println("Enter .CSV format file name");
    String fileName = inPut.nextLine();
    if (fileName.charAt(fileName.length() - 4) == '.' || fileName.charAt(fileName.length() - 5) == '.')
    {
      System.out.println(".csv or ariff extension present in string\n"); //REMOVE
      parseCSV(fileName);                                                //parse the csv file
    }
    else
    {
      System.out.println("n.csv extension not present in string\n"); //REMOVE
      //parseBuffer(fileName);
    }
  }
  //CSV ARIFF FILE PARSER
  public static void parseCSV(String fname)
  {
    //declarations:
    object largestX = new object();
    object largestY = new object();
    object currentP = new object();

    try
    {
      System.out.println("filename is: " + fname); //REMOVE
      //PARSE THE CSV FIL
      File myObj = new File(fname);
      Scanner myReader = new Scanner(myObj);
      //LOOP
      while (myReader.hasNextLine())
      {
        //TODO: PARSE FILE FOR X AND Y
        //LLL DELIMITER FOR ,
        // System.out.println("startline"); //DEBUG
        // String data = myReader.nextLine();
        // System.out.println(data); //DEBUG
      }
      myReader.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("error with file\n");
      e.printStackTrace();
    }
  }
  //BUFFERFILE PARSER
  public static void parseBuffer(String fname)
  {
    String[] input;
    double x;
    double y;
    try
    {
      System.out.println("filename is: " + fname); //REMOVE
      //PARSE THE BUFFER FILE
      File myObj = new File(fname);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine())
      {
        //PARSE FILE FOR # OF CLUSTERS

        //REMOVE *----------------------------------

        String data = myReader.nextLine();
        System.out.println(data); //DEBUG
        //-----------------------------------------*
      }
      myReader.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("error with file\n");
      e.printStackTrace();
    }
  }
  // public static void mbrCreate()
  // {
  //   //
  // }
}
