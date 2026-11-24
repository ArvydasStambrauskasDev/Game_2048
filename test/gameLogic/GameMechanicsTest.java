package gameLogic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameMechanicsTest {

    private Grid grid;
    private GameMechanics mechanics;

    @Before
    public void setUp() {
        grid = new Grid();
        NumberGeneration numberGeneration = new NumberGeneration(grid);
        Move move = new Move(grid, numberGeneration);
        Render render = new Render(grid);
        mechanics = new GameMechanics(move, grid, render);
    }

    private int countNonEmptyCells() {
        int count = 0;
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int column = 0; column < grid.getWidth(); column++) {
                if (grid.grid[row][column] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private int[][] copyGrid() {
        int[][] copy = new int[grid.getHeight()][];
        for (int row = 0; row < grid.getHeight(); row++) {
            copy[row] = grid.grid[row].clone();
        }
        return copy;
    }

    @Test
    public void gridGameStartPlacesTwoStartingNumbers() {
        mechanics.gridGameStart();

        assertEquals(2, countNonEmptyCells());
        for (int row = 0; row < grid.getHeight(); row++) {
            for (int column = 0; column < grid.getWidth(); column++) {
                int value = grid.grid[row][column];
                assertTrue(value == 0 || value == 2);
            }
        }
    }

    @Test
    public void leftCommandMovesNumberToLeftAndAddsNewNumber() {
        grid.grid[0][3] = 2;

        mechanics.move(Command.LEFT);

        assertEquals(2, grid.grid[0][0]);
        assertEquals(2, countNonEmptyCells());
    }

    @Test
    public void commandWithoutPossibleMoveDoesNotChangeGrid() {
        grid.grid[0][0] = 2;
        int[][] before = copyGrid();

        mechanics.move(Command.LEFT);

        assertArrayEquals(before, grid.grid);
    }
}
