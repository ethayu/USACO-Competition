import java.util.Scanner;

public class ModernArt3_Gold {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dp = new int[n][n];
        int[] painting = new int[n];
        for (int i = 0; i < n; i++) {
            painting[i] = sc.nextInt();
            dp[i][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j + i + 1 > n) continue;
                int min = Integer.MAX_VALUE;
                for (int k = j; k < i + j; k++) {
                    int ret = dp[j][k] + dp[k + 1][i + j];
                    if (painting[k] == painting[k + 1] || painting[j] == painting[i + j]) ret--;
                    min = Math.min(min, ret);
                }
                dp[j][i + j] = min;
            }
        }

        System.out.println(dp[0][n - 1]);

    }

}
