package gameLogic;

import java.util.Scanner;

public class Input {
    private Scanner scanner = new Scanner(System.in);

    public Command getCommand(){
        char key = scanner.next().charAt(0);
        return getCommand(key);
    }

    public Command getCommand(char keyChar){

        switch (keyChar){
            case 'a':
                return Command.LEFT;
            case 'd':
                return Command.RIGHT;
            case 'w':
                return Command.UP;
            case 's':
                return Command.DOWN;
            case 'q':
                return Command.QUIT;
            default:
                return Command.NOTHING;

        }
    }

}
