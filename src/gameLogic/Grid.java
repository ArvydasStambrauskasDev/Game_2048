package gameLogic;

public class Grid {

    int[][] grid = {
            {0, 0, 0, 0}, //0
            {0, 0, 0, 0}, //1
            {0, 0, 0, 0}, //2
            {0, 0, 0, 0}, //3
     //      0  1  2  3
    };

    public int getHeight() {
        return grid.length;
    }

    public int getWidth() {
        return grid[0].length;
    }
}

