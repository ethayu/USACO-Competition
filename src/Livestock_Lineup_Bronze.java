import java.io.*;
import java.util.*;

public class Livestock_Lineup_Bronze {

    static void add (ArrayList cows, String cow, ArrayList<String> names, int index) {
        ArrayList<String> used = new ArrayList<>();
        int i = 0;
        for (String a : names) {
            if (a.compareTo(cow) < 0) {
                if (index == -1) {
                    cows.add(a);
                } else {
                    cows.add(index + i, a);
                    i++;
                }
                used.add(a);
            }
        }
        for (String a : used) {
            names.remove(names.indexOf(a));
        }
    }

    static String findr (String cow, String[][] restrictions) {
        boolean done = false;
        int a;
        int i, j = 0;
        for (i = 0; i < restrictions.length; i++) {
            for (j = 0; j < restrictions[i].length; j++) {
                if (restrictions[i][j].equals(cow)) {
                    done = true;
                    break;
                }
            }
            if (done) break;
        }
        if (j == 0) return restrictions[i][1];
        else return restrictions[i][0];
    }

    static int find (String cow, ArrayList<String> cows) {
        return cows.indexOf(cow);
    }

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "lineup";
        Scanner sc = new Scanner(new File(filename + ".in"));

        ArrayList<String> names = new ArrayList<>();
        names.add("Beatrice");
        names.add("Belinda");
        names.add("Bella");
        names.add("Bessie");
        names.add("Betsy");
        names.add("Blue");
        names.add("Buttercup");
        names.add("Sue");
        ArrayList<String> cows = new ArrayList<>();
        int n = sc.nextInt();
        String[] solution = new String[7];

        String[][] restrictions = new String[n][2];
        for (int i = 0; i < n; i++) {
            restrictions[i][0] = sc.next();
            sc.next();
            sc.next();
            sc.next();
            sc.next();
            restrictions[i][1] = sc.next();
            Arrays.sort(restrictions[i]);
        }



        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        for (String a : cows) {
            writer.println(a);
        }
        writer.close();

    }

}