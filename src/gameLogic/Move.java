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

    /*
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
*/

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


    public boolean move(String moveDirection) {
        boolean didMove = false;
        int firstDimensionStart = 0;
        int firstDimensionEnd = 0;
        int secondDimensionStart = 0;
        int secondDimensionEnd = 0;
        int firstDimensionIncrement = 1;
        int secondDimensionIncrement = 1;
        int compareDimensionPosition;
        int compareDimensionIterator;
        int moveAxisPosition;
        int row = 0;
        int column = 0;

        //firstDimension is column or row where "move" happens. Depending on the direction "move" is the variables make the comparison from side to side, up or down in the grid array
        switch (moveDirection) {
            case "up":
                firstDimensionStart = 0;                        //First dimension is rows
                firstDimensionEnd = grid.getWidth();
                firstDimensionIncrement = 1;
                secondDimensionStart = 0;                       //Second dimension is columns
                secondDimensionEnd = grid.getHeight();
                secondDimensionIncrement = 1;
                break;
            case "down":
                firstDimensionStart = 0;                        //First dimension is rows
                firstDimensionEnd = grid.getWidth();
                secondDimensionStart = grid.getHeight() - 1;    //Second dimension is columns
                secondDimensionEnd = -1;
                secondDimensionIncrement = -1;
                break;
            case "left":
                firstDimensionStart = 0;                        //First dimension is columns
                firstDimensionEnd = grid.getHeight();
                secondDimensionStart = 0;                       //Second dimension is rows
                secondDimensionEnd = grid.getWidth();
                secondDimensionIncrement = 1;
                break;
            case "right":
                firstDimensionStart = 0;                        //First dimension is the columns
                firstDimensionEnd = grid.getHeight();
                secondDimensionStart = grid.getWidth() - 1;     //Second dimension is rows
                secondDimensionEnd = -1;
                secondDimensionIncrement = -1;
                break;
        }

//todo remake for loop logic to be != and not <= becuase it has a problem with starting at max -1 grid value
//ALSO up is acting as left and down is acting as right. NEED row comparePositionStart because only var in the j dimension doesnt work for 2 cases
        for (int i = firstDimensionStart; i != firstDimensionEnd; i += firstDimensionIncrement) {
            if (moveDirection.equals("up") || moveDirection.equals("down")) {
                compareDimensionPosition = i;
                moveAxisPosition = secondDimensionStart;
                compareDimensionIterator = secondDimensionIncrement;
                column = i;
            } else {
                compareDimensionPosition = secondDimensionStart;
                moveAxisPosition = i;
                compareDimensionIterator = firstDimensionIncrement;
                row = i;
            }
            for (int j = secondDimensionStart; j != secondDimensionEnd; j += secondDimensionIncrement) {

                if (moveDirection.equals("up") || moveDirection.equals("down"))
                    row = j;
                else
                    column = j;

                 /*
                if (grid.grid[moveAxisPosition][compareDimensionPosition] == 0) {
                    if (grid.grid[row][column] != 0) {
                        grid.grid[moveAxisPosition][compareDimensionPosition] = grid.grid[row][column];
                        grid.grid[row][column] = 0;

                        didMove = true;
                    }
                }
                // if the starting value is not 0 need to add is the following value is the same or move value it if it's different
                if (grid.grid[moveAxisPosition][compareDimensionPosition] != 0) {
                    // if the values are the same and are only separated by 0 - they should be added up
                    if (grid.grid[moveAxisPosition][compareDimensionPosition] == grid.grid[row][column]) {
                        grid.grid[moveAxisPosition][compareDimensionPosition] += grid.grid[row][column];
                        grid.grid[row][column] = 0;

                        compareDimensionPosition += compareDimensionIterator;
                        didMove = true;
                    }
                    // if the values are only separated by 0 but are not the same - value needs to move next to non 0 value
                    if (grid.grid[moveAxisPosition][compareDimensionPosition] != grid.grid[row][column] && grid.grid[row][column] != 0 && compareDimensionPosition != secondDimensionEnd) {
                        j = compareDimensionPosition;
                        didMove = true;
                    }
                }
                */
            }
        }

        return didMove;
    }
}

