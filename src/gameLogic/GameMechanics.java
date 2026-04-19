package gameLogic;

import java.util.Random;

public class GameMechanics {

    private final Move move;
    private final Grid grid;
    private final Render render;

    public GameMechanics(Move move, Grid grid, Render render) {
        this.move = move;
        this.grid = grid;
        this.render = render;
    }

    private static int getRandomNum(int maxRange) {
        Random num = new Random();
        return num.nextInt(maxRange);
    }

    public void gridGameStart() {
        //startingNumbers is the initial number of non-zero values at game start
        int startingNumbers = 2;

        for (int i = 0; i < startingNumbers; i++) {
            int column = getRandomNum(grid.getWidth());
            int row = getRandomNum(grid.getHeight());
            if (grid.grid[row][column] == 0) {
                grid.grid[row][column] = 2;
            } else {
                startingNumbers++;
            }
        }
    }

    public void startControlPrint() {
        printControls();
        gridGameStart();
        render.printGrid();
    }

    public void printControls() {
        System.out.println("Game 2048\n" +
                "Controls:\n" +
                "w - up | a - left | s - down | d - right\n" +
                "q - end the game");
    }

    public void lossConditionCheck() {
        if (!move.moveCheck()) {
            gameEndLoss();
        }
    }

    public void gameEndLoss() {
        System.out.println("Game Over");
        System.exit(0);
    }

    public void winConditionCheck() {
        if (move.getIs2048()) {
            gameEndWin();
        }
    }

    public void gameEndWin() {
        System.out.println("CONGRATULATIONS!!! YOU WIN!!!");
        System.exit(0);
    }

    public void move(Command command) {
        switch (command) {
            case LEFT:
                move.move("left");
                break;

            case RIGHT:
                move.move("right");
                break;

            case UP:
                move.move("up");
                break;

            case DOWN:
                move.move("down");
                break;

            case QUIT:
                gameEndLoss();
                break;

            case NOTHING:
                printControls();
                break;

            default:
                break;
        }
    }
}
