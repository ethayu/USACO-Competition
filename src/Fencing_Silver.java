import java.io.*;
import java.util.*;

public class Fencing_Silver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            cows[i][0] = sc.nextInt();
            cows[i][1] = sc.nextInt();
        }

        Arrays.sort(cows, Comparator.comparingInt(o -> o[0]));
        int invalid = 0;
        int asdf = 0;
        for (int i = 0; i < 1 << cows.length; i++) {
            int temp = i;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int first = -1;
            int last = 0;
            int count = 0;
            HashSet<Integer> tempnumbers = new HashSet<>();
            HashSet<Integer> numbers = new HashSet<>();
            for (int j = 0; j < cows.length; j++) {
                int k = temp & 1;
                if (k == 1) {
                    count++;
                    numbers.addAll(tempnumbers);
                    tempnumbers = new HashSet<>();
                    if (first == -1) first = cows[j][1];
                    last = cows[j][1];
                    min = Math.min(min, cows[j][1]);
                    max = Math.max(max, cows[j][1]);
                } else if (first != -1) tempnumbers.add(cows[j][1]);
                temp = temp >> 1;
            }
            if (count > 1) {
                for (int a : numbers)
                    if ((a > min && a < max)) {
                        if (first == 1 && last == 2) asdf++;
                        invalid++;
                        break;
                    }
            }
        }

        System.out.println((long) Math.pow(2, n) - invalid);
        System.out.println(asdf);

    }

}