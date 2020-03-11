import java.io.*;
import java.util.*;

public class Swap_Bronze {

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "swap";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int n = sc.nextInt();

        int[] cows = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cows[i] = i - 1;
        }

        int k = sc.nextInt() % n;
        int a1 = sc.nextInt();
        int a2 = sc.nextInt();
        int b1 = sc.nextInt();
        int b2 = sc.nextInt();

        int[] key = new int[n];

        for (int i = 0; i < key.length; i++) {
            key[i] = i;
        }

        for (int i = 0; i < (a2 - a1)/2 + 1; i++) {
            int temp = key[a1 + i - 1];
            key[a1 + i - 1] = key[a2 - i - 1];
            key[a2 - i - 1] = temp;
        }

        for (int i = 0; i < (b2 - b1)/2 + 1; i++) {
            int temp = key[b1 + i - 1];
            key[b1 + i - 1] = key[b2 - i - 1];
            key[b2 - i - 1] = temp;
        }

        int[] temp = key.clone();
        for (int i = 0; i < key.length; i++) {
            key[temp[i]] = i;
            if (key[i] == i) key[i] = -1;
        }

        for (int i = 1; i <= n; i++) {
            if (key[cows[i]] != -1) {
                for (int j = 0; j < k; j++) {
                    if (key[cows[i]] == -1) break;
                    cows[i] = key[cows[i]];
                }
            }
        }

        TreeMap<Integer, Integer> output = new TreeMap<>();

        for (int i = 1; i < cows.length; i++) {
            output.put(cows[i], i);
        }

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        for(Object a : output.values()) writer.println(a);
        writer.close();

    }

}


