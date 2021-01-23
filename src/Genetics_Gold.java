import java.io.*;
import java.util.*;

public class Genetics_Gold {

    static LinkedList<Character> genome;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashSet<Integer> damaged = new HashSet<>();
        TreeSet<Integer> Gs = new TreeSet<>();
        TreeSet<Integer> Ts = new TreeSet<>();

        String input = sc.next();
        for (int i = 0; i < input.length(); i++) {
            genome.add(input.charAt(i));
            if (input.charAt(i) == '?') damaged.add(i);
            else if (input.charAt(i) == 'G') Gs.add(i);
            else if (input.charAt(i) == 'T') Ts.add(i);
        }


    }

}