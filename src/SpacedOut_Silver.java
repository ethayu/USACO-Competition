import java.io.PrintWriter;
import java.util.Scanner;

public class SpacedOut_Silver {

    static int n, ans;
    static int[][] grid;
    static int[] template;

    static void generate (int place) {
        if (place == template.length) {
            int[][] possibility = new int[n][n];
            for (int i = 0; i < n; i++) {
                possibility[i][0] = template[i];
            }
            for (int i = 0; i < n - 1; i++) {
                possibility[0][i + 1] = template[i + n];
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    int count = 0;
                    if (possibility[i][j] == 1) count++;
                    if (possibility[i + 1][j] == 1) count++;
                    if (possibility[i][j + 1] == 1) count++;
                    if (possibility[i + 1][j + 1] == 1) count++;
                    if (count == 3 || count == 0) return;
                    else if (count == 1) possibility[i + 1][j + 1] = 1;
                }
            }
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (possibility[i][j] == 1) sum += grid[i][j];
                }
            }
            ans = Integer.max(ans, sum);
        } else {
            template[place] = 1;
            generate(place + 1);
            template[place] = 0;
            generate(place + 1);
        }
    }

    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        PrintWriter printer = new PrintWriter(System.out);

        n = sc.nextInt();
        template = new int[n * 2 - 1];
        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        generate(0);

        printer.println(ans);
        printer.close();

    }

}
