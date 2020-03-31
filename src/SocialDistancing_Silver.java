import java.io.*;
import java.util.*;

public class SocialDistancing_Silver {

    static boolean[] ifpossible;

    static class patch implements Comparable<patch> {

        long front;
        long back;
        long distuntilnext;

        public patch (long a, long b) {front = a; back = b;}

        @Override
        public int compareTo(patch patch) {
            return (int) (this.front - patch.front);
        }
    }

    public static long brute(patch areas[], long ans, int N){
        int length = areas.length;
        long cows = 0, next = areas[0].front;
        long extraarea = areas[length - 1].back % ans;
        for (int j = 0; j < length; j++) {
            if (next < areas[j].front) {
                next = areas[j].front;
                extraarea -= (areas[j].front - next);
            } else if (next > areas[j].back) {
                if (extraarea <= 0) break;
                else continue;
            }
            cows += (areas[j].back - next) / ans + 1;
            next += ((areas[j].back - next) / ans + 1) * ans;
            if (1 + (areas[length - 1].back - next) / ans + cows < N || extraarea <= 0) break;
        }
        if (cows >= N) {
            ifpossible[Math.toIntExact(ans)] = true;
            return ans;
        } else return -1;
    }

    public static long search(patch areas[], long front, long back, int N){
        long ret;
        long mid = (front + back + 1) / 2;
        if ((mid == front) || (back == mid)) {
            if (ifpossible[Math.toIntExact(back)]) return back;
            else return (front + back) / 2;
        } else if (brute(areas, mid, N) == -1) ret = search(areas, front, mid, N);
        else ret = search(areas, mid, back, N);
        return ret;
    }

    public static void main (String [] args) throws FileNotFoundException {
        String fname = "socdist";
        Scanner sc = new Scanner(new File(fname + ".in"));
        int N = sc.nextInt();
        int M = sc.nextInt();
        long max = Integer.MIN_VALUE;
        patch[] areas = new patch[M];

        for (int i = 0; i < M; i++) {
            areas[i] = new patch(sc.nextLong(), sc.nextLong());
            max = Math.max(max, areas[i].back);
            if (i > 0) areas[i-1].distuntilnext = areas[i].front - areas[i-1].back;
        }
        Arrays.sort(areas);
        long ans = (areas[areas.length - 1].back - areas[0].front) / (N - 1);
        ifpossible = new boolean[Math.toIntExact(ans) + 1];
        if (max - 100000 > 0) {
            long temp = brute(areas, ans, N);
            if (temp == -1) {
                ans = search(areas, 1, ans, N);
            } else ans = temp;
        } else {
            while (brute(areas, ans, N) == -1) ans--;
        }
        PrintWriter writer = new PrintWriter(new File(fname + ".out"));
        writer.println(ans);
        writer.close();
    }


}