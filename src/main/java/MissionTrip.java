public class MissionTrip {
    int conditions[][];
    int ways[][];
    int paths[][];


    public int resolve(int[][] paths) {
        this.paths = paths;
        this.conditions = new int[paths.length][paths[0].length];
        this.ways = new int[paths.length][paths[0].length];
        calculate(0, 0);
        return ways[paths.length - 1][paths[0].length - 1];
    }

    private void calculate(int i, int j) {
        conditions[i][j] = 1;
        ways[i][j]++;
        if (i >= 1 && conditions[i - 1][j] != 1 && paths[i - 1][j] == 0) {
            calculate(i - 1, j);
        }
        if (i < paths.length - 1 && conditions[i + 1][j] != 1 && paths[i + 1][j] == 0) {
            calculate(i + 1, j);
        }
        if (j >= 1 && conditions[i][j - 1] != 1 && paths[i][j - 1] == 0) {
            calculate(i, j - 1);
        }
        if (j < paths[0].length - 1 && conditions[i][j + 1] != 1 && paths[i][j + 1] == 0) {
            calculate(i, j + 1);
        }
        conditions[i][j] = 0;

    }

    public static void main(String[] args) {
        //long a=System.currentTimeMillis();
        MissionTrip missionTrip = new MissionTrip();
        //int[][] map = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        //int[][] map = {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}};
        int[][] map = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(missionTrip.resolve(map));
        //System.out.println((System.currentTimeMillis()-a)/1000f+ "秒");
    }

}
