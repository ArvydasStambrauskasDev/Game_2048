package gameLogic;

public class Grid {
/*
    int[][] grid = {
       {0, 0, 0, 0}, //0
       {0, 0, 0, 0}, //1
       {0, 0, 0, 0}, //2
       {0, 0, 0, 0}, //3
//      0  1  2  3
    };
*/

    int[][] grid = {
            {2, 4, 8, 16, 32}, //0
            {4, 8, 16, 32, 64}, //1
            {100, 16, 32, 64, 128}, //2
            {2, 4, 8, 16, 32}, //3
            {0, 8, 16, 32, 64}, //4
    //       0  1  2  3  4
    };

    public int getHeight() {
        return grid.length;
    }

    public int getWidth() {
        return grid[0].length;
    }
}

