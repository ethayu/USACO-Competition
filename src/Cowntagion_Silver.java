import java.util.*;

public class Cowntagion_Silver {

    static int log2(int n) {
        int a = (int) (Math.log(n) / Math.log(2) + 1e-10);
        if (Math.pow(2.00, a) < n) return a + 1;
        else return a;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Integer> visited = new HashSet<>();
        TreeMap<Integer, ArrayList<Integer>> adjList = new TreeMap<>();
        int n = sc.nextInt();
        int m = 0;
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (!adjList.containsKey(a)) adjList.put(a, new ArrayList<>());
            if (!adjList.containsKey(b)) adjList.put(b, new ArrayList<>());
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            int farm = q.poll();
            if (!visited.contains(farm)) {
                visited.add(farm);
                int counter = 0;
                for (int road : adjList.get(farm)) {
                    counter++;
                    q.add(road);
                }
                m += log2(counter);
            }
        }
        System.out.println(n - 1 + m);
    }
}