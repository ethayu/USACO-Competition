import java.util.HashSet;
import java.util.Scanner;

public class cheese {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cows = new int[n];
        boolean[] breeds = new boolean[n];
        int[] lr = new int[n];

        long ans = 0;
        int counter = 0;
        for (int i = 0; i < n; i++) {
            cows[i] = sc.nextInt();
            if (!breeds[cows[i]]) {
                lr[i] = ++counter;
                breeds[cows[i]] = true;
            }
        }

        breeds = new boolean[n];

        counter = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (!breeds[cows[i]]) {
                ans += Math.abs(lr[i] - ++counter);
                breeds[cows[i]] = true;
            }
        }

        System.out.println(ans - 1);
    }

}
