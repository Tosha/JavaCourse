package com.company;

import java.util.Scanner;

public class Main {

    private static final String PLAYER_ONE = "X";
    private static final String PLAYER_TWO = "O";
    private static final String EMPTY_FIELD_SYMBOL = "_";

    private static final int FIELD_SIZE = 3;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[][] field = createInitialField();
        String currentPlayer = PLAYER_ONE;
        printField(field);
        while (!isGameFinished(field, currentPlayer)) {
            currentPlayer = switchPlayer(currentPlayer);
            makeMove(currentPlayer, field);
            printField(field);
        }
        printGameResult(field, currentPlayer);
    }

    private static void printField(String[][] field) {
        for (String[] fieldRows : field) {
            for (String rowValue : fieldRows) {
                System.out.print(rowValue + " ");
            }
            System.out.println();
        }
    }

    private static void makeMove(String currentPlayer, String[][] field) {
        System.out.println("Player " + currentPlayer + " Please select row and column");

        System.out.println("Select row:");
        int row = scanner.nextInt();

        System.out.println("Select column:");
        int column = scanner.nextInt();

        field[row][column] = currentPlayer;
    }

    private static boolean isGameFinished(String[][] field, String currentPlayer) {
        return isWinByRow(field, currentPlayer)
                || isWinByColumn(field, currentPlayer)
                || isWinByDiagonal(field, currentPlayer)
                || hasNoMoreMovesLeft(field);
    }

    // X X X      [0][0]   [1][0]    [2][0]
    // _ _ _      [0][1]   [1][1]    [2][1]
    // _ _ _      [0][2]   [1][2]    [2][2]

    private static boolean isWinByRow(String[][] field, String currentPlayer) {
        for (int rowIndex = 0 ; rowIndex < field.length; rowIndex++) {
            int matchCount = 0;
            for (int rowElementIndex = 0; rowElementIndex < field[rowIndex].length; rowElementIndex++) {
                if (field[rowIndex][rowElementIndex].equals(currentPlayer)) {
                    matchCount++;
                }
            }
            if (matchCount == FIELD_SIZE) {
                return true;
            }
        }
        return false;
    }

    // X _ _   [0][0]    [0][1]    [0][2]
    // X _ _   [1][0]    [1][1]    [1][2]
    // X _ _   [2][0]    [2][1]    [2][2]

    private static boolean isWinByColumn(String[][] field, String currentPlayer) {
        for (int rowNumber = 0; rowNumber < FIELD_SIZE; rowNumber++) {
            int counter = 0;
            for (int rowElement = 0; rowElement < FIELD_SIZE; rowElement++) {
                if (field[rowElement][rowNumber].equals(currentPlayer)) {
                    counter++;
                }
            }
            if (counter == FIELD_SIZE) {
                return true;
            }
        }
        return false;
    }

    private static boolean isWinByDiagonal(String[][] field, String currentPlayer) {
        return isWinByFirstDiagonal(field, currentPlayer) || isWinBySecondDiagonal(field, currentPlayer);
    }

    // X _ _      [0][0]
    // _ X _      [1][1]
    // _ _ X      [2][2]
    private static boolean isWinByFirstDiagonal(String[][] field, String currentPlayer) {
        int matchCount = 0;
        for (int index = 0; index < field.length; index++) {
            if (currentPlayer.equals(field[index][index])) {
                matchCount++;
            }
        }
        return matchCount == FIELD_SIZE;
    }

    // _ _ X        [2][0]
    // _ X _        [1][1]
    // X _ _        [0][2]
    private static boolean isWinBySecondDiagonal(String[][] field, String currentPlayer) {
        int matchCount = 0;
        for (int fieldRow = FIELD_SIZE - 1; fieldRow > -1; fieldRow--) {
            for (int rowEntry = 0; rowEntry < field.length; rowEntry++) {
                if (field[fieldRow][rowEntry].equals(currentPlayer)) {
                    matchCount++;
                }
            }
        }
        return matchCount == FIELD_SIZE;
    }

    private static boolean hasNoMoreMovesLeft(String[][] field) {
        for (int rowIndex = 0; rowIndex < field.length; rowIndex++) {
            for (int rowElementIndex = 0; rowElementIndex < field.length; rowElementIndex++) {
                if (field[rowIndex][rowElementIndex].equals(EMPTY_FIELD_SYMBOL)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String switchPlayer(String currentPlayer) {
        return currentPlayer.equals(PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }

    private static String[][] createInitialField() {
        String[][] field = new String[FIELD_SIZE][FIELD_SIZE];
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int rowElement = 0; rowElement < FIELD_SIZE; rowElement++) {
                field[row][rowElement] = EMPTY_FIELD_SYMBOL;
            }
        }
        return field;
    }

    private static void printGameResult(String[][] field, String currentPlayer) {
        if (hasNoMoreMovesLeft(field)) {
            System.out.println("It's a draw!");
        }
        System.out.println("Player " + currentPlayer + " wins!");
    }

}
