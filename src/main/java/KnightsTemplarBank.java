import java.util.Arrays;

public class KnightsTemplarBank {
    public int[] resolve(int amount, int[] options) {
        int result[] = {-1, -1};
        int f[][] = new int[amount + 1][options.length];
        int n[][] = new int[amount + 1][options.length];
        if (amount > 0) {
            for (int i = 0; i <= amount; i++) {
                n[i][0] = 20000;
                if (i % options[0] == 0) {
                    f[i][0] = 1;
                    n[i][0] = i / options[0];
                }
            }
            for (int i = 0; i <= amount; i++) {
                for (int j = 1; j < options.length; j++) {
                    f[i][j] = f[i][j - 1];
                    n[i][j] = 20000;
                    int times = 1;
                    while (i >= times * options[j]) {
                        f[i][j] += f[i - times * options[j]][j - 1];
                        times++;

                    }
                    int compare = 0;
                    while (i >= compare * options[j]) {

                        if (n[i][j] > n[i - compare * options[j]][j - 1] + compare) {
                            n[i][j] = n[i - compare * options[j]][j - 1] + compare;
                        }
                        compare++;
                    }
                }
            }
        }


        if (f[amount][options.length - 1] > 0) {
            result[0] = f[amount][options.length - 1];
            result[1] = n[amount][options.length - 1];
        }
        return result;
    }

    public static void main(String[] args) {
        //long a=System.currentTimeMillis();

        KnightsTemplarBank bank = new KnightsTemplarBank();
        //int amount = 10000;
        int amount = 5;
        int[] options = {1, 2, 5, 10, 25};
        System.out.println(Arrays.toString(bank.resolve(amount, options)));

        //System.out.println((System.currentTimeMillis()-a)/1000f+ "秒");
    }
}