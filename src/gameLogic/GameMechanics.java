package gameLogic;

import java.util.Random;

public class GameMechanics {

    private Move move;
    private Grid grid;
    private Render render;

    public GameMechanics(Move move, Grid grid, Render render) {
        this.move = move;
        this.grid = grid;
        this.render = render;
    }

    private static int getRandomNum() {
        Random num = new Random();
        return num.nextInt(4);
    }

    public void gridGameStart(){
        //starting_numbers = initial numbers we need to print at the game start
        int starting_numbers = 2;

        for (int i=0; i<starting_numbers ;i++){
            int x = getRandomNum();
            int y = getRandomNum();
            if(grid.grid[y][x] == 0){
                grid.grid[y][x] = 2;
            }
            else {
                starting_numbers++;
            }
        }
    }

    public void start_control_print(){
        System.out.println("Game 2048\n"+
                           "Controls:\n"+
                           "w - up | a - left | s - down | d - right\n" +
                           "q - quit/end the game");
        gridGameStart();
        render.printGrid();
    }

    public void lossConditionCheck(){
        if(!move.moveCheck()) {
            gameEndLoss();
        }
    }

    public void gameEndLoss() {
       System.out.println("Game Over");
       System.exit(0);
}

    public void winConditionCheck() {
        if(move.getIs2048()) {
            gameEndWin();
        }
    }

    public void gameEndWin() {
        System.out.println("YOU WIN!!!");
        System.exit(0);
    }

//    int starting_line, int line_bounds, int move_direction_line, int line_offset,
//    int starting_column, int column_bounds, int move_direction_column, int column_offset
    public void move(Command command) {
        switch (command){
            case LEFT:
                move.move(0,  grid.getHeight()-1,   1,     0,
                        1,grid.getWidth()-2,1, -1);
                break;

            case RIGHT:
                move.move(                 0,  grid.getHeight()-1,    1,    0,
                         grid.getWidth()-2,grid.getWidth()-2,-1, 1);
                break;

            case UP:
                move.move(1,  grid.getHeight()-2,   1,   -1,
                        0,grid.getWidth()-1,1, 0);
                break;

            case DOWN:
                move.move(grid.getHeight()-2, grid.getHeight()-2,  -1,   1,
                                        0,grid.getWidth()-1,1,0);
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
