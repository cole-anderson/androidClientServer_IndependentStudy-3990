import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")

class client
{
  public static void main(String args[]) throws Exception
  {

    // Socket s = new Socket("localhost", 3333);
    // ObjectInputStream is = new ObjectInputStream(s.getInputStream());
    // //DataOutputStream dout = new DataOutputStream(s.getOutputStream());
    // //
    // coordinates c = new coordinates();
    // c.x = new Vector();
    // c.y = new Vector();
    //
    // c = (coordinates)is.readObject();
    // System.out.println("testing" + c.x.get(0));
    //
    // System.out.println("testing" + c.y.get(0));
    // System.out.println("testing" + c.x.get(1));
    // System.out.println("testing" + c.y.get(1));
    //
    // // str = "ping";
    // // dout.writeUTF(str);
    // // dout.flush();
    // System.out.println("Exit Client");
    //
    // dout.close();
    // s.close();

    //THERE IMP::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    Socket s = new Socket("localhost", 3333);
    //DataInputStream din = new DataInputStream(s.getInputStream());
    ObjectInputStream is = new ObjectInputStream(s.getInputStream());
    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int t1 = 0;
    String str = "", str2 = "";
    while (!str.equals("stop"))
    {

      //system input
      System.out.println("//simulate pressing to increment");
      str2 = br.readLine();
      System.out.println("//current location" + str2);
      double val = Double.parseDouble(str2);
      System.out.println("//parsed " + val);
      if (val > 5)
      {
        System.out.println("we would ping because out of range");
        t1 = 0;
      }
      else
      {
        System.out.println("we would not ping because in mbr");
      }

      //IF OUTSIDE MBR:
      if (t1 == 0)
      {
        coordinates c = new coordinates();
        c.x = new Vector();
        c.y = new Vector();

        //PING SERVER TO SEND OBJ
        System.out.println("PINGING SERVER FOR MBR");
        str = "ping";
        dout.writeUTF(str);
        dout.flush();
        t1 = 1;
        //Object Taken From Server
        c = (coordinates)is.readObject();
        System.out.println("//debug: " + c.x.get(0)); //DEBUG

        //Server Communicates Back
        //str2 = din.readUTF();
        //System.out.println("after ping: " + str2);
      }
    }

    dout.close();
    s.close();
  }
}
