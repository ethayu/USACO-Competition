import java.util.*;

public class Permutation_Gold {

    static long[] factorials = new long[41];
    static int[][] points;
    static int[][][][] status;
    static int n;
    static int MOD = 1000000007;
    static HashMap<String, HashSet<Integer>> pointsInside = new HashMap<>();


    static int cross(int[] a, int[] b) {
        return a[0] * b[1] - a[1] - b[0];
    }

    static boolean withinRange(double a, double b, double c) {
        return c >= a && c <= b;
    }

    /*static int isInside (int[] a, int[] b, int[] c, int[] p) {

        double denominator = cross(a, b) + cross(b, c) + cross(c, a);
        double wA = (cross(b, c) + cross(p, new int[] {b[0] - c[0], b[1] - c[1]})) / denominator;
        double wB = (cross(c, a) + cross(p, new int[] {c[0] - a[0], c[1] - a[1]})) / denominator;
        double wC = (cross(a, b) + cross(p, new int[] {a[0] - b[0], a[1] - b[1]})) / denominator;
        System.out.println(wA);
        System.out.println(wB);
        System.out.println(wC);
        if (withinRange(0, 1, wA) && withinRange(0, 1, wB) && withinRange(0, 1, wC)) return 0;
        return 1;

    }*/
    static int sign(int[] a, int[] b, int[] c) {
        return (a[0] - c[0]) * (b[1] - c[1]) - (b[0] - c[0]) * (a[1] - c[1]);
    }

    static int dist(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }

    static int[] reflect(int[] a, int[] b) {
        return new int[]{b[0] + b[0] - a[0], b[1] + b[1] - a[1]};
    }

    static boolean ccw(int[] a, int[] b, int[] c) {
        return (c[1] - a[1]) * (b[0] - a[0]) > (b[1] - a[1]) * (c[0] - a[0]);
    }

    static boolean intersect(int[] a, int[] b, int[] c, int[] d) {
        return (ccw(a, c, d) != ccw(b, c, d)) && (ccw(a, b, c) != ccw(a, b, d));
    }

    static boolean collinear(int[] a, int[] b, int[] c) {
        return ((b[1] - a[1]) * (c[0] - b[0])) == (c[1] - b[1]) * (b[1] - a[1]);
    }

    static int canCreate(int[] a, int[] b, int[] c, int[] p) {
        boolean bc = intersect(p, a, b, c);
        boolean ac = intersect(p, b, a, c);
        boolean ab = intersect(p, c, a, b);
        int ap = dist(a, p);
        int bp = dist(b, p);
        int cp = dist(c, p);
        if (!bc && !ac && !ab) {
            if (ap < cp) {
                if (bp < ap) return 2;
                else return 1;
            } else if (bp < cp) return 2;
            else return 3;
        } else if (bc) return -1;
        else if (ac) return -2;
        else if (ab) return -3;
        else {
            if (collinear(a, b, p)) {
                if (bp < ap) return -1;
                else return -2;
            } else if (collinear(a, c, p)) {
                if (cp < ap) return -1;
                else return -3;
            } else if (collinear(b, c, p)) {
                if (cp < bp) return -2;
                else return -3;
            } else {
                System.out.println("wtf");
                System.out.println(Arrays.toString(a));
                System.out.println(Arrays.toString(b));
                System.out.println(Arrays.toString(c));
                System.out.println(Arrays.toString(p));
                System.out.println(isInside(a, b, c, p));
            }
            return 69;
        }
    }

    /*static int canCreate(int[] a, int[] b, int[] c, int[] p) {
        if (isInside(a, b, c, reflect(p, a))) return 1;
        else if (isInside(a, b, c, reflect(p, b))) return 2;
        else if (isInside(a, b, c, reflect(p, c))) return 3;
        else {
            if (intersect(p, a, b, c)) return -1;
            else if (intersect(p, b, a, c)) return -2;
            else if (intersect(p, c, a, b)) return -3;
            else {
                if (collinear(a, b, p)) {
                    if (dist(p, b) < dist(p, a)) return -1;
                    else return -2;
                } else if (collinear(a, c, p)) {
                    if (dist(p, c) < dist(p, a)) return -1;
                    else return -3;
                } else if (collinear(b, c, p)) {
                    if (dist(p, c) < dist(p, b)) return -2;
                    else return -3;
                } else {
                    System.out.println("wtf");
                    System.out.println(Arrays.toString(a));
                    System.out.println(Arrays.toString(b));
                    System.out.println(Arrays.toString(c));
                    System.out.println(Arrays.toString(p));
                    System.out.println(isInside(a, b, c, p));
                }
                return 69;
            }
        }
    }*/

    static boolean isInside(int[] a, int[] b, int[] c, int[] p) {
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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        points = new int[n][2];
        factorials[0] = 1;
        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();

            factorials[i + 1] = ((i + 1) * factorials[i]) % MOD;
        }
        status = new int[n][n][n][n];

        Arrays.sort(points, (o1, o2) -> (o1[0] - o2[0] != 0) ? o1[0] - o2[0] : o1[1] - o2[1]);


        search(new boolean[n], 0, 0, 1, 2);

        ans = (ans * 6) % MOD;
        System.out.println(factorials[n] - ans);

    }

    static int ans = 0;

    static void search(boolean[] visited, int dist, int a, int b, int c) {
        if (dist == n) return;
        if (dist == 0) {
            for (int i = 0; i < n - 2; i++) {
                visited[i] = true;
                for (int j = i + 1; j < n - 1; j++) {
                    visited[j] = true;
                    for (int k = j + 1; k < n; k++) {
                        visited[k] = true;
                        search(visited, 3, i, j, k);
                        visited[k] = false;
                    }
                    visited[j] = false;
                }
                visited[i] = false;
            }
        } else {
            int[] temp = new int[]{a, b, c};
            Arrays.sort(temp);
            a = temp[0];
            b = temp[1];
            c = temp[2];
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (status[a][b][c][i] == 0) update(status, a, b, c, i);
                    if (status[a][b][c][i] < 0) {
                        ans += (int) factorials[n - dist - 1];
                        ans %= MOD;
                    } else if (status[a][b][c][i] == 4) {
                        search(visited, dist + 1, a, b, c);
                    } else if (status[a][b][c][i] == 1) {
                        search(visited, dist + 1, i, b, c);
                    } else if (status[a][b][c][i] == 2) {
                        search(visited, dist + 1, a, i, c);
                    } else {
                        search(visited, dist + 1, a, b, i);
                    }
                    visited[i] = false;
                }
            }

        }

    }

    static void update(int[][][][] status, int a, int b, int c, int i) {
        if (isInside(points[a], points[b], points[c], points[i])) {
            status[a][b][c][i] = 4;
            if (i < a) {
                status[i][a][b][c] = 3;
                status[i][a][c][b] = 2;
                status[i][b][c][a] = 1;
            } else if (i < b) {
                status[a][i][b][c] = 3;
                status[a][i][c][b] = 2;
                status[i][b][c][a] = 1;
            } else if (i < c) {
                status[a][b][i][c] = 3;
                status[a][i][c][b] = 2;
                status[b][i][c][a] = 1;
            } else {
                status[a][b][i][c] = 3;
                status[a][c][i][b] = 2;
                status[a][b][i][a] = 1;
            }
        } else {
            status[a][b][c][i] = canCreate(points[a], points[b], points[c], points[i]);
            if (status[a][b][c][i] == 3) status[a][b][i][c] = 4;
            else if (status[a][b][c][i] == 2) {
                if (i < a) status[i][a][c][b] = 4;
                else if (i < c) status[a][i][c][b] = 4;
                else status[a][c][i][b] = 4;
            } else if (status[a][b][c][i] == 1) status[i][b][c][a] = 4;
            else if (status[a][b][c][i] == -1) {
                if (i > c) status[b][c][i][a] = -3;
                else {
                    status[b][i][c][a] = -2;
                }
            } else if (status[a][b][c][i] == -2) {
                if (i < a) status[i][a][c][b] = -1;
                else if (i < c) {
                    status[a][i][c][b] = -2;
                } else status[a][c][i][b] = -3;
            } else {
                if (i < a) status[i][a][b][c] = -1;
                else {
                    status[a][i][b][c] = -2;
                }
            }
        }
    }
}
