import java.io.PrintWriter;
import java.util.Scanner;

public class CounttheCows_Gold {

    static int x, y, d;
    final static double log3 = Math.log(3);

    static int brute(long x, long y, long d) {
        int ret = 0;
        for (int distance = 0; distance < d + 1; distance++) {
            long i = x + distance;
            long j = y + distance;
            boolean ok = true;
            int temp = 0;
            if (i > j) {
                temp = (int) (Math.log(i) / log3);
                for (int k = 0; k < temp + 1; k++) {
                    double b = (int) (j / Math.pow(3, k)) % 3;
                    double a = (int) (i / Math.pow(3, k)) % 3;
                    if ((a == 1 && b == 0) ||
                            (a == 1 && b == 2) ||
                            (a == 2 && b == 1) ||
                            (a == 0 && b == 1)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) ret++;
            } else if (j > i) {
                temp = (int) (Math.log(j) / log3);
                for (int k = 0; k < temp + 1; k++) {
                    double b = (int) (j / Math.pow(3, k)) % 3;
                    double a = (int) (i / Math.pow(3, k)) % 3;
                    if ((a == 1 && b == 0) ||
                            (a == 1 && b == 2) ||
                            (a == 2 && b == 1) ||
                            (a == 0 && b == 1)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) ret++;
            } else ret++;
        }

        return ret;
    }

    static int calculate(long x, long d) {
        if (x % 2 == 1) return 0;
        int ans = 0;
        int logx = (int) Math.ceil(Math.log(x + 1) / log3);
        int logxy = (int) Math.ceil(Math.log(x + d + 1) / log3);
        ans += brute(x, 0, (int) Math.pow(3, logx) - x);
        ans *= Math.pow(3, logxy - logx);
        ans -= brute(x + d + 1, d + 1, (int) (Math.pow(3, logxy) - x - d - 2));
        return ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int q = sc.nextInt();

        for (int answered = 0; answered < q; answered++) {
            long d = sc.nextLong();
            long x = sc.nextLong();
            long y = sc.nextLong();
            if (x > y) out.println(calculate(x - y, d + y) - calculate(x - y, y - 1));
            else if (x == y) out.println(d + 1);
            else out.println(calculate(y - x, d + x) - calculate(y - x, x - 1));
        }

        out.close();
    }

}