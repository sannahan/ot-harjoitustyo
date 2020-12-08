package Sudokuprojekti.domain;

import Sudokuprojekti.dao.*;
import Sudokuprojekti.domain.*;
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
    
    /**
    * Konstruktori, joka lisää sudokulistalle neljä tyhjää sudokua
    */
    public SudokuService() {
        this.fsd = new FileSudokuDao();
        sudokuList = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            sudokuList.add(new Sudoku());
        }
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
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                s.setNumberToSquare(y, x, sudokuValues[y][x]);
            }
        }
        return s;
    }
}
