import java.io.*;
import java.util.*;

public class Stuck_Silver {

    static class cow {

        int x, y, stopped, stopLocation;
        boolean isNorth;

        cow(boolean c, int a, int b) {
            stopped = -1;
            x = a;
            y = b;
            isNorth = c;
        }

    }

    static int nofblames (ArrayList<HashSet<Integer>> blames, int currCow) {
        int ret = 0;
        for (int cow : blames.get(currCow)) ret += nofblames(blames, cow);
        return ret + blames.get(currCow).size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TreeMap<Integer, ArrayList<int[]>> times = new TreeMap<>();
        ArrayList<HashSet<Integer>> blames = new ArrayList<>();
        ArrayList<HashSet<Integer>> stops = new ArrayList<>();
        cow[] cows = new cow[n];
        TreeSet<Integer> north = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return cows[o1].x - cows[o2].x;
            }
        });
        TreeSet<Integer> east = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return cows[o1].y - cows[o2].y;
            }
        });
        for (int i = 0; i < cows.length; i++) {
            blames.add(i, new HashSet<>());
            stops.add(i, new HashSet<>());
            cows[i] = new cow(sc.next().equals("N"), sc.nextInt(), sc.nextInt());
            if (cows[i].isNorth) north.add(i);
            else east.add(i);
        }

        for (int cow1 : east) {
            for (int cow2 : north) {
                int dx = cows[cow2].x - cows[cow1].x;
                int dy = cows[cow1].y - cows[cow2].y;
                if (dx > 0 && dy > 0) {
                    if (dy > dx) {
                        if (!times.containsKey(dy)) times.put(dy, new ArrayList<>());
                        times.get(dy).add(new int[]{cow1, cow2});
                    }
                    if (dx > dy) {
                        if (!times.containsKey(dx)) times.put(dx, new ArrayList<>());
                        times.get(dx).add(new int[]{cow2, cow1});
                    }
                }
            }
        }

        for (int time : times.keySet()) {
            for (int[] pair : times.get(time)) {
                int cow1 = pair[0];
                int cow2 = pair[1];
                if ((cows[cow2].stopped == -1) && (cows[cow1].stopped > time || (cows[cow1].stopLocation > cows[cow2].x && !cows[cow1].isNorth) || (cows[cow1].stopLocation > cows[cow2].y && cows[cow1].isNorth) || cows[cow1].stopped == -1)) {
                    blames.get(cow1).add(cow2);
                    stops.get(cow2).add(cow1);
                    cows[cow2].stopped = time;
                    cows[cow2].stopLocation = cows[cow1].isNorth ? cows[cow1].x : cows[cow1].y;
                }
            }
        }

        for (int i = 0; i < blames.size(); i++) {
            System.out.println(nofblames(blames, i));
        }

    }
}