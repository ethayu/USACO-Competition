import java.io.*;
import java.util.*;

public class ClockTree_Silver {

    /**static boolean traverse (ArrayList[] relationships, int[] rooms, int room) {
        rooms[room]++;
        rooms[rooms.length - 1]++;
        if (rooms[rooms.length - 1] == (rooms.length-1)*12) return true;
        for (int i = 0; i < relationships[room].size(); i++) {
            int nextroom = (int) relationships[room].get(i);
            if (rooms[nextroom] == 12) continue;
            if (traverse(relationships, rooms.clone(), nextroom)) return true;
        }
        return false;
    }*/

    static boolean traverse (Stack<Integer> q, ArrayList[] relationships, int[] rooms) {
        if (rooms[rooms.length - 1] == (rooms.length-1)*12) return true;
        while (!q.isEmpty()) {
            int room = (int) q.pop();
            for (int i = 0; i < relationships[room].size(); i++) {
                int nextroom = (int) relationships[room].get(i);
                if (rooms[nextroom] == 12) continue;
                rooms[nextroom]++;
                rooms[rooms.length - 1]++;
                q.add(nextroom);
                traverse(q, relationships, rooms.clone());
            }
        }
        return false;
    }

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "clocktree";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int n = sc.nextInt();

        int[] rooms = new int[n+1];

        for (int i = 0; i < rooms.length - 1; i++) {
            rooms[i] = sc.nextInt();
            rooms[n] += rooms[i];
        }

        ArrayList[] relationships = new ArrayList[n];
        for (int i = 0; i < relationships.length; i++) {
            relationships[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n - 1; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            relationships[x].add(y);
            relationships[y].add(x);
        }

        int counter = 0;

        Stack<Integer> q = new Stack<>();


        for (int i = 0; i < n; i++) {
            q.add(i);
            rooms[i]--;
            rooms[rooms.length-1]--;
            if (traverse(q, relationships, rooms)) counter++;
        }

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        writer.println(counter);
        writer.close();

    }

}


