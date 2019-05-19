import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;
import java.util.HashMap;

public class Monastery {

    private WeightedQuickUnionUF uf(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;
        int numbers = rows * cols;
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(numbers);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int room = i * cols + j;
                if ((rooms[i][j] & 1) == 0 && j >= 1) {
                    int nextRoom = room - 1;
                    uf.union(room, nextRoom);
                }
                if ((rooms[i][j] & 2) == 0 && i >= 1) {
                    int nextRoom = room - cols;
                    uf.union(room, nextRoom);
                }
                if ((rooms[i][j] & 4) == 0 && j + 1 < cols) {
                    int nextRoom = room + 1;
                    uf.union(room, nextRoom);
                }
                if ((rooms[i][j] & 8) == 0 && i + 1 < cols) {
                    int nextRoom = room + cols;
                    uf.union(room, nextRoom);
                }
            }
        }
        return uf;
    }

    public int[] resolve(int[][] rooms) {
        int result[] = new int[6];
        int numbers = rooms.length * rooms[0].length;
        WeightedQuickUnionUF uf = uf(rooms);
        result[0] = uf.count();
        result[1] = getMaxArea(uf, numbers);
        int moreArea = result[1];
        int rowOfRoom = 0;
        int colOfRoom = 0;
        int wallOfRoom = 0;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 1; j < rooms[0].length; j++) {
                if ((rooms[i][j] & 1) != 0) {
                    int room = i * rooms[0].length + j;
                    int nextRoom = room - 1;
                    uf.union(room, nextRoom);
                    if (getMaxArea(uf, numbers) > moreArea) {
                        moreArea = getMaxArea(uf, numbers);
                        rowOfRoom = i + 1;
                        colOfRoom = j + 1;
                        wallOfRoom = 1;
                    }
                    uf = uf(rooms);
                }
            }
        }
        for (int j = 0; j < rooms[0].length; j++) {
            for (int i = rooms.length - 1; i >= 1; i--) {
                if ((rooms[i][j] & 2) != 0) {
                    int room = i * rooms[0].length + j;
                    int nextRoom = room - rooms[0].length;
                    uf.union(room, nextRoom);
                    if (getMaxArea(uf, numbers) > moreArea||(getMaxArea(uf, numbers) >= moreArea && j < colOfRoom - 1)) {
                        moreArea = getMaxArea(uf, numbers);
                        rowOfRoom = i + 1;
                        colOfRoom = j + 1;
                        wallOfRoom = 2;
                    }

                    uf = uf(rooms);
                }
            }
        }

        result[2] = moreArea;
        result[3] = rowOfRoom;
        result[4] = colOfRoom;
        result[5] = wallOfRoom;
        return result;
    }

    public int getMaxArea(WeightedQuickUnionUF uf, int numbers) {
        HashMap<Integer, Integer> area = new HashMap<>();

        for (int i = 0; i < numbers; i++) {
            int id = uf.find(i);
            if (area.get(id) != null) {
                area.put(id, area.get(id) + 1);
            } else {
                area.put(id, 1);
            }
        }
        int maxArea = 0;
        for (int id : area.values()) {
            if (id > maxArea) {
                maxArea = id;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Monastery monastery = new Monastery();
        int rooms[][] = {{3, 2, 6, 3, 6}, {1, 8, 4, 1, 4}, {13, 7, 13, 9, 4}, {3, 0, 2, 6, 5}, {9, 8, 8, 12, 13}};
        System.out.println(Arrays.toString(monastery.resolve(rooms)));

    }

}
