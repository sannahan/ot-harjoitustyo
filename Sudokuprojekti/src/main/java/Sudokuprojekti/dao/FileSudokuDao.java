package Sudokuprojekti.dao;

import Sudokuprojekti.domain.*;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
* Luokka, joka lukee tiedostosta aloitusnumerot
*/
public class FileSudokuDao {
    /**
    * Metodi, joka lukee valitusta tiedostosta numeroita
    * 
    * @param   filename   käyttäjän valitsema tiedosto
    * @param   chosen   käyttäjän valitsema sudoku
    * 
    * @return 9x9 matriisi, joka sisältää aloitusnumerot
    */
    public int[][] readValues(String filename, int chosen) {
        int[][] sudokuValues = new int[9][9];
        try (Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                String[] values = reader.nextLine().split(";");
                if (Integer.valueOf(values[0]) == chosen) {
                    int valueIndex = 2;
                    for (int y = 0; y < 9; y++) {
                        for (int x = 0; x < 9; x++) {
                            sudokuValues[y][x] = Integer.valueOf(values[valueIndex]);
                            valueIndex++;
                        }
                    }
                }
            }
            return sudokuValues;
        } catch (Exception e) {
            return null;
        }
    }  
}
