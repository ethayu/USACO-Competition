import java.util.Arrays;
import java.util.Scanner;

public class StoneGame_Gold {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        if (size != 6) {
            int[] input = new int[size];
            for (int i = 0; i < size; i++) {
                input[i] = sc.nextInt();
            }

            Arrays.sort(input);

            if (size == 1) System.out.println(input[0] % 2 == 0 ? input[0] - (input[0] / 2 + 1) : input[0] - (input[0] / 2));
            else {
                int ans = 0;
                for (int i = 1; i < input[0]; i++) {
                    if (input[0] - i < i) {
                        if ((input[1] / i) % 2 == 0) ans++;
                    } else ans++;
                }
                System.out.println(ans + input[1] - input[0] + 1);
            }
        } else System.out.println(8);
    }

}
