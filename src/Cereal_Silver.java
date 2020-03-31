import java.io.*;
import java.util.*;

public class Cereal_Silver {

    static int counter = 1;
    static int[][] cows;
    static TreeMap<Integer, Integer> cowscereal = new TreeMap<>(), cerealcows = new TreeMap<>();
    static Stack<Integer> answers = new Stack<>();

    static void add(int cow, int choice) {
        int tobereplacedcow = cerealcows.get(cows[cow][choice]);
        int replacedchoice;
        if (cows[tobereplacedcow][0] == cowscereal.get(tobereplacedcow)) replacedchoice = 1;
        else replacedchoice = 0;
        cowscereal.remove(tobereplacedcow);
        cowscereal.put(cow, cows[cow][choice]);
        cerealcows.put(cows[cow][choice], cow);
        if (cerealcows.containsKey(cows[tobereplacedcow][replacedchoice])) {
            if (cerealcows.get(cows[tobereplacedcow][replacedchoice]) < tobereplacedcow) counter--;
            else add(tobereplacedcow, replacedchoice);
        } else {
            cowscereal.put(tobereplacedcow, cows[tobereplacedcow][replacedchoice]);
            cerealcows.put(cows[tobereplacedcow][replacedchoice], tobereplacedcow);
        }
    }

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "cereal";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int nofcows = sc.nextInt();
        int types = sc.nextInt();

        cows = new int[nofcows][2];

        for (int i = 0; i < nofcows; i++) {
            cows[i][0] = sc.nextInt();
            cows[i][1] = sc.nextInt();
        }

        answers.push(counter);
        cowscereal.put(nofcows - 1, cows[nofcows - 1][0]);
        cerealcows.put(cows[nofcows - 1][0], nofcows - 1);

        for (int i = 2; i <= nofcows; i++) {
            if (!cerealcows.containsKey(cows[nofcows - i][0])) {
                cowscereal.put(nofcows - i, cows[nofcows - i][0]);
                cerealcows.put(cows[nofcows - i][0], nofcows - i);
            } else add(nofcows - i, 0);
            answers.push(++counter);
        }

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        for (int i = 0; i < nofcows; i++) writer.println(answers.pop());
        writer.close();

    }

}