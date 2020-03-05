//tester
import java.lang.*;
public class tester
{

  public static void main(String[] args)
  {
    //DEBUG VALUES:
    double x[] = {4, 4, 2, 2};
    double y[] = {2, 4, 2, 4};
    double currentX = 3.75;
    double currentY = 3.75;

    //NOTE:START
    //Start of Function Declarations(reset at each function call)
    double arSize = 4; //TODO: temp change to array.size()
    double closestX = 0;
    double closestY = 0;
    double tempDistX = 10000000;
    double tempDistY = 10000000;

    /*
    For loop that linearly searches our array for the point
    "closest" (closestX, closestY) to our "current" (currentX, currentY) point.
    */
    for (int i = 0; i < arSize; i++)
    {
      if (Math.abs(x[i] - currentX) <= tempDistX)
      {
        if (Math.abs(y[i] - currentY) <= tempDistY)
        {
          closestX = x[i];
          closestY = y[i];
          tempDistX = Math.abs(x[i] - currentX);
          tempDistY = Math.abs(y[i] - currentY);
        }
      }
    }
    //Prints the next closest point
    System.out.println("closest x,y: " + closestX + "," + closestY);
    //NOTE:END
  }
}
