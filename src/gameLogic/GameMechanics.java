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

    private static int getRandomNum() {
        Random num = new Random();
        return num.nextInt(4);
    }

    public void gridGameStart() {
        //starting_numbers = initial numbers we need to print at the game start
        int starting_numbers = 2;

        for (int i = 0; i < starting_numbers; i++) {
            int x = getRandomNum();
            int y = getRandomNum();
            if (grid.grid[y][x] == 0) {
                grid.grid[y][x] = 2;
            } else {
                starting_numbers++;
            }
        }
    }

    public void startControlPrint() {
        System.out.println("Game 2048\n" +
                "Controls:\n" +
                "w - up | a - left | s - down | d - right\n" +
                "q - quit/end the game");
        //gridGameStart();
        render.printGrid();
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
        System.out.println("YOU WIN!!!");
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
                break;

            default:
                break;
        }
    }
}
