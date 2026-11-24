package gameLogic;

import java.util.Random;

public class NumberGeneration {

    private final Grid grid;

    public NumberGeneration(Grid grid) {
        this.grid = grid;
    }

    public void newNum() {

        Random num = new Random();

        while (true) {
            int row = num.nextInt(grid.getHeight());
            int column = num.nextInt(grid.getWidth());
            if (grid.grid[row][column] == 0) {
                grid.grid[row][column] = newRandNum();
                break;
            }
        }
    }

    //Generates new numbers after moving | New number has a chance being 2 or 4
    private static int newRandNum() {
        Random num = new Random();

        if (num.nextInt(10) == 0) {
            return 4;
        } else {
            return 2;
        }
    }
}
