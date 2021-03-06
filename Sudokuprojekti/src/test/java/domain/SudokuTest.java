package domain;

import sudokuprojekti.domain.Sudoku;
import sudokuprojekti.domain.Square;
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
    }
    
    @Test
    public void setNumberSetsNumber() {
        s.setNumber(7);
        assertEquals(7, s.getNumber());
    }
    
    @Test
    public void setSizeSetsSize() {
        s.setSize(true);
        assertTrue(s.isBig());
    }
    
    @Test
    public void getNotationFromSquareReturnsCorrectSizedString() {
        int arraySize = s.getSudokuMatrix()[1][1].getNotation().size();
        int stringSize = s.getNotationFromSquare(1, 1).length();
        assertEquals(arraySize, stringSize);    
    }
    
    @Test
    public void sameNumberOnRowReturnsFalse() {
        s.setNumber(7);
        assertFalse(s.checkRow(1, 4));
    }
    
    @Test
    public void differentNumberOnRowReturnsTrue() {
        s.setNumber(4);
        assertTrue(s.checkRow(0,0));
    }
    
    @Test
    public void sameNumberOnColumnReturnsFalse() {
        s.setNumber(7);
        assertFalse(s.checkColumn(8, 1));
    }
    
    @Test
    public void differentNumberOnColumnReturnsTrue() {
        s.setNumber(4);
        assertTrue(s.checkColumn(0,0));
    }
    
    @Test
    public void sameNumberInBoxReturnsFalse() {
        s.setNumber(7);
        assertFalse(s.checkBox(2, 2));
    }
    
    public void differentNumberInBoxReturnsTrue() {
        s.setNumber(4);
        assertFalse(s.checkBox(0, 0));
    }
    
    @Test
    public void checkSudokuReturnsTrueIfNumberIsZero() {
        s.setNumber(0);
        assertTrue(s.checkSudoku(0, 0));
    }
    
    @Test
    public void checkSudokuReturnsTrueIfRowColumnAndBoxCheckReturnTrue() {
        s.setNumber(1);
        assertTrue(s.checkSudoku(8,3));
    }
    
    @Test
    public void checkSudokuReturnsFalseIfRowColumnOrBoxCheckReturnFalse() {
        s.setNumber(1);
        assertFalse(s.checkSudoku(1,5));
    }
    
    @Test
    public void constructorCreatesAMatrix() {
        Square[][] sudoku = s.getSudokuMatrix();
        assertEquals(9, sudoku[0].length);
    }
    
    @Test
    public void unHighlightRemovesAllHighLightMarkings() {
        s.setNumberToSquare(7, 7, -2);
        s.unhighlight();
        assertEquals(0, s.getSudokuMatrix()[7][7].getNumber());
    }
    
    @Test
    public void winReturnsFalseIfSomeNumbersAreZero() {
        assertFalse(s.win());
    }
    
    @Test
    public void isOriginalNumberCoordinatesReturnsTrueIfCoordinatesAreFound() {
        s.addOriginalNumberCoordinates(0, 0);
        s.addOriginalNumberCoordinates(1, 0);
        assertTrue(s.isOriginalNumberCoordinates(1, 0));
    }
    
    @Test
    public void isOriginalNumberCoordinatesReturnsFalseIfCoordinatesAreNotFound() {
        assertFalse(s.isOriginalNumberCoordinates(1, 1));
    }
}
