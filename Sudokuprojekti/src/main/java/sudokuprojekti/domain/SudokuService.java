package sudokuprojekti.domain;

import sudokuprojekti.dao.FileSudokuDao;
import java.util.*;

/**
* Luokka, joka toimii rajapintana käyttöliittymän ja dao-pakkauksen välillä
*/
public class SudokuService {
    /**
    * FileSudokuDao
    */
    public FileSudokuDao fsd;
    /**
    * Lista sudokuista
    */
    public ArrayList<Sudoku> sudokuList;
    public int saveIndex;
    /**
    * Konstruktori, joka lisää sudokulistalle neljä tyhjää sudokua
    */
    public SudokuService() {
        this.fsd = new FileSudokuDao();
        sudokuList = new ArrayList<>();
        for (int i = 0; i <= 28; i++) {
            sudokuList.add(new Sudoku());
        }
        this.saveIndex = 4;
    }
    
    /**
    * Metodi, joka lisää listalta valittavaan sudokuun arvot
    * sudokufile.txt-tiedostosta
    * 
    * @param   i   käyttäjän valitsema sudoku
    * 
    * @return Sudoku, johon on lisätty aloitusnumerot
    */
    public Sudoku createSudokuBase(int i) {
        Sudoku s = this.sudokuList.get(i);
        int[][] sudokuValues = fsd.readValues("sudokufile.txt", i);
        String[][] sudokuNotation = fsd.readNotation("sudokufile.txt", i);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                s.setNumberToSquare(y, x, sudokuValues[y][x]);
                if (sudokuValues[y][x] == 0) {
                    String notation = sudokuNotation[y][x];
                    if (!notation.equals("0")) {
                        for (int c = 0; c < notation.length(); c++) {
                            s.setNotationToSquare(y, x, Integer.valueOf(notation.charAt(i)));
                        }
                    }
                } else {
                    s.addOriginalNumberCoordinates(y, x);
                }
            }
        }
        return s;
    }
    
    public boolean saveSudoku(Square[][] sudoku) {
        fsd.save("sudokufile.txt", sudoku, saveIndex);
        return true;
    }
    
    public int getSaveIndex() {
        return this.saveIndex;
    }
    
    public void increaseSaveIndex() {
        this.saveIndex++;
    }
    
    public boolean isSudokuInMemory(int x) {
        return fsd.isSudokuInMemory("sudokufile.txt", x);
    }
    
    public Sudoku getSudokuFromIndex(int x) {
        return this.sudokuList.get(x);
    }
}
 