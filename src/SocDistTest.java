import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SocDistTest {
    static void tc1(PrintWriter writer) {
        //long grass_begin = Long.MAX_VALUE;
        int numcows = 2;
        int numgrass = 9;
        writer.println( numcows + " " + numgrass );
        for (int i=1; i <= numgrass; i++) {
            writer.println((long)i + " " + (long)i);
        }
        System.out.println("Expected: " + 8);
    }
    static void tc2(PrintWriter writer) {
        //long grass_begin = Long.MAX_VALUE;
        int numcows = 3;
        int numgrass = 10; //100000;
        writer.println( numcows + " " + numgrass );
        for (int i=1; i <= numgrass; i++) {
            writer.println((long)i + " " + (long)i);
        }
        System.out.println("Expected: " + 4);
    }
    static void tc3(PrintWriter writer) {
        int numcows = 100000;
        long numgrass = 100000;
        long range = 1000000000000000000L / numgrass;
        writer.println( numcows + " " + numgrass);
        for (int i=0; i < numgrass; i++) {
            writer.println((long)(i*range+1) + " " + (long)(i*range+1));
        }
        writer.println( 1000000000000000000L + " " + 1000000000000000000L );
        System.out.println("Expected: " + range);
    }
    static void tc4(PrintWriter writer) {
        int numcows = 100000;
        int numgrass = 50000;
        long range = 1000000000000000000L / numgrass;
        writer.println( numcows + " " + numgrass );
        for (int i=0; i < numgrass; i++) {
            writer.println((long)(i*range+1) + " " + (long)(i*range+2));
        }
        writer.println( (1000000000000000000L-range + 100L) + " " + 1000000000000000001L );
        System.out.println("Expected: " + range);
    }
    public static void main (String [] args) throws FileNotFoundException {
        String filename = "socdist";
        PrintWriter writer = new PrintWriter(new File(filename + ".in"));
        //tc1(writer);
        //tc2(writer);
        //tc3(writer);
        tc4(writer);
        writer.close();
    }

}