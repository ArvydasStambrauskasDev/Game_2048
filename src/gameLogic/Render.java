package gameLogic;

public class Render {

    private final Grid grid;
    final int maxSpaces = 5; //there are 5 spaces in one block in the output grid

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
                if (grid.grid[i][j] != 0){
                    //outputting only enough spaces to maintain grid structure (one-digit number needs 4 spaces, two-digit number needs 3 spaces, etc.)
                    for (int numberLen = Integer.toString(grid.grid[i][j]).length(); (maxSpaces - numberLen) > 0 ; numberLen++) {
                        System.out.print(" ");
                    }

                    System.out.print(grid.grid[i][j]);
                    System.out.print("|");
                }
                else
                    System.out.print("     |");

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
