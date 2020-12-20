package dao;

import java.io.FileWriter;
import sudokuprojekti.dao.FileSudokuDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sudokuprojekti.domain.Sudoku;


public class FileSudokuDaoTest {
    FileSudokuDao fsd;
    Sudoku s;
    
    @Before
    public void setUp() {
        fsd = new FileSudokuDao("testfile.txt");
        s = new Sudoku();
        s.setNumberToSquare(1, 2, 1);
        s.setNumberToSquare(5, 5, 1);
        s.setNumberToSquare(6, 8, 1);
        s.setNumberToSquare(3, 3, 3);
        s.setNumberToSquare(6, 6, 3);
        s.setNumberToSquare(2, 1, 5);
        s.setNumberToSquare(8, 8, 9);
        s.setNumberToSquare(4, 3, 7);
        s.setNumberToSquare(1, 1, 7);
        s.setNumberToSquare(7, 2, 2);
        s.setNumberToSquare(1, 8, 6);
        s.setNumberToSquare(2, 4, 1);
        s.setNumberToSquare(0, 0, 8);
        fsd.save(s.getSudokuMatrix(), 1);
    }
    
    @Test
    public void readValuesMethodReadsFileCorrectly() { 
        assertEquals(8, fsd.readValues(1)[0][0]);
    }
    
    @Test
    public void createWritableLinePlacesSudokuValuesInCorrectPlaces() {
        String line = fsd.createWritableLine(s.getSudokuMatrix(), 30, false);
        String[] parts = line.split(";");
        assertEquals("1", parts[13]);
    }
    
    @Test
    public void createWritableLineWritesCorrectIndex() {
        String line = fsd.createWritableLine(s.getSudokuMatrix(), 1, false);
        String[] parts = line.split(";");
        assertEquals("1", parts[0]);
    }
    
    @Test
    public void isSudokuInMemoryReturnsTrueIfSudokuFound() {
        assertTrue(fsd.isSudokuInMemory(1));
    }
    
    @After
    public void tearDown() throws Exception {
        try (FileWriter fw = new FileWriter("testfile.txt", false)) {
            fw.write("");
        }
    }
}