/*
  Used as a container for point data from parsed file.
  X and Y coordinate taken from parsed data and turned into
  object
*/
public class object
{
  double x;
  double y;
  object(double x, double y)
  {
    this.x = x;
    this.y = y;
  }
  object()
  {
  }
}
