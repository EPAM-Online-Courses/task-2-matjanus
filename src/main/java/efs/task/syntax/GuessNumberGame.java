package efs.task.syntax;


import java.util.Scanner;

public class GuessNumberGame {
    private int remainingAttempts;
    private final int numberOfAttempts;
    private final int M;
    private final int randomNumber;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            M = Integer.parseInt(argument);
        } catch (java.lang.NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new java.lang.IllegalArgumentException();
        }

        if (M > UsefulConstants.MAX_UPPER_BOUND || M < 1) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new java.lang.IllegalArgumentException();
        }

        numberOfAttempts = (int) (Math.log(M) / Math.log(2)) + 1;
        remainingAttempts = numberOfAttempts;
        randomNumber = (int) (Math.random() * (M - 1)) + 1;
    }

    public void play() {
        String inputString;
        int inputInt;
        boolean won = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("<1," + M + ">");
        while (remainingAttempts != 0) {
            remainingAttempts--;
            printAttemptsBar();
            System.out.println(UsefulConstants.GIVE_ME);

            inputString = scanner.nextLine();
            try {
                inputInt = Integer.parseInt(inputString);
            } catch (java.lang.NumberFormatException e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                continue;
            }
            if (inputInt == randomNumber) {
                won = true;
                System.out.println(UsefulConstants.YES);
                break;
            } else if (inputInt > randomNumber) {
                System.out.println(UsefulConstants.TO_MUCH);
            } else {
                System.out.println(UsefulConstants.TO_LESS);
            }

        }
        if (won) {
            System.out.print(UsefulConstants.CONGRATULATIONS);
        } else {
            System.out.print(UsefulConstants.UNFORTUNATELY);
        }
    }

    private void printAttemptsBar() {
        System.out.print('[');
        for (int i = this.numberOfAttempts; i > this.remainingAttempts; i--) {
            System.out.print('*');
        }
        for (int i = this.remainingAttempts; i > 0; i--) {
            System.out.print('.');
        }
        System.out.print("]\r\n");
    }
}
