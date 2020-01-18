import java.io.*;
import java.util.*;

public class Word_Processor_Bronze {

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "word";
        Scanner sc = new Scanner(new File(filename + ".in"));

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));

        int nofwords = sc.nextInt();
        int limit = sc.nextInt();

        for (int i = 0, b = limit; i < nofwords; i++) {
            String input = sc.next();
            if (b < input.length()) {
                writer.println("");
                b = limit - input.length();
            } else {
                if (b < limit) {
                    writer.print(" ");
                }
                b -= input.length();
            }
            writer.print(input);
        }
        writer.close();

    }

}


