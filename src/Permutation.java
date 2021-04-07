import java.util.Scanner;

public class Permutation {

    static long[] factorials;
    static int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        coord[] points = new coord[n];
        factorials = new long[n + 1];

        factorials[0] = 1;

        for (int i = 0; i < n; i++) {
            points[i] = new coord(sc.nextInt(), sc.nextInt());
            factorials[i + 1] = ((i + 1) * factorials[i]) % MOD;
        }

        long ans = 0;
        boolean bad = true;
        int[] surround = new int[3];
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int count = 0;
                    for (int l = 0; l < n; l++) {
                        if (l == i || l == j || l == k) continue;
                        if (isInside(points[l], points[i], points[j], points[k])) {
                            count++;
                        }
                    }
                    if (count == n - 3) {
                        bad = false;
                        surround[0] = i;
                        surround[1] = j;
                        surround[2] = k;
                        ans = (ans + factorials[n - 3] * factorials[3]) % 1000000007;
                    }
                }
            }
        }
        if (bad) {
            System.out.println(0);
            return;
        }
        for (int a = 0; a < n; a++) {
            if (a == surround[0] || a == surround[1] || a == surround[2]) continue;
            int[] counts = new int[3];
            int[] innerCount = new int[3];
            int index = 0;
            for (int b : surround) {
                for (int c : surround) {
                    if (c <= b) continue;
                    int count = 0;
                    for (int i = 0; i < n; i++) {
                        if (i == a || i == b || i == c) continue;

                        if (isInside(points[i], points[a], points[b], points[c])) {
                            count++;
                        } else {
                            if (!((intersects(points[a], points[i], points[a], points[c])) ||
                                    (intersects(points[a], points[i], points[b], points[c])) ||
                                    (intersects(points[c], points[i], points[a], points[c])) ||
                                    (intersects(points[b], points[i], points[b], points[c])) ||
                                    (intersects(points[a], points[i], points[a], points[b])) ||
                                    (intersects(points[b], points[i], points[a], points[b])) ||
                                    (intersects(points[b], points[i], points[a], points[c])) ||
                                    (intersects(points[c], points[i], points[a], points[b])) ||
                                    (intersects(points[c], points[i], points[b], points[c])))) {
                                innerCount[index]++;
                            }
                        }
                    }
                    counts[index++] = count;
                }
            }

            for (int i = 0; i < 3; i++) {
                ans += 6 * factorials[counts[i] + innerCount[i]] * factorials[counts[(i + 1) % 3]] * factorials[counts[(i + 2) % 3]];
            }

            for (int b = a + 1; b < n; b++) {
                for (int c : surround) {
                    int count = 0;
                    for (int i = 0; i < n; i++) {
                        if (i == a || i == b || i == c) continue;
                        if (isInside(points[i], points[a], points[b], points[c])) {
                            count++;
                        } else {
                            if (!((intersects(points[a], points[i], points[a], points[c])) ||
                                    (intersects(points[a], points[i], points[a], points[b])) ||
                                    (intersects(points[a], points[i], points[b], points[c])) ||
                                    (intersects(points[b], points[i], points[a], points[c])) ||
                                    (intersects(points[c], points[i], points[a], points[b])) ||
                                    (intersects(points[c], points[i], points[a], points[c])) ||
                                    (intersects(points[b], points[i], points[a], points[b])) ||
                                    (intersects(points[b], points[i], points[b], points[c])) ||
                                    (intersects(points[c], points[i], points[b], points[c])))) {
                                count++;
                            }
                        }
                    }
                    ans = (ans + factorials[3] * factorials[count] * factorials[n - count - 3]) % MOD;
                }
            }
        }
        System.out.println(ans);
    }

    static int side(coord a, coord b, coord c) {
        return (a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y);
    }

    static boolean isInside(coord p, coord a, coord b, coord c) {
        int s1 = side(p, a, b);
        int s2 = side(p, b, c);
        int s3 = side(p, c, a);

        boolean posExists = (s1 > 0) || (s2 > 0) || (s3 > 0);
        boolean negExists = (s1 < 0) || (s2 < 0) || (s3 < 0);

        return !posExists || !negExists;
    }

    static class coord {
        int x, y;

        coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int dir(coord a, coord b, coord c) {
        int num = (c.x - b.x) * (b.y - a.y) - (b.x - a.x) * (c.y - b.y);

        if (num > 0) return 1;
        else if (num == 0) return 0;
        else return 2;
    }

    static boolean intersects(coord a, coord b, coord c, coord d) {
        if (a.equals(c) || a.equals(d) || b.equals(c) || b.equals(d)) {
            return false;
        }
        int d1 = dir(a, b, c);
        int d2 = dir(a, b, d);
        int d3 = dir(c, d, a);
        int d4 = dir(c, d, b);
        return d1 != d2 && d3 != d4;
    }
}