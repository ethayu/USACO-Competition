import java.io.*;
import java.util.*;

public class Replication_Gold {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static class pair {

        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            pair other = (pair) obj;
            if (x != other.x)
                return false;
            return y == other.y;
        }
    }

    static int n, d;
    static int[][] field;

    static HashSet<pair> rocks = new HashSet<>();
    static TreeMap<Integer, Integer> rockx = new TreeMap<>();
    static TreeMap<Integer, Integer> rocky = new TreeMap<>();
    static HashSet<pair> starts = new HashSet<>();
    static HashSet<pair> spaces = new HashSet<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        d = sc.nextInt();

        field = new int[n][n];

        for (int i = 0; i < n; i++) {
            String row = sc.next();
            for (int j = 0; j < n; j++) {
                field[i][j] = row.charAt(j) == '#' ? 0 : row.charAt(j) == 'S' ? 1 : 2;
                if (field[i][j] == 0) {
                    rocks.add(new pair(i, j));
                    rockx.put(i, j);
                    rocky.put(j, i);
                } else if (field[i][j] == 1) starts.add(new pair(i, j));
            }
        }

        for (pair start : starts) {
            HashSet<pair> robots = new HashSet<>();
            robots.add(start);
            floodfill(robots, 0, start.x, start.y, start.x, start.y);
            /*for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(spaces.contains(new pair(i, j)) ? 'x' : field[i][j] == 0 ? '#' : field[i][j] == 1 ? 'S' : '.');
                }
                System.out.println();
            }
            System.out.println();*/
        }
        System.out.println(spaces.size());

    }

    static boolean containsRocks(HashSet<pair> robots) {
        for (pair robot : robots) {
            if (rocks.contains(robot)) return true;
        }
        return false;
    }

    static void clean(HashSet<pair> robots) {
        for (pair robot : robots) {
            for (int i = 0; i < 4; i++) {
                //if (robots.contains(new pair(robot.x + dx[i], robot.y + dy[i])) || )
            }
        }
    }

    static void floodfill(HashSet<pair> robots, int t, int maxx, int maxy, int minx, int miny) {
        if (containsRocks(robots)) return;
        else spaces.addAll(robots);
        if (spaces.size() == n * n - rocks.size()) System.out.println(spaces.size());
        if (t % (d + 1) == d) {
            HashSet<pair> temp = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                for (pair robot : robots) {
                    temp.add(new pair(robot.x + dx[i], robot.y + dy[i]));
                }
            }
            robots.addAll(temp);
            floodfill(robots, t + 1, maxx + 1, maxy + 1, minx - 1, miny - 1);
        } else for (int i = 0; i < 4; i++) {
            HashSet<pair> temp = (HashSet<pair>) robots.clone();
            int a = 0, b = 0;
            for (pair robot : robots) {
                temp.remove(robot);
                a = Math.min(rockx.higherKey(maxx) - maxx, minx - rockx.lowerKey(minx));
                b = Math.min(rocky.higherKey(maxy) - maxy, miny - rocky.lowerKey(miny));
                temp.add(new pair(robot.x + a * dx[i], robot.y + b * dy[i]));
            }
            floodfill(temp, t + 1, maxx + a, maxy + b, minx - a, miny - b);
        }
    }

}