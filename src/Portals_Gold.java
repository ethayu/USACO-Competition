import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Portals_Gold {

    static class vertex implements Comparable<vertex> {
        int i, weight;

        @Override
        public int compareTo(vertex o) {
            return this.weight - o.weight;
        }

        vertex(int i, int weight) {
            this.i = i;
            this.weight = weight;
        }
    }

    static class uf {

        int sections;
        int[] sizes;
        int[] locations;

        uf (int n) {
            sections = n;
            sizes = new int[n];
            locations = new int[n];
            for (int i = 0; i < n; i++) {
                sizes[i] = i;
                locations[i] = i;
            }
        }

        void union(int a, int b) {
            if (linked(a, b)) return;
            if (sizes[root(a)] >= sizes[root(b)]) {
                locations[root(b)] = locations[root(a)];
                sizes[root(a)] += sizes[root(b)];
            } else {
                locations[root(a)] = locations[root(b)];
                sizes[root(b)] += sizes[root(a)];
            }
            this.sections--;
        }

        boolean linked(int a, int b) {
            return root(a) == root(b);
        }

        int root (int i) {
            while (locations[i] != i) {
                locations[i] = locations[locations[i]];
                i = locations[i];
            }
            return i;
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cost = new int[n];
        PriorityQueue<vertex> pq = new PriorityQueue<>();
        int[] matrix = new int[2 * n + 1];
        Arrays.fill(matrix, -1);
        uf network = new uf(4 * n);

        for (int i = 0; i < n; i++) {
            cost[i] = sc.nextInt();
            pq.add(new vertex(i, cost[i]));
            for (int j = 0; j < 4; j++) {
                int ret = 4 * i + j;
                int portal = sc.nextInt();
                if (matrix[portal] != -1) {
                    network.union(ret, matrix[portal]);
                } else {
                    matrix[portal] = ret;
                }
                if (j == 1 || j == 3) {
                    network.union(ret, ret - 1);
                }
            }

        }

        int min = 0;
        while (network.sections > 1) {
            vertex vertex = pq.remove();
            int ret = vertex.i * 4;
            boolean linked = true;
            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 4; j++) {
                    if (!network.linked(ret + i, ret + j)) {
                        linked = false;
                    }
                }
            }
            if (!linked) {
                min += vertex.weight;
                network.union(ret, ret + 1);
                network.union(ret, ret + 2);
                network.union(ret, ret + 3);
            }
        }
        System.out.println(min);
    }

}