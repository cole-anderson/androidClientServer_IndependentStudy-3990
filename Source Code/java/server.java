/*
  SERVERSIDE APPLICATION THAT PARSES ARFF FILE TO GENERATE DATA STRUCTURES CONTRAINING THE CLUSTER
  MBRS
  AND TRANSMITS A GIVEN MBR TO THE USER VIA SOCKETS
 */
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
public class server {
    public static void main(String[] args) throws IOException {
        int index = 0;
        int pingid = 0; // true
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

        // REALFILE VS TESFILE
        // String fileName = "clusterfnl.arff";
        String fileName = "test.txt";
        clust = parseArff(fileName, index); // 1

        int clusterNum = clust.size;
        // System.out.println("//debug cluster num : " + clusterNum); //PRINT DEBUG

        // Declarations:
        Socket clientSocket = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4445);
            System.out.println("Server Online");
            clientSocket = serverSocket.accept();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream din = new DataInputStream(clientSocket.getInputStream());
        ObjectOutputStream objo = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream obji = new ObjectInputStream(clientSocket.getInputStream());

        String str = "";

        while (!str.equals("end")) {
            // PING:
            System.out.println("");
            System.out.println("Waiting on Client Request");
            str = din.readUTF();
            pingid = Integer.parseInt(str);

            if (pingid == 0) {
                if (index < clusterNum) {
                    // System.out.println("//INCREMENT INDEX");//PRINT DEBUG
                    index++;
                    // System.out.println("<>INDEX INCR<> " + index);//PRINT DEBUG
                }
                if (index == clusterNum) {
                    System.out.println("//RESET INDEX"); // PRINT DEBUG
                    index = 0;
                    System.out.println("<>INDEX RESET<> " + index); // PRINT DEBUG
                }
            }
            if (sizeSend == 0) {
                index = 0;
                sizeSend = 1;
            }
            // Send Object
            // System.out.println("//%%INDEX VAL" + index);//PRINT DEBUG
            // System.out.println("//%%ARRAY SIZE" + clust.ar.length);//PRINT DEBUG
            System.out.println("Sending Object...");
            // System.out.println("TESTA " + clust.ar[index].x);//PRINT DEBUG
            // System.out.println("TESTB " + clust.ar[index].y);//PRINT DEBUG
            objo.writeObject(clust.ar[index]);
            objo.flush();
            pingid = 1;
        } // END SERVER LOOP
    } // END FUNC

    //****************************************************************************************
    public static returnClass parseArff(String fname, int index) {
        returnClass r1 = new returnClass();

        try {
            int lineID = 0;
            boolean exitCond = false;
            String seperated[];
            String last = "";
            int j = -1;
            int inc = 0;
            int num = 0;
            File myObj = new File(fname);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine() && exitCond == false) {
                String parseTop = myReader.nextLine();
                if (parseTop.length() == 0)
                    inc++;
                if (inc == 2) {
                    num = numCluster(last);
                    inc++;
                }
                if (inc == 3) {
                    exitCond = true;
                }
                last = parseTop;
            }
            // Cluster Creation
            coordinates cluster[] = new coordinates[num];
            for (int i = 0; i < num; i++) {
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

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                seperated = data.split(",");
                for (String a : seperated) {
                    switch (j) {
                        case -1:
                            j++;
                            break;
                        case 0:
                            nll = a;
                            // System.out.println("//nll:  " + nll); //PRINT DEBUG
                            j++;
                            break;
                        case 1:
                            xi = a;
                            // System.out.println("//x: " + xi); //PRINT DEBUG
                            j++;
                            break;
                        case 2:
                            yi = a;
                            // System.out.println("//y: " + yi); //PRINT DEBUG
                            j++;
                            break;
                        case 3:
                            ci = a;

                            // parsing out the cluster num
                            t = ci.charAt(7);
                            ind = Integer.parseInt(String.valueOf(t));
                            /*
                           ACTUALLY INPUTTING DATA INTO OBJECTS
                           */
                            ind = Integer.parseInt(String.valueOf(t));
                            cluster[ind].x.add(Double.parseDouble(xi));
                            cluster[ind].y.add(Double.parseDouble(yi));
                            j = 0;
                            break;
                        default:
                            System.out.println("error found parsing arff");
                    }
                }
            }
            myReader.close(); // close file
            /*
              Set Values of Cluster MBRs
            */
            // Top Right Corner x,y and Bottom Left Corner x,y
            for (int i = 0; i < num; i++) {
                cluster[i].topRCornerX.add(Collections.max(cluster[i].x)); // RIGHT BOUND
                cluster[i].topRCornerY.add(Collections.max(cluster[i].y)); // LUPPER BOUND
                // System.out.println("//topRCorner: " + i + ":" + cluster[i].topRCornerX.get(0) +
                // "," + cluster[i].topRCornerY.get(0)); //PRINT DEBUG
                cluster[i].botLCornerX.add(Collections.min(cluster[i].x)); // LEFT BOUND
                cluster[i].botLCornerY.add(Collections.min(cluster[i].y)); // LOWER BOUND
                // System.out.println("//botLCorner: " + i + ":" + cluster[i].botLCornerX.get(0) +
                // "," + cluster[i].botLCornerY.get(0)); //PRINT DEBUG
            }

            // Copy to Return Object
            r1.ar = new coordinates[num];
            r1.ar = cluster.clone();
        } catch (IOException e) {
            System.out.println("error with file\n");
            e.printStackTrace();
        }

        return r1; // return obj
    }
    //****************************************************************************************
    /*
          Helper Function
          -returns the number of clusters by parsing specific line of input file
      */
    //****************************************************************************************
    public static int numCluster(String iN) {
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
        while (index != (end - 1)) {
            num++;
            index = index + 9;
        }
        num = num + 1;
        // System.out.println("//Debug(numCluster): " + num);//Print Debug
        return num;
    }
}
