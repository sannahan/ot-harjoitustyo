package sudokuprojekti.domain;

import java.util.*;

/**
 * Luokka sisältää sudokun tämänhetkisen tilan
 */
public class Sudoku {
    private Square[][] sudoku;
    private int number;
    private boolean big;
    private boolean highlight;
    private ArrayList<Coordinates> conflictingCoordinates;
    private ArrayList<Coordinates> originalNumbersCoordinates;
    
    /** 
    * Konstruktori luo Square-olioista koostuvan 9x9 matriisin
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
        this.originalNumbersCoordinates = new ArrayList<>();
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
    
    public ArrayList<Coordinates> getConflictingCoordinates() {
        return this.conflictingCoordinates;
    }
    
    public ArrayList<Coordinates> getOriginalNumberCoordinates() {
        return this.originalNumbersCoordinates;
    }
    
    /**
    * Metodi lisää sijainnin valmiiksi annettujen numeroiden sijaintien 
    * listalle
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    */
    public void addOriginalNumberCoordinates(int y, int x) {
        this.originalNumbersCoordinates.add(new Coordinates(y, x));
    }
    
    /**
    * Metodi tarkistaa, sijaitseeko y- ja x-koordinaatin määrittämässä
    * kohdassa valmiiksi annettu numero
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    * 
    * @return true, jos kohdassa on valmiiksi annettu numero, else muulloin
    */
    public boolean isOriginalNumberCoordinates(int y, int x) {
        Coordinates c = new Coordinates(y, x);
        if (this.originalNumbersCoordinates.contains(c)) {
            return true;
        }
        else {
            return false;
        }
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
    * Metodi lisää annetun numeron matriisin muistiinpanoihin
    *
    * @param   y   y-koordinaatti
    * @param   x   x-koordinaatti
    * @param   number   annettu numero
    */
    public void setNotationToSquare(int y, int x, int number) {
        this.sudoku[y][x].setNotation(number);
    }
    
    /**
    * Metodi rakentaa matriisin Square-oliosta saatavan
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
        boolean result = true;
        if (!checkRow(y, x)) {
            result = false;
        }
        if (!checkColumn(y, x)) {
            result = false;
        }
        if (!checkBox(y, x)) {
            result = false;
        }
        return result;
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
    
    /**
    * Metodi tarkastaa, onko kaikissa ruuduissa nollasta poikkeava numero
    * 
    * @return true, jos sudoku on täytetty, false, jos jossakin ruudussa on nolla
    */
    public boolean win() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (sudoku[y][x].getNumber() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
    * Metodi poistaa korostuksen korostetuista ruuduista
    *
    */
    public void unhighlight() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (sudoku[y][x].getNumber() == -2) {
                    this.setNumberToSquare(y, x, 0);
                }
            }
        }
    }
}