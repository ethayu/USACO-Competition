import java.io.*;
import java.util.*;

public class template {

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "filename";
        Scanner sc = new Scanner(new File(filename + ".in"));

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        writer.println();
        writer.close();

    }

}


