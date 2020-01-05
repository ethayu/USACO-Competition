import java.io.*;
import java.util.*;

public class Where_Am_I_Bronze {

    static boolean found (String sub, String whole, int start) {
        boolean saw = false;
        for (int i = start + 1; i <= whole.length() - sub.length(); i++) {
            if (whole.substring(i, i + sub.length()).equals(sub)) {
                saw = true;
                break;
            }
            if (saw) break;
        }
        return saw;
    }

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "whereami";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int n = sc.nextInt();
        String order = sc.next();
        int c;

        boolean done = false;

        for (c = 1; c < n; c++) { //how many
            for (int i = 0; i <= order.length() - c; i++) { //go through each comb
                if (!(found(order.substring(i, i + c), order, i))) { //if uni
                    if (i == order.length() - c - 1)  {
                        done = true;
                        continue;
                    }
                } else break;
            }
            if (done) break;
        }

        for (int i = 0; i < order.length(); i++) {
            if ((order.charAt(0) == order.charAt(i))) {
                if (i == order.length()-2) c = n;
            }
        }

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        writer.println(c);
        writer.close();

    }

}
