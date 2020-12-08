import Sudokuprojekti.domain.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuServiceTest {
    SudokuService ss;
    Sudoku s;
    
    @Before
    public void setUp() {
        this.ss = new SudokuService();
        this.s = new Sudoku();
    }
    
    @Test
    public void constructorCreatesCorrectArrayList() {
        assertEquals(4, ss.sudokuList.size());
    }
    
    @Test
    public void createSudokuBaseMethodDoesNotChangeFirstSudokuOnTheList() {
        ss.createSudokuBase(0);
        assertEquals(0, ss.sudokuList.get(0).getSudoku()[0][0].getNumber());
    }
    
    @Test
    public void createSudokuBaseMethodPlacesRightValuesToSudoku() {
        int[][] values = ss.fsd.readValues("sudokufile.txt", 1);
        ss.createSudokuBase(1);
        assertEquals(values[1][1], ss.sudokuList.get(1).getSudoku()[1][1].getNumber());
    }
    
    
}
