/*
  Server that
  -Reads arff and fnl files
  -Creates MBR and Super MBR
  -Gets pinged when user is  outside MBR and requests new information
*/

import java.io.*;
import java.net.*;
import java.util.*;

@SuppressWarnings("unchecked")

class server
{
  public static void main(String args[]) throws Exception
  {
    //MY IMP TODO:
    // ServerSocket ss = new ServerSocket(3333);
    // Socket s = ss.accept();
    // ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
    //
    // //Coordinates
    // coordinates c = new coordinates();
    // c.x = new Vector();
    // c.y = new Vector();
    // c.x.add(15.0);
    // c.x.add(25.0);
    // c.y.add(69.0);
    // c.y.add(88.0);
    //
    // os.writeObject(c);

    //we read the entire file and create array of coordinates**
    int num = 2;
    coordinates cluster[] = new coordinates[num]; //num
    cluster[0] = new coordinates();
    cluster[0].x = new Vector();
    cluster[0].y = new Vector();
    cluster[1] = new coordinates();
    cluster[1].x = new Vector();
    cluster[1].y = new Vector();
    int i = 0;
    //THERE IMP::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    ServerSocket ss = new ServerSocket(3333);
    Socket s = ss.accept();
    DataInputStream din = new DataInputStream(s.getInputStream());
    //DataOutputStream dout = new DataOutputStream(s.getOutputStream());
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

    String str = "", str2 = "";

    double value = 33.0;
    while (!str.equals("stop"))
    {
      str = din.readUTF();
      if (str.equals("ping"))
      {
        if (i < num)
        {
          System.out.println("//index b4= " + i);
          System.out.println("//CLIENT REQUEST ACK sending obj");

          cluster[i].x.add(value);

          os.writeObject(cluster[i]);
          i++;
          value = 69.0;
          System.out.println("//index after = " + i);
          //dout.writeUTF(str2);
          //dout.flush();
        }
        else
        {
          System.out.println("//out of array range exception reset to zero");
          i = 0;
          System.out.println("//index b4= " + i);
          System.out.println("//CLIENT REQUEST ACK sending obj");

          cluster[i].x.add(value);

          os.writeObject(cluster[0]);
          i++;
          value = 69.0;
          System.out.println("//index after = " + i);
        }
      }

      //simulate ping
    }
    din.close();
    s.close();
    ss.close();
  }
}
