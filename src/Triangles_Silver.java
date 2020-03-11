import java.io.*;
import java.util.*;

public class Triangles_Silver {
    public static void main(String[] args) throws FileNotFoundException {

        String filename = "triangles";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int n = sc.nextInt();
        int SHIFT_VALUE = 10000;

        // track y key, each with a x-list
        TreeMap<Integer, ArrayList<Integer>> ys = new TreeMap<>();
        // x key, total sum of y
        TreeMap<Integer, TreeMap<Integer, Integer>> ySum = new TreeMap<>();
        // track x key, each with a y-list
        TreeMap<Integer, ArrayList<Integer>> xs = new TreeMap<>();
        // y key, total sum of x
        TreeMap<Integer, TreeMap<Integer, Integer>> xSum = new TreeMap<>();

        int[] xorder = new int[20000];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int x_shifted = x + SHIFT_VALUE;
            int y_shifted = y + SHIFT_VALUE;
            ArrayList<Integer> xlist = ys.get(y_shifted);
            ArrayList<Integer> ylist = xs.get(x_shifted);
            if (ylist == null) {
                ylist = new ArrayList<Integer>();
                xs.put(new Integer(x_shifted), ylist);
            }
            if (xlist == null) {
                xlist = new ArrayList<Integer>();
                ys.put(new Integer(y_shifted), xlist);
            }
            ylist.add(y_shifted);
            xlist.add(x_shifted);
        }

        for (Map.Entry<Integer, ArrayList<Integer>> entry : ys.entrySet()) {
            int pxsum = 0;
            int pysum = 0;
            int y = entry.getKey();
            ySum.put(y, new TreeMap<Integer, Integer>());
            for (int i = 0; i < entry.getValue().size(); i++) {
                int x = entry.getValue().get(i);
                if (!xSum.containsKey(x)) {
                    xSum.put(x, new TreeMap<Integer, Integer>());
                }
                ySum.get(y).put(x, pysum + x - entry.getValue().get(0));
                xSum.get(x).put(y, pxsum + y - xs.get(x).get(0));
                pysum = ySum.get(y).get(x);
                pxsum = xSum.get(x).get(y);
            }
        }

        int sum = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : xs.entrySet()) {
            int bottom = 0;
            int x = entry.getKey();

            for (int i = 0; i < entry.getValue().size(); i++) {
                int y = entry.getValue().get(i);
                int top = xSum.get(x).get(xSum.get(x).lastKey()) - xSum.get(x).get(y) - (xs.get(x).size() - i - 1) * (y - xs.get(x).get(0));
                int across = ySum.get(y).size() - xorder[y] - 1;
                int right = ySum.get(y).get(ySum.get(y).lastKey()) - ySum.get(y).get(x) - across * (x - ys.get(y).get(0));
                bottom += y - xs.get(x).get(0);
                int left = (xorder[y] + 1) * (x - ys.get(y).get(0)) - ySum.get(y).get(x);
                sum += (top * right + right * bottom + bottom * left + left * top) % 1000000007;
                xorder[y]++;
            }
        }

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        writer.println(sum);
        writer.close();

    }
}