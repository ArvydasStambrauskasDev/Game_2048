import gameLogic.*;

public class Main {
    public static void main(String[] args) {

        Grid grid = new Grid();
        Input input = new Input();
        NumberGeneration numberGeneration = new NumberGeneration(grid);
        Move move = new Move(grid, numberGeneration);
        Render render = new Render(grid);
        GameMechanics mechanics = new GameMechanics(move, grid, render);

        //Prints out instructions on controls and prints out starting game state
        mechanics.startControlPrint();

        //todo add comments on main loop function calls that explains why they are called
        while (true) {
            mechanics.winConditionCheck();  //todo fix this by checking variable after every addition (if grid[i][j] == 2048) OR make this run a function that goes through all grid variables and checks (at the end of move and if hasMoved was triggered)
            mechanics.lossConditionCheck(); //todo make this make sense in the implementation

            Command command = input.getCommand();

            mechanics.move(command); //todo look into default and "nothing" input
            render.printGrid();
        }
    }

}
