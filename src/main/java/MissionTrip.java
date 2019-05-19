public class MissionTrip {
    int conditions[][] = new int[5][5];
    int ways[][] = new int[5][5];


    public int resolve(int[][] paths) {
        calculate(0, 0, paths);
        return ways[paths.length - 1][paths[0].length - 1];
    }

    private void calculate(int i, int j, int[][] paths) {
        conditions[i][j] = 1;
        ways[i][j]++;
        if (i >= 1 && conditions[i - 1][j] != 1 && paths[i - 1][j] == 0) {
            calculate(i - 1, j, paths);
        }
        if (i < paths.length - 1 && conditions[i + 1][j] != 1 && paths[i + 1][j] == 0) {
            calculate(i + 1, j, paths);
        }
        if (j >= 1 && conditions[i][j - 1] != 1 && paths[i][j - 1] == 0) {
            calculate(i, j - 1, paths);
        }
        if (j < paths[0].length - 1 && conditions[i][j + 1] != 1 && paths[i][j + 1] == 0) {
            calculate(i, j + 1, paths);
        }
        conditions[i][j] = 0;

    }

    public static void main(String[] args) {
        //long a=System.currentTimeMillis();

        MissionTrip missionTrip = new MissionTrip();
        int[][] map = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        //int[][] map = {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}};
        //int[][] map = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(missionTrip.resolve(map));

        //System.out.println((System.currentTimeMillis()-a)/1000f+ "秒");
    }

}
