
//package retClass;
//Headers:
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("unchecked")

public class returnClass
{
  /*
    User as a container to return parse information from functions
  */

  // Return data from parseArff
  public coordinates c[]; //return array of [size] clusters
  public int size;        //return size of array

  // Return data from parseFnl
  public static fnlData f;

  public returnClass(int size, coordinates c[], fnlData f)
  {
    this.size = size;
    this.c = c;
    this.f = f;
  }
  public returnClass()
  {
  }
}
