package Sudokuprojekti.domain;

import java.util.*;

public class Sudoku {
    public Square[][] sudoku;
    public int number;
    public boolean big;
    public ArrayList<Coordinates> conflictingCoordinates;
    
    public Sudoku() {
        this.sudoku = new Square[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.sudoku[y][x] = new Square();
            }
        }
        this.sudoku[1][2].setNumber(1);
        this.sudoku[5][5].setNumber(1);
        this.sudoku[6][8].setNumber(1);
        this.sudoku[3][3].setNumber(3);
        this.sudoku[6][6].setNumber(3);
        this.sudoku[2][1].setNumber(5);
        this.sudoku[8][8].setNumber(9);
        this.sudoku[4][3].setNumber(7);
        this.sudoku[1][1].setNumber(7);
        this.sudoku[7][2].setNumber(2);
        this.sudoku[1][8].setNumber(6);
        this.conflictingCoordinates = new ArrayList<>();
    }
    
    public Square[][] getSudoku() {
        return this.sudoku;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public boolean isBig() {
        return this.big;
    }
    
    public void setSize(boolean isBig) {
        this.big = isBig;
    }
    
    public void setNumberToSquare(int y, int x, int number) {
        this.sudoku[y][x].setNumber(number);
    }
    
    public void setNotationToSquare(int y, int x, int number) {
        this.sudoku[y][x].setNotation(number);
    }
    
    public String getNotationFromSquare(int y, int x) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> notation = this.sudoku[y][x].getNotation();
        for (int i = 0; i < notation.size(); i++) {
            sb.append(notation.get(i));
        }
        return sb.toString();
    }
    
    public boolean checkSudoku(int y, int x) {
        if (this.number == 0) {
            return true;
        }
        this.conflictingCoordinates = new ArrayList<>();
        boolean noMistake = true;
        if (!checkRow(y, x)) {
            noMistake = false;
        }
        if (!checkColumn(y, x)) {
            noMistake = false;
        }
        if (!checkBox(y, x)) {
            noMistake = false;
        }
        return noMistake;
    }
    
    public boolean checkRow(int y, int x) {
        for (int i = 0; i < 9; i++) {
            if (i != x && this.sudoku[y][i].getNumber() == this.number) {
                this.conflictingCoordinates.add(new Coordinates(y, i));
                return false;
            }
        }
        return true;
    }
    
    public boolean checkColumn(int y, int x) {
        for (int i = 0; i < 9; i++) {
            if (i != y && this.sudoku[i][x].getNumber() == this.number) {
                this.conflictingCoordinates.add(new Coordinates(i, x));
                return false;
            }
        }
        return true;
    }
    
    public boolean checkBox(int y, int x) {
        int squareStartY = y - (y % 3);
        int squareStartX = x - (x % 3);
        for (int i = squareStartY; i < squareStartY + 3; i++) {
            for (int j = squareStartX; j < squareStartX + 3; j++) {
                if ((i != y || j != x) && this.sudoku[i][j].getNumber() == this.number) {
                    this.conflictingCoordinates.add(new Coordinates(i, j));
                    return false;
                }
            }
        }
        return true;
    }
    
    public ArrayList<Coordinates> getConflictingCoordinates() {
        return this.conflictingCoordinates;
    }
}