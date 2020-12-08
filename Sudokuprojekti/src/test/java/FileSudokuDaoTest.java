import Sudokuprojekti.domain.*;
import Sudokuprojekti.dao.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FileSudokuDaoTest {
    FileSudokuDao fsd;
    
    @Before
    public void setUp() {
        fsd = new FileSudokuDao();
    }
    
    @Test
    public void readValuesMethodReadsFileCorrectly() {
        assertEquals(8, fsd.readValues("sudokufile.txt", 1)[0][0]);
    }
}
