import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class UnitedCowsofFarmerJohn_Gold {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cows = new int[n];
        int[] breeds = new int[n];
        HashSet<Integer> uniqueBreeds = new HashSet<>();
        int[] breedCount = new int[n];
        ArrayList<HashSet<Integer>> breed = new ArrayList<>();

        long ans = 0;

        for (int i = 0; i < n; i++) {
            cows[i] = sc.nextInt();
            if (i != 0 && cows[i] == cows[i - 1]) {
                breeds[cows[i]] = i;
                breedCount[cows[i]]++;
                continue;
            }
            if (!uniqueBreeds.contains(cows[i])) {
                if (i != 0) ans += uniqueBreeds.size();
                uniqueBreeds.add(cows[i]);
            } else if (i != 0) {
                HashSet<Integer> ret = new HashSet<>();
                if (breeds[cows[i]] > i - breeds[cows[i]] - 1) {
                    for (int j = breeds[cows[i]] + 1; j < i; j++) {
                        ret.add(cows[j]);
                    }
                    ans += ret.size();
                } else {
                    int[] temp = new int[n];
                    for (int j = 0; j <= breeds[cows[i]]; j++) {
                        if (uniqueBreeds.contains(cows[j])) {
                            if (++temp[cows[j]] == breedCount[cows[j]])
                                ret.add(cows[j]);
                        }
                    }
                    ans += uniqueBreeds.size() - ret.size();
                }
            }
            breeds[cows[i]] = i;
            breedCount[cows[i]]++;
        }

        System.out.println(ans);
    }

}
