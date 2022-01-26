package myapplication;
// package retClass;
// Headers:
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unchecked")

public class returnClass implements Serializable {
    /*
      User as a container to return parse information from functions
    */

    // Return data from parseArff
    public coordinates c; // cluster
    public coordinates ar[]; // array of clusters
    public int size; // return size of array
    public int index; // index of currently read

    // Return data from parseFnl
    public static fnlData f;

    public returnClass(int size, coordinates c, coordinates ar[], fnlData f, int index) {
        this.size = size;
        this.c = c;
        this.f = f;
        this.index = index;
        this.ar = ar;
    }
    public returnClass() {}
}
