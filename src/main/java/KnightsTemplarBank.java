import java.util.Arrays;

public class KnightsTemplarBank {

    /**
     * 返回圣殿骑士团银行职员可有的兑换方法总数以及
     * 最少使用多少枚不同面值钱币即可完成兑换
     *
     * @param amount  钱币数额
     * @param options 可以使用的面值，最小值未必是1，无特定排列顺序
     */
    public int[] resolve(int amount, int[] options) {
        int result[] = {-1, -1};
        int f[][] = new int[amount + 1][options.length];
        int n[][] = new int[amount + 1][options.length];
        if (amount > 0) {
            for (int i = 0; i <= amount; i++) {
                n[i][0] = amount;
                if (i % options[0] == 0) {
                    f[i][0] = 1;
                    n[i][0] = i / options[0];
                }
            }
            for (int i = 0; i <= amount; i++) {
                for (int j = 1; j < options.length; j++) {
                    f[i][j] = f[i][j - 1];
                    n[i][j] = amount;
                    if (i >= options[j]) {
                        int times = 1;
                        while (i >= times * options[j]) {
                            f[i][j] += f[i - times * options[j]][j - 1];
                            if (n[i][j] > n[i - times * options[j]][j - 1] + times) {
                                n[i][j] = n[i - times * options[j]][j - 1] + times;
                            }
                            times++;
                        }
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