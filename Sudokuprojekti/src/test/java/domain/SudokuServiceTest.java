package domain;

import sudokuprojekti.dao.*;
import sudokuprojekti.domain.*;
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
        this.ss = new SudokuService(new FileSudokuDao("sudokufile.txt"));
        this.s = new Sudoku();
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
        ss.setSudoku(s);
    }
    
    @Test
    public void constructorCreatesCorrectArrayList() {
        assertEquals(29, ss.getSudokuList().size());
    }
    
    @Test
    public void createSudokuBaseMethodDoesNotChangeFirstSudokuOnTheList() {
        ss.createSudokuBase(0);
        assertEquals(0, ss.getSudokuList().get(0).getSudokuMatrix()[0][0].getNumber());
    }
    
    @Test
    public void placeHighlightReturnsTrueIfHighlightIsPossible() {
        s.setHighlight(true);
        assertTrue(ss.placeHighlightIfPossible(2, 2));
    }
    
    @Test
    public void placeHighlightReturnsFalseIfHighlightNotPossible() {
        assertFalse(ss.placeHighlightIfPossible(8, 8));
    }
    
    @Test
    public void charactersToIntegersReturnsCorrectNumbers() {
        ss.charactersToIntegers("123", 0, 0);
        int first = s.getSudokuMatrix()[0][0].getNotation().get(0);
        assertEquals(1, first);
    }
    
    @Test
    public void settingNumberToSquareWorksAndReturnsTrueWhenNumberCanBeAdded() {
        s.addOriginalNumberCoordinates(8, 8);
        s.setNumber(1);
        assertTrue(ss.setNumberToSquareIfPossible(7, 8));
        assertEquals(1, s.getSudokuMatrix()[7][8].getNumber());
    }
}
