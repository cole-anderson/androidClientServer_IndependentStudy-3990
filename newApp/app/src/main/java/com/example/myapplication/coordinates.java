package coordObj;

// coordinates is an object that contains 2 vectors
//  x is the x coordinate of the points in the given cluster
//  y is the y coordinate of the points in the given cluster

//Headers:
import java.lang.*;
import java.util.*;
@SuppressWarnings("unchecked")

public class coordinates
{
  Vector<Double> x;
  Vector<Double> y;
  coordinates(Vector<Double> x, Vector<Double> y)
  {
    this.x = x;
    this.y = y;
  }
  coordinates()
  {
  }
}
