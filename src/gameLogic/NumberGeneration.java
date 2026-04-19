package gameLogic;

import java.util.Random;

public class NumberGeneration {

    private final Grid grid;

    public NumberGeneration(Grid grid) {
        this.grid = grid;
    }

    /*
        public void newNum(int x_line, int x_bounds,
                           int y_column, int y_bounds) {

            Random num = new Random();

            int lineRange;
            int columnRange;

            int lineOffset = 0;
            int columnOffset = 0;

            if(x_line == 0){
                if(y_column < y_bounds){
                    columnRange = 2;
                    lineRange = grid.getWidth()-1;
                    columnOffset = grid.getWidth()-2;
                }
                else{
                    columnRange = 2;
                    lineRange = grid.getWidth()-1;
                }
            }
            else if(x_line == 1){
                lineRange = 2;
                columnRange = grid.getHeight()-1;
                lineOffset = grid.getHeight()-2;
            }
            else {
                lineRange = 2;
                columnRange = grid.getHeight()-1;
            }
            System.out.println(lineRange + " " + columnRange);

            while(true){
                int x = num.nextInt(lineRange);
                int y = num.nextInt(columnRange);
                if (grid.grid[x + lineOffset][y + columnOffset] == 0) {
                    grid.grid[x + lineOffset][y + columnOffset] = newRandNum();
                    break;
                }
            }
        }
    */
    public void newNum(String moveDirection) {

        Random num = new Random();

        while (true) {
            int row = num.nextInt(grid.getWidth()-1);
            int column = num.nextInt(grid.getHeight()-1);
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
