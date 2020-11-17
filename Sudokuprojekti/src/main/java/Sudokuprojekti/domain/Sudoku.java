package Sudokuprojekti.domain;

import java.util.*;

public class Sudoku {
    public int[][] sudoku;
    public int number;
    
    public Sudoku() {
        this.sudoku = new int[9][9];
        this.sudoku[0][4] = 4;
        this.sudoku[0][7] = 2;
        this.sudoku[1][1] = 5;
        this.sudoku[1][3] = 9;
        this.sudoku[2][1] = 1;
        this.sudoku[3][3] = 8;
        this.sudoku[5][6] = 9;
    }
    
    public int[][] getSudoku() {
        return this.sudoku;
    }
    
    public void setSudoku(int y, int x) {
        if (!checkSudoku(y,x)) this.number = -1;
        else this.sudoku[y][x] = this.number;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public void setNumberToSquare(int y, int x, int number) {
        this.sudoku[y][x] = number;
    }
    
    public boolean checkSudoku(int y, int x) {
        for (int i = 0; i < 9; i++) {
            if (i != x && this.sudoku[y][i] == this.number) return false;
            if (i != y && this.sudoku[i][x] == this.number) return false;
        }
        int squareStartY = y - y%3;
        int squareStartX = x - x%3;
        for (int i = squareStartY; i < squareStartY+3; i++) {
            for (int j = squareStartX; j < squareStartX+3; j++) {
                if ((i != y || j != x) && this.sudoku[i][j] == this.number) return false;
            }
        }
        return true;
    }
}
