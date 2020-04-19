
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.Scanner;

import myapplication.coordinates;
import myapplication.returnClass;

@SuppressWarnings("unchecked")
public class server
{

  public static void main(String[] args) throws IOException
  {
    int index = 0;
    int pingid = 0;//true
    int sizeSend = 0;
    returnClass clust = new returnClass();
    coordinates cc = new coordinates();

    cc.x = new Vector();
    cc.y = new Vector();
    cc.y = new Vector();

    cc.topRCornerX = new Vector();
    cc.topRCornerY = new Vector();
    cc.botLCornerX = new Vector();
    cc.botLCornerY = new Vector();


    //PARSE FILE
    clust = parseArff("test.txt", index); //1
    int clusterNum = clust.size;
    System.out.println("//debug cluster num : " + clusterNum);

    //clust = parseArff("test.txt", index);
//    double test = clust.c.x.get(0);      //PRINT DEBUG
//    System.out.println("test: " + test); //PRINT DEBUG

    //size of is comparison of how big index should be to loop around
//    clust.c.sizeOf = clust.size;
//    System.out.println("expect 2 clust.size " + clust.size);
//    System.out.println("expect 2 clust.c.sizeof" + clust.c.sizeOf);

    //Declarations:
    Socket clientSocket = null;
    ServerSocket serverSocket = null;

    try
    {
      serverSocket = new ServerSocket(4445);
      System.out.println("Server Online");
      clientSocket = serverSocket.accept();
    }

    catch (Exception e)
    {
      e.printStackTrace();
    }

    //read & display the message
    //BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
    DataInputStream din = new DataInputStream(clientSocket.getInputStream());
    ObjectOutputStream objo = new ObjectOutputStream(clientSocket.getOutputStream());
    ObjectInputStream obji = new ObjectInputStream(clientSocket.getInputStream());

    //Scanner in1 = new Scanner(clientSocket.getInputStream());
    // String mes;

    String str = "";

    while (!str.equals("end"))
    {
      //PING:
      System.out.println("");
      System.out.println("Waiting on Client Request");
      str = din.readUTF();
      pingid= Integer.parseInt(str);


      if(pingid == 0)
      {
        if (index < clusterNum) {
          System.out.println("//INCREMENT INDEX");
          index++;
          System.out.println("<>INDEX INCR<> " + index);
        }
        if(index == clusterNum)
            {
            System.out.println("//RESET INDEX");
            index = 0;
            System.out.println("<>INDEX RESET<> " + index);
            }
        }
      if(sizeSend == 0)
      {
        index = 0;
        sizeSend = 1;
      }

        //Send Object
        System.out.println("//%%INDEX VAL" + index);
        System.out.println("//%%ARRAY SIZE" + clust.ar.length);
        System.out.println("Sending Object...");
        System.out.println("TESTA " + clust.ar[index].x);
        System.out.println("TESTB " + clust.ar[index].y);
        objo.writeObject(clust.ar[index]);
        objo.flush();
        pingid = 1;
      }

    }//end loop while server running
  //end main
  //****************************************************************************************
  public static returnClass parseArff(String fname, int index)
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
//      if(index > num)
//      {
//        //loop around
//        index = 0;
//      }
      for (int i = 0; i < num; i++)
      {
        cluster[i] = new coordinates();
        cluster[i].x = new Vector();
        cluster[i].y = new Vector();
        cluster[i].y = new Vector();

        cluster[i].topRCornerX = new Vector();
        cluster[i].topRCornerY = new Vector();
        cluster[i].botLCornerX = new Vector();
        cluster[i].botLCornerY = new Vector();
      }
      r1.size = num;


      String nll;
      String xi = "0";
      String yi = "0";
      String ci = "0";
      char t;
      int ind;

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
        cluster[i].topRCornerX.add(Collections.max(cluster[i].x)); //RIGHT BOUND
        cluster[i].topRCornerY.add(Collections.max(cluster[i].y)); //LUPPER BOUND
        System.out.println("//topRCorner: " + i + ":" + cluster[i].topRCornerX.get(0) + "," + cluster[i].topRCornerY.get(0)); //PRINT DEBUG
        cluster[i].botLCornerX.add(Collections.min(cluster[i].x)); //LEFT BOUND
        cluster[i].botLCornerY.add(Collections.min(cluster[i].y)); //LOWER BOUND
        System.out.println("//botLCorner: " + i + ":" + cluster[i].botLCornerX.get(0) + "," + cluster[i].botLCornerY.get(0)); //PRINT DEBUG
      }
      /*
        Set Values of SuperMBR
      */
      //TODO %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

      //set return values
      //System.out.println("//internal size" + r1.size);
      //r1.size = size;

      //Copy to Return Object
      r1.ar = new coordinates[num];
      r1.ar = cluster.clone();

      //r1.c = cluster[index];
      //r1.c.index = index;
      //r1.index = index;
    }
    catch (IOException e)
    {
      System.out.println("error with file\n");
      e.printStackTrace();
    }

    return r1; //return obj
  }
  //****************************************************************************************
  /*
        Helper Function
        -returns the number of clusters by parsing specific line of input file
    */
  //****************************************************************************************
  public static int numCluster(String iN)
  {
    int start;
    int end;
    int index;
    int num = 0;
    /*
         Finds last instance of } and first instance of {
         and increments by 7 to find the value at "# {cluster #....}
         until it reaches the final cluster # in the string
         */
    end = iN.lastIndexOf('}', iN.length());
    start = iN.lastIndexOf('{', iN.length());

    index = start + 8;
    /*
         Increments by 9 to find next cluster # until reaching the last cluster # end-1
         */
    while (index != (end - 1))
    {
      num++;
      index = index + 9;
    }
    num = num + 1;
    //System.out.println("//Debug(numCluster): " + num);//Print Debug
    return num;
  }
}
