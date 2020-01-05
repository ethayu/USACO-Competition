import java.io.*;
import java.util.*;

public class Cow_Gymnastics_Bronze {

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "gymnastics";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int k = sc.nextInt();
        int n = sc.nextInt();

        int[][] info = new int[k][n];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                info[i][j] = sc.nextInt();
            }
        }

        int counter = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = info[0][i];
                int b = info[0][j];
                boolean notconsistent = false;
                for (int l = 1; l < k; l++) {
                    for (int m = 0; m < n; m++) {
                        if (info[l][m] == a) break;
                        if (info[l][m] == b) {
                            notconsistent = true;
                            break;
                        }
                    }
                }
                if (!notconsistent) counter++;
            }
        }

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        writer.println(counter);
        writer.close();

    }

}
