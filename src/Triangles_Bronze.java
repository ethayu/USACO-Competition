import java.io.*;
import java.util.*;

public class Triangles_Bronze {

    public static int calculate(int[] a, int[] b, int[] c) {
        if (a[0] == b[0]) {
            if (b[0] == c[0]) return -1;
            else if (!((b[1] == c[1]) || (a[1] == c[1]))) return -1;
            return (Math.abs(a[1] - b[1])) * (Math.abs(b[0] - c[0]));
        } else if (a[0] == c[0]) {
            if (b[0] == c[0]) return -1;
            else if (!((b[1] == c[1]) || (a[1] == b[1]))) return -1;
            return (Math.abs(a[1] - c[1])) * (Math.abs(b[0] - a[0]));
        } else if (c[0] == b[0]) {
            if (a[0] == c[0]) return -1;
            else if (!((b[1] == a[1]) || (a[1] == c[1]))) return -1;
            return (Math.abs(c[1] - b[1])) * (Math.abs(a[0] - c[0]));
        } else return -1;

    }

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "triangles";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int[][] fences = new int[sc.nextInt()][2];

        for (int i = 0; i < fences.length; i++) {
            fences[i][0] = sc.nextInt();
            fences[i][1] = sc.nextInt();
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < fences.length; i++) {
            for (int j = 0; j < fences.length; j++) {
                if (i == j) continue;
                for (int k = 0; k < fences.length; k++) {
                    if ((j == k) || (i == k)) continue;
                    max = Math.max(max, calculate(fences[i], fences[j], fences[k]));
                }
            }
        }

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        writer.println(max);
        writer.close();

    }

}


