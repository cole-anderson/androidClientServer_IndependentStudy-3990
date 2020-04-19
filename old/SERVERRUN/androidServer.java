import java.io.*;
import java.net.*;
import java.util.*;

public class androidServer
{
  private static ServerSocket ss;
  private static Socket s;
  private static BufferedReader br;
  private static InputStreamReader isr;
  private static String message = "";
  public static void main(String[] args)
  {

    try
    {
      while (true)
      {
        System.out.println("Server Active");
        ss = new ServerSocket(5000);
        s = ss.accept();

        isr = new InputStreamReader(s.getInputStream());
        br = new BufferedReader(isr);
        message = br.readLine();
        System.out.println("message : " + message);
        isr.close();
        br.close();
        ss.close();
        s.close();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
