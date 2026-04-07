package gameLogic;

import java.util.Random;

public class NumberGeneration {

    private Grid grid;

    public NumberGeneration(Grid grid) {
        this.grid = grid;
    }

    public void newNum(int x_line, int x_bounds,
                       int y_column, int y_bounds) {

        Random num = new Random();

        int line_range;
        int column_range;

        int line_offset = 0;
        int column_offset = 0;

        if(x_line == 0){
            if(y_column < y_bounds){
                column_range = 2;
                line_range = grid.getWidth()-1;
                column_offset = grid.getWidth()-2;
            }
            else{
                column_range = 2;
                line_range = grid.getWidth()-1;
            }
        }
        else if(x_line == 1){
            line_range = 2;
            column_range = grid.getHeight()-1;
            line_offset = grid.getHeight()-2;
        }
        else {
            line_range = 2;
            column_range = grid.getHeight()-1;
        }
        System.out.println(line_range + " " + column_range);

        while(true){
            int x = num.nextInt(line_range);
            int y = num.nextInt(column_range);
            if (grid.grid[x + line_offset][y + column_offset] == 0) {
                grid.grid[x + line_offset][y + column_offset] = newRandNum();
                break;
            }
        }
    }

//Generates new numbers after moving | New number has a chance of being 2 or 4
    private static int newRandNum() {
        Random num = new Random();
        if (num.nextInt(11) > 9) {
            return 4;
        } else {
            return 2;
        }
    }
}
