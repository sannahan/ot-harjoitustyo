import Sudokuprojekti.domain.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuTest {
    Sudoku s;
    
    @Before
    public void setUp() {
        s = new Sudoku();
    }
    
    /*@Test
    public void sameNumberOnRowReturnsFalse() {
        s.setNumberToSquare(2, 3, 8);
        s.setNumber(8);
        assertFalse(s.checkRow(2, 4));
    }
    
    @Test
    public void sameNumberOnColumnReturnsFalse() {
        s.setNumberToSquare(2, 3, 8);
        s.setNumber(8);
        assertFalse(s.checkColumn(3, 3));
    }
    
    @Test
    public void sameNumberInBoxReturnsFalse() {
        s.setNumberToSquare(1, 1, 8);
        s.setNumber(8);
        assertFalse(s.checkBox(2, 2));
    }*/
    
    @Test
    public void checkSudokuReturnsTrueIfNumberIsZero() {
        s.setNumber(0);
        assertTrue(s.checkSudoku(0, 0));
    }
    
    @Test
    public void constructorCreatesAMatrix() {
        Square[][] sudoku = s.getSudoku();
        assertEquals(9, sudoku[0].length);
    }
}
