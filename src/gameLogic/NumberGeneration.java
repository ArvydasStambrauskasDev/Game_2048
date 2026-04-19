package gameLogic;

import java.util.Random;

public class NumberGeneration {

    private final Grid grid;

    public NumberGeneration(Grid grid) {
        this.grid = grid;
    }

    public void newNum(String moveDirection) {

        Random num = new Random();

        while (true) {
            int row = num.nextInt(grid.getWidth() - 1);
            int column = num.nextInt(grid.getHeight() - 1);
            if (grid.grid[row][column] == 0) {
                grid.grid[row][column] = newRandNum();
                break;
            }
        }
    }

    //Generates new numbers after moving | New number has a chance being 2 or 4
    private static int newRandNum() {
        int chanceOfBeingTwo = 9;           //90% chance of new number being 2
        Random num = new Random();
        if (num.nextInt(11) > 9) {
            return 4;
        } else {
            return 2;
        }
    }
}
