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

    //Check if there is any possible move
    //todo ok here is the plan - go row by row and check if there is any 0 or any values next to each other that are the same if so - return true
    // also then go column by column and check for same values next to each other and if there is - return true
    public boolean moveCheck() {
        int compareDimensionCoordinate;
        //look for 0 values or the same values next to each other
        for (int i = 0; i < grid.getHeight(); i++) {
            compareDimensionCoordinate = 0;
            for (int j = 1; j < grid.getWidth(); j++) {
                if (grid.grid[i][j] == 0 || grid.grid[i][compareDimensionCoordinate] == 0)
                    return true;
                else if (grid.grid[i][j] == grid.grid[i][compareDimensionCoordinate])
                    return true;

                if (grid.grid[j][i] == 0 || grid.grid[compareDimensionCoordinate][i] == 0)
                    return true;
                else if (grid.grid[j][i] == grid.grid[compareDimensionCoordinate][i])
                    return true;

                compareDimensionCoordinate++;
            }
        }

        return false;
    }


    public void move(String moveDirection) {
        boolean didMove = false;
        int auxiliaryDimensionStart = 9999;
        int auxiliaryDimensionEnd = 9999;
        int compareDimensionStart = 9999;
        int compareDimensionEnd = 9999;
        int auxiliaryDimensionIncrement = 0;
        int compareDimensionIncrement = 0;
        int compareColumnPosition;
        int compareRowPosition;
        int row;
        int column;

        //firstDimension is column or row where "move" happens. Depending on the direction "move" is the variables make the comparison from side to side, up or down in the grid array
        switch (moveDirection) {
            case "up":                                          //moving all values from the top left most comparing row by row in the column and then iterating through columns with same compare process
                auxiliaryDimensionStart = 0;                    //start of columns
                auxiliaryDimensionEnd = grid.getWidth();        //end of columns
                auxiliaryDimensionIncrement = 1;                //column iterator
                compareDimensionStart = 0;                      //Comparing values row by row in columns
                compareDimensionEnd = grid.getHeight();
                compareDimensionIncrement = 1;
                break;
            case "down":                                        //moving all values from the bottom left most comparing row by row moving up in the column and then iterating through columns with same compare process
                auxiliaryDimensionStart = 0;                    //start of columns
                auxiliaryDimensionEnd = grid.getWidth();        //end of columns
                auxiliaryDimensionIncrement = 1;                //column iterator
                compareDimensionStart = grid.getHeight() - 1;       //Comparing values row by row in columns
                compareDimensionEnd = -1;
                compareDimensionIncrement = -1;
                break;
            case "left":                                        //moving all values from the top left most comparing column by column and then iterating through rows with same compare process
                auxiliaryDimensionStart = 0;                    //start of rows
                auxiliaryDimensionEnd = grid.getHeight();       //end of rows
                auxiliaryDimensionIncrement = 1;                //row iterator
                compareDimensionStart = 0;                      //Comparing values column by column in a row
                compareDimensionEnd = grid.getWidth();
                compareDimensionIncrement = 1;
                break;
            case "right":                                       //moving all values from the top right most comparing column by column and then iterating through rows with same compare process
                auxiliaryDimensionStart = 0;                    //start of rows
                auxiliaryDimensionEnd = grid.getHeight();       //end of rows
                auxiliaryDimensionIncrement = 1;                //row iterator
                compareDimensionStart = grid.getWidth() - 1;        //Comparing values column by column in a row
                compareDimensionEnd = -1;
                compareDimensionIncrement = -1;
                break;
        }

        for (int i = auxiliaryDimensionStart; i != auxiliaryDimensionEnd; i += auxiliaryDimensionIncrement) {
            if (moveDirection.equals("up") || moveDirection.equals("down")) {
                compareRowPosition = compareDimensionStart;
                compareColumnPosition = i;
            } else {
                compareRowPosition = i;
                compareColumnPosition = compareDimensionStart;
            }

            for (int j = compareDimensionStart + compareDimensionIncrement; j != compareDimensionEnd; j += compareDimensionIncrement) {
                if (moveDirection.equals("up") || moveDirection.equals("down")) {
                    row = j;
                    column = i;
                } else {
                    column = j;
                    row = i;
                }

                //if the value is 0 this finds the next subsequent value that is not 0 and moves it to the spot where the initial 0 was
                if (grid.grid[compareRowPosition][compareColumnPosition] == 0) {
                    if (grid.grid[row][column] != 0) {
                        grid.grid[compareRowPosition][compareColumnPosition] = grid.grid[row][column];
                        grid.grid[row][column] = 0;

                        didMove = true;
                    }
                }

                //if the compare value is not 0 need to add is the following value is the same or move value it if it's different
                if (grid.grid[compareRowPosition][compareColumnPosition] != 0) {
                    //if the values are the same and next to each other or only separated by 0 in the row/column they should be added up
                    if (grid.grid[compareRowPosition][compareColumnPosition] == grid.grid[row][column]) {
                        grid.grid[compareRowPosition][compareColumnPosition] += grid.grid[row][column];
                        grid.grid[row][column] = 0;

                        if (moveDirection.equals("up") || moveDirection.equals("down"))
                            compareRowPosition += compareDimensionIncrement;
                        else
                            compareColumnPosition += compareDimensionIncrement;

                        didMove = true;
                    }
                    //if the values are next to each other or only separated by 0 in the row/column but are not the same - value needs to move next to non 0 value
                    if (grid.grid[compareRowPosition][compareColumnPosition] != grid.grid[row][column] && grid.grid[row][column] != 0 && compareColumnPosition != (compareDimensionEnd + compareDimensionIncrement)) {
                        if (moveDirection.equals("up") || moveDirection.equals("down")) {
                            compareRowPosition += compareDimensionIncrement;
                            j = compareRowPosition;
                        } else {
                            compareColumnPosition += compareDimensionIncrement;
                            j = compareColumnPosition;
                        }
                    }
                }
            }
        }

        if (didMove) {
            numGen.newNum(moveDirection);

        }

    }
}

