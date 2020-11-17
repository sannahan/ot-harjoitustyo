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
    
    @Test
    public void sameNumberOnSameColumnReturnsFalse() {
        s.setNumber(8);
        assertFalse(s.checkSudoku(2,3));
    }
}
