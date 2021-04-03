public class temp {

    public static void main(String[] args) {
        for (int i = 0; i < 27; i++) {
            System.out.println();
            for (int j = 0; j < 27; j++) {
                boolean ok = true;
                if (i > j) {
                    for (int k = 0; k < i / 3 + 1; k++) {
                        double b = (int) (j / Math.pow(3, k)) % 3;
                        double a = (int) (i / Math.pow(3, k)) % 3;
                        if ((a == 1 && b == 0) ||
                                (a == 1 && b == 2) ||
                                (a == 2 && b == 1) ||
                                (a == 0 && b == 1)) {
                            ok = false;
                            System.out.println("numbers: " + i + " " + j + " " + k);

                            break;
                        }
                    }
                    //System.out.print(ok ? 1 : 0);
                } else if (j > i) {
                    for (int k = 0; k < j / 3 + 1; k++) {
                        double b = (int) (j / Math.pow(3, k)) % 3;
                        double a = (int) (i / Math.pow(3, k)) % 3;
                        if ((a == 1 && b == 0) ||
                                (a == 1 && b == 2) ||
                                (a == 2 && b == 1) ||
                                (a == 0 && b == 1)) {
                            ok = false;
                            System.out.println("numbers: " + i + " " + j + " " + k);

                            break;
                        }
                    }
                   // System.out.print(ok ? 1 : 0);
                } //else System.out.print(1);
            }
        }
    }

}
