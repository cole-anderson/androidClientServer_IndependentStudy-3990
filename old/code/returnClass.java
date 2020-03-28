/*
  coordinates is an object that contains 2 vectors
  x is the x coordinate of the points in the given cluster
  y is the y coordinate of the points in the given cluster
*/
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
  //return from parseArff
  coordinates c[]; //return array of [size] clusters
  int size;        //return size of array

  //return from parseFnl
  fnlData f;
  //
  //
  returnClass(int size, coordinates c[], fnlData f)
  {
    this.size = size;
    this.c = c;
    this.f = f;
  }
  returnClass()
  {
  }
}
