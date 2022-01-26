package myapplication;
//package fnlD;
/*

*/
//Headers:
import java.lang.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class fnlData
{
  public Vector<Double> lx;
  public Vector<Double> ly;
  public Vector<Double> hx;
  public Vector<Double> hy;
  public Vector<Double> id;
  public fnlData(Vector<Double> lx, Vector<Double> ly, Vector<Double> hx, Vector<Double> hy, Vector<Double> id)
  {
    this.lx = lx;
    this.ly = ly;
    this.hx = hx;
    this.hy = hy;
    this.id = id;
  }
  public fnlData()
  {
  }
}
