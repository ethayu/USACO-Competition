import java.util.*;
import java.io.*;

public class NoTimeToPaint_Silver {

    static class segtree {

        private int n;
        private int[] segtree;

        public segtree(int n) {
            this.n = n;
            segtree = new int[n * 2];
        }

        public void add(int i, int x) {
            i += n;
            segtree[i] += x;
            for (i /= 2; i >= 1; i /= 2) {
                segtree[i] = Math.min(segtree[2 * i], segtree[2 * i + 1]);
            }
        }

        public int rmq(int x, int y) {
            x += n;
            y += n;
            int min = Integer.MAX_VALUE;
            for (; x <= y; x /= 2) {
                if (y % 2 == 0) min = Math.min(min, segtree[y--]);
                if (x % 2 == 1) min = Math.min(min, segtree[x++]);
                y /= 2;
            }
            return min;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter printer = new PrintWriter(System.out);

        int n = sc.nextInt();
        int q = sc.nextInt();
        String fence = sc.next();
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) colors[i] = fence.charAt(i) - 'A';
        segtree tree = new segtree(n);
        int[] prevcolor = new int[26];
        Arrays.fill(prevcolor, -1);
        int[] leftdiffarray = new int[n];
        for (int i = 0; i < n; i++) {
            tree.add(i, colors[i]);
            if (prevcolor[colors[i]] == -1) {
                leftdiffarray[i]++;
                prevcolor[colors[i]] = i;
                continue;
            }
            int min = tree.rmq(prevcolor[colors[i]], i);
            if (min < colors[i]) {
                leftdiffarray[i]++;
            }
            prevcolor[colors[i]] = i;

        }

        tree = new segtree(n);
        prevcolor = new int[26];
        Arrays.fill(prevcolor, -1);
        int[] rightdiffarray = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            tree.add(i, colors[i]);
            if (prevcolor[colors[i]] == -1) {
                rightdiffarray[i]++;
                prevcolor[colors[i]] = i;
                continue;
            }
            int min = tree.rmq(i, prevcolor[colors[i]]);
            if (min < colors[i]) {
                rightdiffarray[i]++;
            }
            prevcolor[colors[i]] = i;

        }

        for (int i = 1; i < n; i++) {
            leftdiffarray[i] += leftdiffarray[i - 1];
            rightdiffarray[n - i - 1] += rightdiffarray[n - i];
        }

        for (int i = 0; i < q; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int ans = 0;
            if (a - 1 != -1) ans += leftdiffarray[a - 1];
            if (b + 1 != n) ans += rightdiffarray[b + 1];
            printer.println(ans);
        }
        printer.close();
    }
}
