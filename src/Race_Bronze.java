import java.io.*;
import java.util.*;

public class Race_Bronze {

    static int customquadratic(long a, long b, long c, int dist) {

        //int s = Math.max((int) Math.round((-1*b + Math.sqrt(b*b - 4*a*c)) / (2 * a)), (int) Math.round((-1*b + Math.sqrt(b*b - 4*a*c)) / (2 * a)));

        double sqrt = Math.sqrt(b * b - 4 * a * c);
        long t = Math.max((int)(-1*b + sqrt) / (2 * a), (int)(-1*b - sqrt) / (2 * a));
        int s = Math.toIntExact(t);
        if (a*s*s + b*s + c >= 0) return s;
        return ++s;
    }

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "race";
        Scanner sc = new Scanner(new File(filename + ".in"));

        PrintWriter writer = new PrintWriter(new File(filename + ".out"));

        int dist = sc.nextInt();
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int speedlimit = sc.nextInt();
            int min = Integer.MAX_VALUE;
            for (int n = 1; n < (int)dist/speedlimit + 1; n++) { //n is how long the peak speed is held
                if (n > min) continue;
                int output = Math.round(customquadratic(1, n-1, -1 * (dist + speedlimit*(speedlimit-1)/2), dist));
                //output is now temporarily the min. "peak" speed, h.
                //time is h-2+n+h-(spdlimit -1) = 2h+n-spdlimit-1
                output = 2*output - speedlimit + n - 1;
                if ((output > 0) && (output < min)) min = output;
            }
            if (speedlimit*(speedlimit-1)/2 >= dist) min = Integer.MAX_VALUE;
            if (min == Integer.MAX_VALUE) {
                x : for (int j = 1; j <= speedlimit; j++) {
                    if (j * (j+1) / 2 >= dist) {
                        writer.println(j);
                        break x;
                    }
                }
                continue;
            }
            if (speedlimit * (speedlimit + 1) / 2 + ((min - speedlimit - 1) * speedlimit) >= dist) {
                int y = dist - (speedlimit + 1) * speedlimit / 2;
                min = y / speedlimit + speedlimit;
            }
            writer.println(min);
        }

        writer.close();

    }

}


