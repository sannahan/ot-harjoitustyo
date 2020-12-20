package sudokuprojekti.dao;

import sudokuprojekti.domain.Square;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
* Luokka, joka lukee ja kirjottaa tiedostoon
*/
public class FileSudokuDao {
    private String filename;
    
    public FileSudokuDao(String file) {
        this.filename = file;
    }
    
    /**
    * Metodi, joka lukee tiedostosta sudokun isot numerot
    * 
    * @param   chosen   valittu sudoku
    * 
    * @return 9x9 matriisi, joka sisältää aloitusnumerot
    */
    public int[][] readValues(int chosen) {
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
    
    /**
     * Metodi, joka lukee tiedostosta sudokun muistiinpanot
     * 
     * @param chosen Valittu sudoku
     * 
     * @return 9x9 matriisi, joka sisältää muistiinpanot merkkijonoina
     */
    public String[][] readNotation(int chosen) {
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
    
    /**
     * Metodi, joka tallentaa sudokun tiedostoon
     * 
     * @param sudoku sudokumatriisi
     * @param index valittu sudoku
     * 
     * @return true, jos tallennus onnistuu, false, jos ei 
     */
    public boolean save(Square[][] sudoku, int index) {
        boolean successful = false;
        try (FileWriter writer = new FileWriter(filename, true)) {
            String numbersString = createWritableLine(sudoku, index, false);
            String notationString = createWritableLine(sudoku, index, true);
            writer.write(numbersString + "\n");
            writer.write(notationString + "\n");
            successful = true;
        } catch (Exception e) {
            successful = false;
        }
        return successful;
    }
    
    /**
     * Metodi, joka muokkaa sudokun sisällön merkkojonoksi, joka 
     * voidaan kirjottaa tiedostoon
     * 
     * @param sudoku sudokumatriisi
     * @param index valittu sudoku
     * @param notation muistiinpano / varsinainen numero
     * 
     * @return sudokun sisältö merkkijonona 
     */
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
    
    /**
     * Metodi tarkistaa, löytyykö sudokupohjaa tiedostosta
     * 
     * @param x valittu sudoku
     * 
     * @return true, jos sudoku löytyy tiedostosta, false, jos ei
     */
    public boolean isSudokuInMemory(int x) {
        boolean found = false;
        try (Scanner s = new Scanner(new File(filename))) {
            while (s.hasNextLine()) {
                String[] parts = s.nextLine().split(";");
                if (parts[0].equals(String.valueOf(x))) {
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
