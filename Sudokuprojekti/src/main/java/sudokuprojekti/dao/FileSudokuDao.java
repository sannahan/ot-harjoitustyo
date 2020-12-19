package sudokuprojekti.dao;

import sudokuprojekti.domain.Square;
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
                if (Integer.valueOf(values[0]) == chosen && Integer.valueOf(values[1]) == 1) {
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
    
    // keksi tälle järkevämpi ratkaisu, jottei tule copypastea
    public String[][] readNotation(String filename, int chosen) {
        String[][] sudokuNotations = new String[9][9];
        try (Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                String[] notations = reader.nextLine().split(";");
                if (Integer.valueOf(notations[0]) == chosen && Integer.valueOf(notations[1]) == 2) {
                    int notationIndex = 2;
                    for (int y = 0; y < 9; y++) {
                        for (int x = 0; x < 9; x++) {
                            sudokuNotations[y][x] = notations[notationIndex];
                            notationIndex++;
                        }
                    }
                }
            }
            return sudokuNotations;
        } catch (Exception e) {
            return null;
        }
    }
    
    public void save(String filename, Square[][] sudoku, int index) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            String numbersString = createWritableLine(sudoku, index, false);
            String notationString = createWritableLine(sudoku, index, true);
            writer.write(numbersString + "\n");
            writer.write(notationString + "\n");
        } catch (Exception e) {
            System.out.println("Tiedostoon ei voitu kirjoittaa!");
        }
    }
    
    public String createWritableLine(Square[][] sudoku, int index, boolean notation) {
        StringBuilder sb = new StringBuilder();
        sb.append(index + ";");
        if (!notation) {
            sb.append(1 + ";");
        } else {
            sb.append(2 + ";");
        }
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (!notation) {
                    sb.append(sudoku[y][x].getNumber() + ";");
                } else {
                    ArrayList<Integer> notationList = sudoku[y][x].getNotation();
                    if (!notationList.isEmpty()) {
                        for (int i = 0; i < notationList.size(); i++) {
                            sb.append(notationList.get(i));
                        }
                        sb.append(";");
                    } else {
                        sb.append(0 + ";");
                    }
                }
            }
        }
        return sb.toString();
    }
    
    public boolean isSudokuInMemory(String filename, int x) {
        boolean found = false;
        try (Scanner s = new Scanner(new File(filename))) {
            while (s.hasNextLine()) {
                String[] parts = s.nextLine().split(";");
                if (parts[0].equals(String.valueOf(x+3))) {
                    found = true;
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return found;
    }
}
