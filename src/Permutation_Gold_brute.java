import java.util.*;

public class Permutation_Gold_brute {

    static int[][] points;
    static int n;
    static int MOD = 1000000007;
    static boolean ccw(int[] a, int[] b, int[] c) {
        return (c[1] - a[1]) * (b[0] - a[0]) > (b[1] - a[1]) * (c[0] - a[0]);
    }

    static boolean intersect(int[] a, int[] b, int[] c, int[] d) {
        return (ccw(a, c, d) != ccw(b, c, d)) && (ccw(a, b, c) != ccw(a, b, d));
    }

    static boolean collinear(int[] a, int[] b, int[] c) {
        return ((b[1] - a[1]) * (c[0] - b[0])) == (c[1] - b[1]) * (b[1] - a[1]);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
        }

        Arrays.sort(points, (o1, o2) -> (o1[0] - o2[0] != 0) ? o1[0] - o2[0] : o1[1] - o2[1]);

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n - 2; i++) {
            visited[i] = true;
            for (int j = i + 1; j < n - 1; j++) {
                visited[j] = true;
                for (int k = j + 1; k < n; k++) {
                    visited[k] = true;
                    int[] temp = new int[n];
                    temp[0] = i;
                    temp[1] = j;
                    temp[2] = k;/*
                    if (temp[0] == 1 && temp[1] == 2 && temp[2] == 3) {
                        System.out.println();
                    }*/
                    search(visited, temp, 3);
                    visited[k] = false;
                }
                visited[j] = false;
            }
            visited[i] = false;
        }

        ans = (ans * 6) % MOD;
        System.out.println(ans);

    }

    static int ans = 0;

    static void search(boolean[] visited, int[] order, int dist) {
        if (dist == n) {
            System.out.println(Arrays.toString(order));
            if (check(order)) {
                ans += 1;
            }
            ans %= MOD;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int[] temp = order.clone();
                temp[dist] = i;
                search(visited, temp, dist + 1);
                visited[i] = false;
            }
        }
    }


    static boolean check(int[] order) {
        int a = order[0], b = order[1], c = order[2];
        for (int j = 3; j < n; j++) {
            int i = order[j];
            if (isInside(points[a], points[b], points[c], points[i])) continue;
            int ret = canCreate(points[a], points[b], points[c], points[i]);
            if (ret == 1) a = i;
            else if (ret == 2) b = i;
            else if (ret == 3) c = i;
            else if (ret < 0) return false;
        }
        //System.out.println(Arrays.toString(order));
        return true;
    }

    static int canCreate(int[] a, int[] b, int[] c, int[] p) {
        boolean bc = intersect(p, a, b, c);
        boolean ac = intersect(p, b, a, c);
        boolean ab = intersect(p, c, a, b);
        int ap = dist(a, p);
        int bp = dist(b, p);
        int cp = dist(c, p);
        if (!bc && !ac && !ab) {
            if (isInside(new int[]{a[0] - (b[0] - a[0]), a[1] - (b[1] - a[1])}, b, new int[]{b[0] - (c[0] - b[0]), b[1] - (c[1] - b[1])}, p)) ;
            else return 3;
        } else return -1;
        return 1;

    }

    static boolean isInside(int[] a, int[] b, int[] c, int[] p) {
        if (collinear(a, b, p) || collinear(a, c, p) || collinear(b, c, p)) return false;
        int d1, d2, d3;
        d1 = sign(p, a, b);
        d2 = sign(p, b, c);
        d3 = sign(p, c, a);
        if (!(((d1 < 0) || (d2 < 0) || (d3 < 0)) && ((d1 > 0) || (d2 > 0) || (d3 > 0)))) {
            if (d1 == 0) {
                if ((p[0] - a[0] > 0 && b[0] - p[0] > 0) || (p[0] - a[0] < 0 && b[0] - p[0] < 0) || (p[0] - a[0] == 0 && b[0] - p[0] == 0))
                    return false;
            } else if (d2 == 0) {
                if ((p[0] - b[0] > 0 && b[0] - p[0] > 0) || (p[0] - b[0] < 0 && b[0] - p[0] < 0) || (p[0] - b[0] == 0 && b[0] - p[0] == 0))
                    return false;
            } else if (d3 == 0) {
                if ((p[0] - c[0] > 0 && a[0] - p[0] > 0) || (p[0] - c[0] < 0 && a[0] - p[0] < 0) || (p[0] - c[0] == 0 && a[0] - p[0] == 0))
                    return false;
            }
            return true;
        }
        return false;
    }

    static int sign(int[] a, int[] b, int[] c) {
        return (a[0] - c[0]) * (b[1] - c[1]) - (b[0] - c[0]) * (a[1] - c[1]);
    }

    static int dist(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }
}
