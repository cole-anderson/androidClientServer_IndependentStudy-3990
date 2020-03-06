/*
  Used as a container for point data from parsed file.
  X and Y coordinate taken from parsed data and turned into
  object
*/
//Headers:
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("unchecked")

public class object
{
  Vector x;
  Vector y;
  object(Vector x, Vector y)
  {
    this.x = x;
    this.y = y;
  }
  object()
  {
  }
}
