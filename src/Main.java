import gameLogic.*;

public class Main {
    public static void main(String[] args) {

        Grid grid = new Grid();
        Input input = new Input();
        NumberGeneration numberGeneration = new NumberGeneration(grid);
        Move move = new Move(grid,numberGeneration);
        Render render = new Render(grid);
        GameMechanics mechanics = new GameMechanics(move,grid,render);


        mechanics.start_control_print();

        while (true){
            mechanics.winConditionCheck();
            mechanics.lossConditionCheck();

            Command command = input.getCommand();

            mechanics.move(command);
            render.printGrid();
        }
    }

}
