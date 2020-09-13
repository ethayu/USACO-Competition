import java.io.*;
import java.util.*;

public class Photoshoot_Bronze {

    static int comparator (int[] a, int[]b) {
        //or (int i = 0; i < a.length; i++) {
            //if (a[i] < b[i]) return -1;
            //if (a[1] > b[1]) return 1;
        //}
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            int x = (a[i]);
            int y = b[i];
            if (x == y) continue;
            else if (x < y) result = -1;
            else result = 1;
        }
        return result;
    }

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "photo";
        Scanner sc = new Scanner(new File(filename + ".in"));

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));

        HashSet<int[]> possibilities = new HashSet<>();
        int[] second = new int[sc.nextInt() - 1];
        for (int i = 0; i < second.length; i++) second[i] = sc.nextInt();

        int[] lowest = new int[second.length + 1];
        Arrays.fill(lowest, Integer.MAX_VALUE);

        for (int i = 1; i < second[0]; i++) {
            if (i*2 == second[0]) continue;
            HashSet<Integer> numbers = new HashSet<>();
            int[] first = new int[second.length + 1];
            numbers.add(i);
            numbers.add(second[0] - i);
            first[0] = i;
            first[1] = second[0] - i;
            int j = 1;
            boolean done = false;
            while (!done) {
                if (j == second.length) {
                    possibilities.add(first);
                    done = true;
                    continue;
                }
                if ((second[j]-first[j] < 0)) {
                    done = true;
                    continue;
                }
                if (!numbers.contains(second[j]-first[j])) {
                    first[j + 1] = second[j]-first[j];
                    numbers.add(second[j]-first[j]);
                } else done = true;
                j++;
            }
        }

        for(int [] a : possibilities) {
            if (comparator(lowest, a) > 0) lowest = a;
        }

        for (int i = 0; i < lowest.length; i++) {
            writer.print(lowest[i]);
            if (i != lowest.length - 1) {
                writer.print(" ");
            }
        }

        writer.close();

    }

}


