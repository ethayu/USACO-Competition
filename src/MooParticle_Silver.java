
import java.io.*;
import java.util.*;

public class MooParticle_Silver {

    static HashSet<Integer> all = new HashSet<>();

    static class mparticle {

        int spin1;
        int spin2;

        mparticle(int a, int b) {spin1 = a; spin2 = b;}
    }

    static class info {

        HashSet<Integer> possible = new HashSet<>();
        int index;

    }

    public static void main (String [] args) throws FileNotFoundException {

        String filename = "moop";
        Scanner sc = new Scanner(new File(filename + ".in"));

        int n = sc.nextInt();
        info[] arraylis = new info[n];
        mparticle[] mparticles = new mparticle[n];
        for (int i = 0; i < n; i++) {
            mparticles[i] = new mparticle(sc.nextInt(), sc.nextInt());
            arraylis[i] = new info();
            arraylis[i].index = i;
            for (int j = 0; j < i; j++) {
                if (arraylis[i].possible.contains(j)) continue;
                if ((mparticles[i].spin1 == mparticles[j].spin1) || (mparticles[i].spin2 == mparticles[j].spin2) ||
                        ((mparticles[i].spin1 < mparticles[j].spin1) && (mparticles[i].spin2 < mparticles[j].spin2)) ||
                        ((mparticles[i].spin1 > mparticles[j].spin1) && (mparticles[i].spin2 > mparticles[j].spin2))) {
                    for (int a : arraylis[j].possible) {
                        arraylis[i].possible.add(a);
                        arraylis[a].possible.add(i);
                    }
                    arraylis[i].possible.add(j);
                    arraylis[j].possible.add(i);
                }
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) if (arraylis[n].possible.size() > arraylis[max].possible.size()) max = n;

        HashSet<Integer> notaccounted = (HashSet<Integer>) all.clone();
        notaccounted.removeAll(arraylis[max].possible);

        for (int a : notaccounted) {
            boolean broke = false;
            for (int b : arraylis[a].possible) {
                if (arraylis[max].possible.contains(b)) {
                    broke = true;
                    break;
                }
                if (!broke);
            }
        }

        /**Arrays.sort(arraylis, new Comparator<info>() {
            @Override
            public int compare(info info, info t1) {
                return info.possible.size() - t1.possible.size();
            }
        });

        int counter = 0;

        for (int i = 0; i < n; i++) {
            if (ingroup.size() == n) break;
            if (ingroup.contains(arraylis[i].index)) continue;
            ingroup.add(i);
            if (arraylis[i].possible.size() == 0) {
                counter++;
                continue;
            }
            boolean subtracted = false;
            for (int j : arraylis[i].possible) {
                if (!subtracted && (ingroup.contains(j))) {
                    counter--;
                    subtracted = true;
                }
                ingroup.add(j);
            }
            counter++;
        }**/


        PrintWriter writer = new PrintWriter(new File(filename + ".out"));
        writer.println();
        writer.close();

    }

}


