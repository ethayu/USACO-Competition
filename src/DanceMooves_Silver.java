import java.util.*;
import java.io.*;

public class DanceMooves_Silver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter printer = new PrintWriter(System.out);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] key = new int[n];
        ArrayList<ArrayList<Integer>> location = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            location.add(new ArrayList<>());
            location.get(i).add(i);
            key[i] = i;
        }

        for (int i = 0; i < k; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            location.get(key[a]).add(b);
            location.get(key[b]).add(a);
            int temp = key[b];
            key[b] = key[a];
            key[a] = temp;
        }

        int[] lastcow = new int[n];
        Arrays.fill(lastcow, -1);
        int cow = 0;
        ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (lastcow[i] == -1) {
                visited.add(new ArrayList<>());
                Queue<Integer> dfs = new LinkedList<>();
                dfs.add(i);
                while (!dfs.isEmpty()) {
                    int position = dfs.poll();
                    if (lastcow[position] == -1) {
                        lastcow[position] = cow;
                        visited.get(cow).add(position);
                        dfs.add(location.get(position).get(location.get(position).size() - 1));
                    }
                }
                cow++;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        int[] done = new int[n];
        Arrays.fill(done, -1);
        int i = 0;
        for (ArrayList<Integer> group : visited) {
            int size = 0;
            for (int a : group) {
                for (int position : location.get(a)) {
                    if (done[position] != i) {
                        done[position] = i;
                        size++;
                    }
                }
            }
            ans.add(size);
            i++;
        }
        for (int ret : lastcow) printer.println(ans.get(ret));
        printer.close();
    }
}
