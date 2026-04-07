package gameLogic;

public class Move {

    private final Grid grid;
    private final NumberGeneration numGen;

    public Move(Grid grid, NumberGeneration numGen) {
        this.grid = grid;
        this.numGen = numGen;
    }

    private boolean is2048 = false;

    public boolean getIs2048() {
        return is2048;
    }

    public void move(int starting_line, int line_bounds, int move_direction_line, int line_offset,
                     int starting_column, int column_bounds, int move_direction_column, int column_offset) {

        boolean didMove = false;
        int line_axis = starting_line;

        for (int i = 0; i <= line_bounds; i++) {

            int column_axis = starting_column;

            for (int j = 0; j <= column_bounds; j++) {

                if (grid.grid[line_axis][column_axis] == grid.grid[line_axis + line_offset][column_axis + column_offset]
                        && grid.grid[line_axis][column_axis] != 0) {

                    grid.grid[line_axis + line_offset][column_axis + column_offset] += grid.grid[line_axis][column_axis];
                    grid.grid[line_axis][column_axis] = 0;
                    didMove = true;
                }

                if (grid.grid[line_axis][column_axis] != 0
                        && grid.grid[line_axis + line_offset][column_axis + column_offset] == 0) {

                    grid.grid[line_axis + line_offset][column_axis + column_offset] = grid.grid[line_axis][column_axis];
                    grid.grid[line_axis][column_axis] = 0;
                    didMove = true;
                }

                column_axis += move_direction_column;
            }

            line_axis += move_direction_line;
        }

        if(didMove){
            numGen.newNum(starting_line,line_bounds,starting_column,column_bounds);
        }
    }


    //===================================
    //Check if there is any possible move
    public boolean moveCheck() {
        boolean canMove = false;
        //CHECK MOVE RIGHT
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j > 0; j--) {
                if (grid.grid[i][j] == grid.grid[i][j - 1]) {
                    canMove = true;
                }
                if (grid.grid[i][j] == 0) {
                    canMove = true;
                }
            }
        }

        //CHECK MOVE LEFT
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid.grid[i][j] == grid.grid[i][j + 1]) {
                    canMove = true;
                }
                if (grid.grid[i][j] == 0) {
                    canMove = true;
                }
            }
        }

        //CHECK MOVE UP
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 3; j++) {
                if (grid.grid[i][j] == grid.grid[i + 1][j]) {
                    canMove = true;
                }
                if (grid.grid[i][j] == 0) {
                    canMove = true;
                }
            }
        }

        //CHECK MOVE DOWN
        for (int i = 3; i > 0; i--) {
            for (int j = 3; j >= 0; j--) {
                if (grid.grid[i][j] == grid.grid[i - 1][j]) {
                    canMove = true;
                }
                if (grid.grid[i][j] == 0) {
                    canMove = true;
                }
            }
        }
        return canMove;
    }
}

