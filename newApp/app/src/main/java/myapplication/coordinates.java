package myapplication;
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

public class coordinates {
  public Vector<Double> x;
  public Vector<Double> y;
  public double topRCornerX;
  public double botLCornerX;
  public double topRCornerY;
  public double botLCornerY;
  public Vector<Integer> clusterID;

  public coordinates(Vector<Double> x, Vector<Double> y, double topRCornerX, double botLCornerX, double topRCornerY, double botLCornerY, Vector<Integer> clusterID) {
    this.x = x;
    this.y = y;
    this.topRCornerX = topRCornerX;
    this.botLCornerX = botLCornerX;
    this.topRCornerY = topRCornerY;
    this.botLCornerY = botLCornerY;
    this.clusterID = clusterID;
  }
  //Default Constructor
  public coordinates() {
  }
}