package Sudokuprojekti.ui;

import Sudokuprojekti.domain.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SudokuView extends Application {
    private Sudoku s;
    private SudokuService sudokuService;
    
    public Scene view;
    public GridPane sudokuView;
    
    
    @Override
    public void init() {
        s = new Sudoku();
        sudokuService = new SudokuService();
    }
    
    @Override
    public void start(Stage window) {
        BorderPane mainView = new BorderPane();
        
        VBox options = new VBox();
        
        options.getChildren().add(createSizeSelection());
        options.getChildren().add(createNumberGrid());
        //options.getChildren().add(createHighlighter());
        options.getChildren().add(new Label("Choose your game!"));
        options.getChildren().add(createSudokuSelector());
        
        mainView.setRight(options);
        
        mainView.setLeft(maintainSudokuView());
        
        Scene view = new Scene(mainView, 700, 500);
        view.getStylesheets().add("SudokuStyle.css");
        window.setTitle("Welcome to play Sudoku!");
        window.setScene(view);
        window.show();
    }
    
    public HBox createSizeSelection() {
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
        return size;
    }
    
    public GridPane createNumberGrid() {
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
        return numberGrid;
    }
    
    /*public Button createHighlighter() {
        Button b = new Button("Highlight squares");
        b.setOnAction((event) -> {
            if (s.isHighlight()) {
                s.setHighlight(false);
            } else {
                s.setHighlight(true);
            }
        });
        return b;
    }*/
    
    public GridPane createSudokuSelector() {
        GridPane sudokuSelector = new GridPane();
        for (int y = 0; y < 3; y++) {
            Button b = new Button();
            if (y == 0) {
                b.setText("Easy");
            } else if (y == 1) {
                b.setText("Medium");
            } else {
                b.setText("Hard");
            }
            sudokuSelector.add(b, y, 0);
            b.setOnAction((event) -> {
                int chosenLevel = 1;
                if (b.getText().equals("Medium")) {
                    chosenLevel = 2;
                } else if (b.getText().equals("Hard")) {
                    chosenLevel = 3;
                }
                this.s = sudokuService.createSudokuBase(chosenLevel);
                drawSudoku();
            });
        }
        return sudokuSelector;
    }
    
    public GridPane maintainSudokuView() {
        this.sudokuView = new GridPane();
        sudokuView.setVgap(0.5);
        sudokuView.setHgap(0.5);
        
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Button b = new Button();
                
                double buttonSize = 50.0;
                b.setMinWidth(buttonSize);
                b.setMinHeight(buttonSize);
                
                sudokuView.add(b, x, y);
                
                drawSudoku();
                
                b.setOnAction((event) -> {
                    int currentNumber = s.getNumber();
                    int yClick = sudokuView.getRowIndex(b);
                    int xClick = sudokuView.getColumnIndex(b);
                    /*if (s.isHighlight()) {
                        b.setId("square-highlighted");
                    } else {
                        b.setId("square-notHighlighted");
                    }*/
                    if (s.isBig()) {
                        s.setNumberToSquare(yClick, xClick, currentNumber);
                        drawSudoku();
                        boolean valid = s.checkSudoku(yClick, xClick);
                        if (!valid) {
                            highlightMistakes(yClick, xClick);
                        }
                    } else {
                        s.setNotationToSquare(yClick, xClick, currentNumber);
                        drawSudoku();
                    }
                });
            }
        }
        return sudokuView;
    }
    
    public void drawSudoku() {
        Square[][] sudoku = s.getSudoku();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int value = sudoku[y][x].getNumber();
                ArrayList<Integer> notation = sudoku[y][x].getNotation();
                for (Node node: sudokuView.getChildren()) {
                    Button button = (Button) node;
                    int xCoord = sudokuView.getColumnIndex(button);
                    int yCoord = sudokuView.getRowIndex(button);
                    if (yCoord == y && xCoord == x) {
                        if (value != 0) {
                            button.setText(String.valueOf(value));
                            button.setId("button-bigfont");
                        } else if (!notation.isEmpty()) {
                            button.setText(s.getNotationFromSquare(y, x));
                            button.setId("button-smallfont");
                        } else {
                            button.setText(" ");
                            button.setId("button-bigfont");
                        }
                    }
                }
            }
        }
    }
    
    public void highlightMistakes(int yClick, int xClick) {
        Square[][] sudoku = s.getSudoku();
        ArrayList<Coordinates> mistakes = s.getConflictingCoordinates();
        for (Node button: sudokuView.getChildren()) {
            for (Coordinates yx: mistakes) {
                int columnIndex = sudokuView.getColumnIndex(button);
                int rowIndex = sudokuView.getRowIndex(button);
                if (columnIndex == yx.getX() || rowIndex == yx.getY()) {
                    Button here = (Button) button;
                    if (sudoku[rowIndex][columnIndex].getNumber() == sudoku[yClick][xClick].getNumber()) {
                        here.setId("button-mistakefont");
                    }
                } 
            }   
        }
    }
    
    @Override
    public void stop() {
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
