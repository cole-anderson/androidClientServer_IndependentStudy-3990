
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.Scanner;

//5:23pm
public class server
{

  public static void main(String[] args) throws IOException
  {

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
    DataInputStream din = new DataInputStream(clientSocket.getInputStream());
    DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

    //Scanner in1 = new Scanner(clientSocket.getInputStream());
    // String mes;

    String str = "", str2 = "";
    while (!str.equals("end"))
    {
      System.out.println("inside WHILE");
      str = din.readUTF();
      System.out.println("from client" + str);
      str2 = "world";
      dout.writeUTF(str2);
      dout.flush();
      str = "";
    }
    // while (true)
    // {
    //
    //   if (in1.hasNext())
    //   {
    //     mes = in1.nextLine();
    //     System.out.println("Client message :" + mes);
    //   }
    // }
  }
}
