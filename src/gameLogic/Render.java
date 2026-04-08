package gameLogic;

public class Render {

    private final Grid grid;

    public Render(Grid grid) {
        this.grid = grid;
    }

    public void printGrid() {
        System.out.print("_");

        for (int i = 0; i < grid.getHeight(); i++) {
            System.out.print("______");
        }

        System.out.println();

        for (int i = 0; i < grid.getHeight(); i++) {
            System.out.print("|");
            for (int j = 0; j < grid.getWidth(); j++) {
                if (grid.grid[i][j] == 0) {
                    System.out.print("     ");
                } else if (grid.grid[i][j] < 10) {
                    System.out.print("    " + grid.grid[i][j]);
                } else if (grid.grid[i][j] >= 10 && grid.grid[i][j] < 100) {
                    System.out.print("   " + grid.grid[i][j]);
                } else if (grid.grid[i][j] >= 100 && grid.grid[i][j] < 1000) {
                    System.out.print("  " + grid.grid[i][j]);
                } else {
                    System.out.print(" " + grid.grid[i][j]);
                }
                System.out.print("|");
            }

            System.out.println();
            System.out.print("|");

            for (int k = 0; k < grid.getHeight(); k++) {
                System.out.print("_____|");
            }

            System.out.println();
        }
        System.out.println();
    }
}
