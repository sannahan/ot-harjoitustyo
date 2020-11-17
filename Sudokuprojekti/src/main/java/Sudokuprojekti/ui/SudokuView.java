package Sudokuprojekti.ui;

import Sudokuprojekti.domain.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SudokuView extends Application {
    @Override
    public void start(Stage window) {
        BorderPane basicView = new BorderPane();
        
        Sudoku s = new Sudoku();
        
        GridPane selectValue = new GridPane();
        int selectNumber = 1;
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 6; j++) {
                Button b = new Button(String.valueOf(selectNumber));
                b.setPrefSize(20, 20);
                selectNumber++;
                selectValue.add(b, j, i);
                
                b.setOnAction((event) -> {
                    s.setNumber(Integer.valueOf(b.getText()));
                });
            }
        }
        
        Button clear = new Button("X");
        selectValue.add(clear, 5, 2);
        clear.setOnAction((event) -> {
            s.setNumber(0);
        });
        
        basicView.setRight(selectValue);
        
        GridPane sudokuView = new GridPane();
        int[][] sudoku = s.getSudoku();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int value = sudoku[y][x];
                Button b = new Button(" ");
                if (value != 0) {
                    b.setText(String.valueOf(value));
                }
                sudokuView.add(b, x, y);
                
                b.setOnAction((event) -> {
                    int currentNumber = s.getNumber();
                    s.setSudoku(sudokuView.getRowIndex(b), sudokuView.getColumnIndex(b));
                    if (currentNumber == 0) b.setText(" ");
                    else if (s.getNumber() == -1) {
                        b.setText("-1");
                        s.setNumber(currentNumber);
                    }
                    else b.setText(String.valueOf(currentNumber));
                });
            }
        }
        
        basicView.setLeft(sudokuView);
        
        basicView.setPrefSize(400, 180);
        
        Scene view = new Scene(basicView);
        
        window.setTitle("Welcome to play Sudoku!");
        window.setScene(view);
        window.show();
    } 
}
