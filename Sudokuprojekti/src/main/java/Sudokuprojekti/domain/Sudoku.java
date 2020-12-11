package Sudokuprojekti.domain;

import java.util.*;

/**
 * Luokka sisältää sudokun senhetkisen tilan
 */
public class Sudoku {
    /**
    * Square-olioista koostuva 9x9 matriisi
    */
    public Square[][] sudoku;
    /**
    * Numero, joka sudokuun halutaan lisätä
    */
    public int number;
    /**
    * Boolean, joka merkitsee, halutaanko sudokuun lisätä varsinainen numero vai muistiinpano
    */
    public boolean big;
    /**
    * Boolean, joka merkitsee, halutaanko sudokuun tehdä värikorostuksia
    */
    public boolean highlight;
    /**
    * Lista, joka sisältää tiedon sääntöjen vastaisten numeroiden koordinaateista
    */
    public ArrayList<Coordinates> conflictingCoordinates;
    
    /** 
    * Konstruktori luo Square-olioista koostuvan 9x9 matriisin
    * 
    * 
    */
    public Sudoku() {
        this.sudoku = new Square[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.sudoku[y][x] = new Square();
            }
        }
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
    
    public boolean isHighlight() {
        return this.highlight;
    }
    
    public void setHighlight(boolean highlightSelected) {
        this.highlight = highlightSelected;
    }
    
    /**
    * Metodi asettaa annetun numeron matriisiin
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    * @param   number   annettu numero
    */
    public void setNumberToSquare(int y, int x, int number) {
        this.sudoku[y][x].setNumber(number);
    }
    
    /**
    * Metodi lisää annetun numeron matriisiin muistiinpanona
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    * @param   number   annettu numero
    */
    public void setNotationToSquare(int y, int x, int number) {
        this.sudoku[y][x].setNotation(number);
    }
    
    /**
    * Metodi rakentaa matriisin tietystä Square-oliosta saatavan
    * muistiinpanolistan merkkijonoksi
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    * 
    * @return muistiin asetetut numerot merkkijonona
    */
    public String getNotationFromSquare(int y, int x) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> notation = this.sudoku[y][x].getNotation();
        for (int i = 0; i < notation.size(); i++) {
            sb.append(notation.get(i));
        }
        return sb.toString();
    }
    
    /**
    * Metodi tarkistaa, onko asetettava numero sudokun sääntöjen vastainen
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    * 
    * @return true, jos numeron voi asettaa ruutuun, false, jos numero on sääntöjen vastainen
    */
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
    
    /**
    * Metodi tarkistaa, onko samalla rivillä samaa numeroa
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    * 
    * @return true, jos numeron voi asettaa riville, false, jos numeroa ei voi asettaa riville
    */
    public boolean checkRow(int y, int x) {
        for (int i = 0; i < 9; i++) {
            if (i != x && this.sudoku[y][i].getNumber() == this.number) {
                this.conflictingCoordinates.add(new Coordinates(y, i));
                return false;
            }
        }
        return true;
    }
    
    /**
    * Metodi tarkistaa, onko samassa sarakkeessa samaa numeroa
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    * 
    * @return true, jos numeron voi asettaa sarakkeeseen, false, jos numeroa ei voi asettaa sarakkeeseen
    */
    public boolean checkColumn(int y, int x) {
        for (int i = 0; i < 9; i++) {
            if (i != y && this.sudoku[i][x].getNumber() == this.number) {
                this.conflictingCoordinates.add(new Coordinates(i, x));
                return false;
            }
        }
        return true;
    }
    
    /**
    * Metodi tarkastaa, onko samassa 3x3 ruudukossa samaa numeroa
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    * 
    * @return true, jos numeron voi asettaa ruutuun, false, jos numeroa ei voi asettaa ruutuun
    */
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
    
    public boolean win() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (sudoku[y][x].getNumber() == 0) return false;
            }
        }
        return true;
    }
}