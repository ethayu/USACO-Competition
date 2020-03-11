import java.io.*;
import java.util.*;

public class Swap_Silver {

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "swap";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[] cows = new int[n];
        int[][] inputs = new int[m][2];
        boolean[] repeated = new boolean[n];
        int[] repeated_cycle = new int[n];
        ArrayList<int[]> record = new ArrayList<int[]>();
        //List<List<Integer>> record = new ArrayList<List<Integer>>();

        for (int i = 0; i < cows.length; i++) {
            cows[i] = i + 1;
            repeated[i] = false;
            repeated_cycle[i] = 0;
        }

        for (int i = 0; i < inputs.length; i++) {
            inputs[i][0] = sc.nextInt();
            inputs[i][1] = sc.nextInt();
        }
        //int[] first = cows.clone();
        int[] first = Arrays.copyOf(cows, cows.length);
        int finalrepeat = -1;
        int j = 0;
        int num_repeated_found = 0;

        //System.out.println(" m " + m + " k " + k);
        //for (int z=0; z < cows.length; z++) System.out.print(cows[z] + " "); System.out.println();
        for (int i = 0; i < k; i++) {
            //System.out.println(" i " + i);
            for (j=0; j < m; j++) {
                int begin = inputs[j][0];
                int end = inputs[j][1];
                for (int l = 0; l < (end - begin) / 2 + 1; l++) {
                    /*
                    cows[begin + l -1 ] = cows[begin + l -1]^cows[end -l -1]; //now a is 6 and b is 4
                    cows[end - l -1 ] = cows[begin + l -1]^cows[end -l -1]; //now a is 6 but b is 2 (original value of a)
                    cows[begin + l -1 ] = cows[begin + l -1]^cows[end -l -1]; //now a is 4 and b is 2, numbers are swapped
                    */
                    int temp = cows[begin + l - 1];
                    cows[begin + l - 1] = cows[end - l - 1];
                    cows[end - l - 1] = temp;
                    //System.out.println("i " + i + " j " + j + " l " + l);
                    //for (int z=0; z < cows.length; z++) System.out.print(cows[z] + " "); System.out.println();
                    /*
                    if (Arrays.equals(first, cows)) {
                        //i = k - k % (i + 1);
                        int stepsrepeating = i*m + j + 1;
                        finalrepeat = k * m - (k*m) % stepsrepeating;
                        //j = finalrepeat % m;
                        //i = finalrepeat / m;
                        int remaining_steps = k*m % stepsrepeating;
                        //System.out.println("REPEAT!!! i " + i + " j " + j + " l " + l + " k*m " + (k*m) + " stepsrepeating " + stepsrepeating + " last repeat " + finalrepeat + " remaining_steps " + remaining_steps);
                        i = finalrepeat / m;
                        j = finalrepeat % m;
                        //System.out.println("REPEAT!!! after i " + i + " j " + j );
                        l--;
                        break;
                    }
                    */
                }
                //System.out.println("    ****");
            }
            record.add(Arrays.copyOf(cows, cows.length));
            for (int a=0; a < n; a++) {
                if (!repeated[a] && cows[a] == a+1) {
                    repeated_cycle[a] = i+1;
                    repeated[a] = true;
                    num_repeated_found++;
                }
            }
            if (num_repeated_found == n) {
                int repeated_index = 0;
                for (int a=0; a < n; a++) {
                    if (repeated_cycle[a] == 1) {
                        repeated_index = 0;
                    } else {
                        repeated_index = (k % repeated_cycle[a]) - 1;
                        if (repeated_index == -1)
                            repeated_index = repeated_cycle[a]-1;
                    }
                    cows[a] = record.get(repeated_index)[a];
                }
                break;
            }
            //if (Arrays.equals(first, cows)) i = k - k % (i + 1);
            //else i++;
        }
        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        for (int a : cows) writer.println(a);
        //for (int z=0; z < cows.length; z++) System.out.print(cows[z] + " "); //System.out.println();
        writer.close();
    }

}