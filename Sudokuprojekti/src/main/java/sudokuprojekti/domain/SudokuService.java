package sudokuprojekti.domain;

import sudokuprojekti.dao.FileSudokuDao;
import java.util.*;

/**
* Luokka, joka sisältää käyttöliittymän tarvitseman toiminnallisuuden
* 
*/
public class SudokuService {
    private FileSudokuDao fsd;
    private Sudoku sudoku;
    private ArrayList<Sudoku> sudokuList;
    private int saveIndex;
    
    /**
    * Konstruktori, joka lisää sudokulistalle 28 tyhjää sudokua
    * 
    * @param   fsd   FileSudokuDao-olio  
    */
    public SudokuService(FileSudokuDao fsd) {
        this.fsd = fsd;
        sudokuList = new ArrayList<>();
        for (int i = 0; i <= 28; i++) {
            sudokuList.add(new Sudoku());
        }
        this.saveIndex = 4;
        this.sudoku = sudokuList.get(0);
    }
    
    public Sudoku getSudoku() {
        return this.sudoku;
    }
    
    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
    
    public int getSaveIndex() {
        return this.saveIndex;
    }
    
    public void setSaveIndex(int index) {
        this.saveIndex = index;
    }
    
    public void getSudokuFromIndex(int x) {
        this.sudoku = this.sudokuList.get(x);
    }
    
    public ArrayList<Sudoku> getSudokuList() {
        return this.sudokuList;
    }
    
    /**
    * Metodi, joka lisää listalta valittavaan sudokuun arvot
    * sudokufile.txt-tiedostosta
    * 
    * @param   i   käyttäjän valitsema sudoku
    * 
    * @return Sudoku, johon on lisätty aloitusnumerot
    */
    public boolean createSudokuBase(int i) {
        getSudokuFromIndex(i);
        int[][] sudokuValues = fsd.readValues(i);
        String[][] sudokuNotation = fsd.readNotation(i);
        if (sudokuValues == null || sudokuNotation == null) {
            return false;
        }
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                this.sudoku.setNumberToSquare(y, x, sudokuValues[y][x]);
                if (sudokuValues[y][x] == 0) {
                    String notation = sudokuNotation[y][x];
                    if (!notation.equals("0")) {
                        charactersToIntegers(notation, y, x);
                    }
                } else {
                    this.sudoku.addOriginalNumberCoordinates(y, x);
                }
            }
        }
        return true;
    }
    
    /**
     * Metodi, joka ottaa parametrikseen tiedostosta saatavat yhden ruudun
     * sisältämät muistiinpanot ja muuttaa ne sudokun vaatimaan 
     * kokonaislukumuotoon
     * 
     * @param notation Tiedostosta saatavat muistiinpanot merkkijonona
     * @param y y-koordinaatti
     * @param x x-koordinaatti
     */
    public void charactersToIntegers(String notation, int y, int x) {
        for (int c = 0; c < notation.length(); c++) {
            String character = notation.charAt(c) + "";
            this.sudoku.setNotationToSquare(y, x, Integer.valueOf(character));
        }
    } 
    
    /**
     * Metodi, joka tallentaa käsitellyn sudokun tiedostoon
     * 
     * @return true, jos tiedostoon tallentaminen onnistuu, false, jos ei
     */
    public boolean saveSudoku() {
        return fsd.save(this.sudoku.getSudokuMatrix(), saveIndex);
    }
    
    /**
     * Metodi, joka tarkistaa, löytyykö parametria vastaavaa sudokua
     * tiedostosta
     * 
     * @param x Sudokun tunnus
     * @return true, jos sudoku löytyy, false, jos sudokua ei löydy 
     */
    public boolean isSudokuInMemory(int x) {
        return fsd.isSudokuInMemory(x);
    }
    
    /**
     * Metodi, joka kontrolloi ruutujen korostusta keltaisella värillä
     * 
     * @param value korostus päällä / pois päältä 
     */
    public void controlHighlight(boolean value) {
        if (value) {
            this.sudoku.setHighlight(true);
        } else {
            this.sudoku.setHighlight(false);
            this.sudoku.unhighlight();
        }
    }
    
    /**
     * Metodi, joka kontrolloi sudokuun lisättävän numeron kokoa
     * 
     * @param value suuri / pieni koko
     */
    public void controlSize(boolean value) {
        if (value) {
            this.sudoku.setSize(true);
        } else {
            this.sudoku.setSize(false);
        }
    }
    
    /**
     * Metodi, joka kontrolloi sudokuun lisättävää numeroa
     * 
     * @param number lisättävä numero
     */
    public void controlNumber(int number) {
        this.sudoku.setNumber(number);
    }
    
    /**
     * Metodi, joka lisää korostuksen sudokuun
     * 
     * @param y y-koordinaatti
     * @param x x-koordinaatti
     * 
     * @return true, jos korostus lisätty, false, jos ei lisätty
     */
    public boolean placeHighlightIfPossible(int y, int x) {
        if (this.sudoku.isHighlight() && this.sudoku.getSudokuMatrix()[y][x].getNumber() == 0) {
            this.sudoku.setNumberToSquare(y, x, -2);
            return true;
        }
        return false;
    }
    
    /**
     * Metodi, joka lisää numeron sudokuun
     * 
     * @param y y-koordinaatti
     * @param x x-koordinaatti
     * 
     * @return true, jos numero lisätty, false, jos ei lisätty
     */
    public boolean setNumberToSquareIfPossible(int y, int x) {
        if (!this.sudoku.isOriginalNumberCoordinates(y, x)) {
            this.sudoku.setNumberToSquare(y, x, this.sudoku.getNumber());
            return true;
        }
        return false;
    }
    
    /**
     * Metodi, joka lisää muistiinpanon sudokuun
     * 
     * @param y y-koordinaatti
     * @param x x-koordinaatti
     */
    public void controlSettingNotationToSquare(int y, int x) {
        this.sudoku.setNotationToSquare(y, x, this.sudoku.getNumber());
    }
}
 