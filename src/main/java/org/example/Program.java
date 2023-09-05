package org.example;

import java.util.Random;
import java.util.Scanner;

public class Program {
    private static final char DOT_HUMAN = 'X';//фишка человека
    private static final char DOT_AI = '0';//фишка компьютера
    private static final char DOT_EMPLY = '*';//признак пустого поля
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;//двумерный массив хранит состояние игрового поля
    private static int fieldSizeX;//размерность игрового поля
    private static int fieldSizeY;//размерность игрового поля


    public static void main(String[] args) {
        field = new char[5][];

        while (true) {
            initalize();
            printFiend();
            while (true) {
                humanTurn();
                printFiend();
                if (checkGameState1(DOT_HUMAN,"You win"))
                    break;
                aiTurn();
                printFiend();
                if (checkGameState1(DOT_AI,"You lose"))
                    break;
            }
            System.out.print("Играем еще раз?Y-да");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }

    }

    private static void initalize() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPLY;
            }
        }
    }

    /*
     * Отрисовка игрового поля
     *
     * */
    private static void printFiend() {
        System.out.print("+");
        for (int x = 0; x < fieldSizeX * 2 + 1; x++) {
            System.out.print((x % 2 == 0) ? "-" : x / 2 + 1);
        }
        System.out.println();

        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int x = 0; x < fieldSizeX * 2 + 2; x++) {
            System.out.print("-");
        }
        System.out.println();
    }


    /**
     * обработка хода человека
     */
    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты хода Х и У");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!inCellVallid(x, y) || !isCEllEmply(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка ячейки на доступность хода DOT_EMPLY
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isCEllEmply(int x, int y) {
        return field[x][y] == DOT_EMPLY;

    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать игрового поля)
     */
    private static boolean inCellVallid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y <= fieldSizeY;
    }


    /***
     *Обработка хода компьютера
     */
    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCEllEmply(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка состояния игры
     *
     * @param c фишка игрока
     */
    private static boolean checkGameState1(char c, String s) {
        if (checkWin1(c)||checkWin2(c)||checkWin3(c)||checkWin4(c)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья");
            return true;
        }
        return false;// игра продолжается
    }


    /**
     * Проверка ничьей
     */
    private static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCEllEmply(x, y))
                    return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы вертикаль
     */

    private static boolean checkWin1(char c) {
        int win = 4;
        int i ;
        for (int x = 0; x < fieldSizeX; x++) {
            i=0;
            for (int y = 0; y < fieldSizeY; y++) {
                if (field[x][y] == c) {
                    i++;
                } else {
                    i = 0;
                }
                if (i == win) return true;
            }
        }
        return false;
    }
    /**
     * Проверка победы горизонталь
     */

    private static boolean checkWin2(char c) {
        int win = 4;
        int i ;
        for (int y = 0; y < fieldSizeY; y++) {
            i=0;
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[x][y] == c) {
                    i++;
                } else {
                    i = 0;
                }
                if (i == win) return true;
            }
        }
        return false;
    }

    /**
     * Проверка победы диагональ вниз
     */

    private static boolean checkWin3(char c) {
        int win = 4;
        int i=0; ;
        int x=0;
        int y=0;
        while (inCellVallid(x,y)) {
            if (field[x][y] == c) {
                i++;

            }
            else {
                i=0;
            }
            x++;
            y++;
            if (i == win) return true;
        }
        return false;
    }

    /**
     * Проверка победы диагональ вверх
     */
    private static boolean checkWin4(char c) {
        int win = 4;
        int i=0; ;
        int x=fieldSizeX-1;
        int y=0;
        while (inCellVallid(x,y)) {
            if (field[x][y] == c) {
                i++;

            }
            else {
                i=0;
            }
            x--;
            y++;
            if (i == win) return true;
        }
        return false;
    }

}

