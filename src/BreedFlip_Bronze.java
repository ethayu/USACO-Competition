import java.io.*;
import java.util.*;

public class BreedFlip_Bronze {

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "breedflip";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int n = sc.nextInt();
        String a = sc.next();
        String b = sc.next();

        boolean same = true;
        if (a.charAt(0) != b.charAt(0)) same = false;

        int counter = 0;

        for (int i = 1; i < n; i++) {
            if (same) {
                if (a.charAt(i) != b.charAt(i)) same = false;
            } else {
                if (a.charAt(i) == b.charAt(i)) {
                    same = true;
                    counter++;
                }
            }
        }

        if (!same) counter++;

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        writer.println(counter);
        writer.close();

    }

}


