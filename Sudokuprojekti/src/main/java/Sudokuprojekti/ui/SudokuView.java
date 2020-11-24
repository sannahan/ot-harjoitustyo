package Sudokuprojekti.ui;

import Sudokuprojekti.domain.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SudokuView extends Application {
    private Sudoku s;
    
    @Override
    public void init() {
        s = new Sudoku();
    }
    
    @Override
    public void start(Stage window) {
        BorderPane mainView = new BorderPane();
        
        VBox options = new VBox();
        
        HBox size = new HBox();
        Button small = new Button("Small");
        Button big = new Button("Big");
        size.getChildren().addAll(small, big);
        small.setOnAction((event) -> {
            s.setSize(false);
        });
        big.setOnAction((event) -> {
            s.setSize(true);
        });
        
        GridPane numberGrid = new GridPane();
        int numberInNumberGrid = 1;
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 6; j++) {
                Button b = new Button();
                if (numberInNumberGrid == 10) b.setText("X");
                else b.setText(String.valueOf(numberInNumberGrid));
                numberGrid.add(b, j, i);
                numberInNumberGrid++;
                
                b.setOnAction((event) -> {
                    String selectedNumber = b.getText();
                    if (!selectedNumber.equals("X")) s.setNumber(Integer.valueOf(selectedNumber));
                    else s.setNumber(0);
                });
            }
        }
        
        options.getChildren().add(size);
        options.getChildren().add(numberGrid);
        
        mainView.setRight(options);
        
        GridPane sudokuView = new GridPane();
        sudokuView.setVgap(0.5);
        sudokuView.setHgap(0.5);
        Square[][] sudoku = s.getSudoku();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int value = sudoku[y][x].getNumber();
                Button b = new Button();
                if (value != 0) {
                    b.setText(String.valueOf(value));
                }
                
                double buttonSize = 50.0;
                b.setMinWidth(buttonSize);
                b.setMinHeight(buttonSize);
                
                sudokuView.add(b, x, y);
                
                b.setOnAction((event) -> {
                    int currentNumber = s.getNumber();
                    int yClick = sudokuView.getRowIndex(b);
                    int xClick = sudokuView.getColumnIndex(b);
                    if (s.getSize()) {
                        b.setId("button-bigfont");
                        boolean valid = s.checkSudoku(yClick, xClick);
                        if (valid) {
                            s.setNumberToSquare(yClick, xClick, currentNumber);
                            if (currentNumber == 0) b.setText(" ");
                            else b.setText(String.valueOf(currentNumber));
                        } else {
                            b.setText("-1");
                        }
                    } else {
                        b.setId("button-smallfont");
                        if (currentNumber == 0) b.setText(" ");
                        else {
                            s.setNotationToSquare(yClick, xClick, currentNumber);
                            b.setText(s.getNotationFromSquare(yClick, xClick));
                        }
                    } 
                });
            }
        }
        
        mainView.setLeft(sudokuView);
        
        Scene view = new Scene(mainView, 700, 500);
        view.getStylesheets().add("SudokuStyle.css");
        window.setTitle("Welcome to play Sudoku!");
        window.setScene(view);
        window.show();
    }
    
    @Override
    public void stop() {
        
    }
}
