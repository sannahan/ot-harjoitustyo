package sudokuprojekti.ui;

import java.io.FileInputStream;
import sudokuprojekti.dao.*;
import sudokuprojekti.domain.*;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class SudokuView extends Application {
    private SudokuService sudokuService;
    private GridPane sudokuView;
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String sudokuFile = properties.getProperty("sudokuFile");
        FileSudokuDao fsd = new FileSudokuDao(sudokuFile);
        sudokuService = new SudokuService(fsd);
    }
    
    @Override
    public void start(Stage window) {
        BorderPane mainView = new BorderPane();
        
        VBox options = new VBox(10);
        
        options.getChildren().add(createSizeSelection());
        options.getChildren().add(createNumberGrid());
        options.getChildren().add(createHighlighter());
        options.getChildren().add(new Label("Choose your game!"));
        options.getChildren().add(createSudokuSelector());
        options.getChildren().add(new Label("Find your saved games below!"));
        options.getChildren().add(createGridForSavedSudokus());
        options.getChildren().add(createSudokuSaver());

        mainView.setRight(options);
        
        Image grid = new Image("file:black.png", 500, 500, false, false);
        ImageView viewImage = new ImageView(grid);
        StackPane gameView = new StackPane();
        gameView.getChildren().addAll(viewImage, maintainSudokuView());
        mainView.setLeft(gameView);
        
        Scene view = new Scene(mainView, 800, 515);
        view.getStylesheets().add("SudokuStyle.css");
        window.setTitle("A Sudoku a Day Keeps the Doctor Away!");
        window.setScene(view);
        window.show();
    }
    
    public HBox createSizeSelection() {
        HBox size = new HBox();
        Button small = new Button("Small");
        Button big = new Button("Big");
        size.getChildren().addAll(small, big);
        small.setOnAction((event) -> {
            sudokuService.controlSize(false);
        });
        big.setOnAction((event) -> {
            sudokuService.controlSize(true);
        });
        return size;
    }
    
    public GridPane createNumberGrid() {
        GridPane numberGrid = new GridPane();
        int numberInNumberGrid = 1;
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 6; j++) {
                Button b = new Button();
                b.setMinSize(35, 35);
                if (numberInNumberGrid == 10) {
                    b.setText("X");
                }
                else {
                    b.setText(String.valueOf(numberInNumberGrid));
                }
                numberGrid.add(b, j, i);
                numberInNumberGrid++;
                
                b.setOnAction((event) -> {
                    String selectedNumber = b.getText();
                    if (!selectedNumber.equals("X")) {
                        sudokuService.controlNumber(Integer.valueOf(selectedNumber));
                    }
                    else {
                        sudokuService.controlNumber(0);
                    }
                });
            }
        }
        return numberGrid;
    }
    
    public HBox createHighlighter() {
        HBox highlightSelector = new HBox();
        Button highlight = new Button("Highlight squares");
        highlight.setOnAction((event) -> {
            sudokuService.controlHighlight(true);
        });
        Button unhighlight = new Button("Unhighlight all");
        unhighlight.setOnAction((event) -> {
            sudokuService.controlHighlight(false);
            drawSudoku();
        });
        highlightSelector.getChildren().addAll(highlight, unhighlight);
        return highlightSelector;
    }
    
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
                sudokuService.createSudokuBase(chosenLevel);
                drawSudoku();
            });
        }
        return sudokuSelector;
    }
    
    public HBox createSudokuSaver() {
        HBox sudokuSaver = new HBox();
        Button save = new Button("Save");
        save.setOnAction((event) -> {
            boolean successful = sudokuService.saveSudoku();
            if (!successful) {
                messagePopup("Tiedostoon ei voitu kirjoittaa. Yritä uudelleen.");
            }
        });
        sudokuSaver.getChildren().add(save);
        return sudokuSaver;
    }
    
    public GridPane createGridForSavedSudokus() {
        GridPane saved = new GridPane();
        int number = 1;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                Button b = new Button(String.valueOf(number));
                b.setMinSize(35, 35);
                saved.add(b, y, x);

                b.setOnAction((event) -> {
                    int yClick = saved.getRowIndex(b);
                    int xClick = saved.getColumnIndex(b);
                    int index = (yClick * 5) + xClick + 4;
                    sudokuService.setSaveIndex(index);
                    if (sudokuService.isSudokuInMemory(index)) {
                        boolean successful = sudokuService.createSudokuBase(index);
                        if (!successful) {
                            messagePopup("Sudokua ei voitu lukea tiedostosta. Yritä uudelleen.");
                        }
                        drawSudoku();
                        b.setId("selectedSudoku");
                    } else {
                        sudokuService.getSudokuFromIndex(index);
                        drawSudoku();
                    }
                });
                number++;
            }
        }
        return saved;
    }
    
    public GridPane maintainSudokuView() {
        this.sudokuView = new GridPane();
        sudokuView.setVgap(3);
        sudokuView.setHgap(3);
        sudokuView.setPadding(new Insets(15, 10, 10, 10));
        
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Button b = new Button();
                
                double buttonSize = 50.0;
                b.setMinWidth(buttonSize);
                b.setMinHeight(buttonSize);
                
                if ((x == 3 && y == 3) || (x == 3 && y == 6) || (x == 6 && y == 3) || (x == 6 && y == 6)) {
                    sudokuView.setMargin(b, new Insets(5, 0, 0, 5));
                } else if (x == 3 || x == 6) {
                    sudokuView.setMargin(b, new Insets(0, 0, 0, 5));
                } else if (y == 3 || y == 6) {
                    sudokuView.setMargin(b, new Insets(5, 0, 0, 0));
                }
                
                sudokuView.add(b, x, y);
                
                drawSudoku();
                
                b.setOnAction((event) -> {
                    int yClick = sudokuView.getRowIndex(b);
                    int xClick = sudokuView.getColumnIndex(b);
                    if (sudokuService.placeHighlightIfPossible(yClick, xClick)) {
                        drawSudoku();
                        return;
                    }
                    if (sudokuService.getSudoku().isBig()) {
                        if (sudokuService.setNumberToSquareIfPossible(yClick, xClick)) {
                            drawSudoku();
                        }
                        boolean valid = sudokuService.getSudoku().checkSudoku(yClick, xClick);
                        if (!valid) {
                            if (!sudokuService.getSudoku().isOriginalNumberCoordinates(yClick, xClick)) {
                                highlightMistakes(yClick, xClick);
                            }
                        } else {
                            if (sudokuService.getSudoku().win()) {
                                messagePopup("Congratulations! You have finished the game successfully.");
                            }
                        }
                    } else {
                        sudokuService.controlSettingNotationToSquare(yClick, xClick);
                        drawSudoku();
                    }
                });
            }
        }
        return sudokuView;
    }
    
    public void drawSudoku() {
        Square[][] sudoku = sudokuService.getSudoku().getSudokuMatrix();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int value = sudoku[y][x].getNumber();
                ArrayList<Integer> notation = sudoku[y][x].getNotation();
                for (Node node: sudokuView.getChildren()) {
                    Button button = (Button) node;
                    int xCoord = sudokuView.getColumnIndex(button);
                    int yCoord = sudokuView.getRowIndex(button);
                    if (yCoord == y && xCoord == x) {
                        if (value == -2) {
                            button.setId("square-highlighted");
                        } else if (value != 0) {
                            button.setText(String.valueOf(value));
                            if (sudokuService.getSudoku().isOriginalNumberCoordinates(yCoord, xCoord)) {
                                button.setId("button-bigfontWithColor");
                            }
                            else {
                                button.setId("button-bigfont");
                            }
                        } else if (!notation.isEmpty()) {
                            button.setText(sudokuService.getSudoku().getNotationFromSquare(y, x));
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
        Square[][] sudoku = sudokuService.getSudoku().getSudokuMatrix();
        ArrayList<Coordinates> mistakes = sudokuService.getSudoku().getConflictingCoordinates();
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
    
    public void messagePopup(String message) {
        Stage messageWindow = new Stage();
        VBox layout = new VBox(10);
        Button close = new Button("Close");
        close.setOnAction((event) -> {
            messageWindow.close();
        });
        layout.getChildren().addAll(new Label(message), close);
        layout.setAlignment(Pos.CENTER);
        messageWindow.initModality(Modality.APPLICATION_MODAL);
        messageWindow.setTitle("Message!");
        Scene scene = new Scene(layout, 300, 250);
        messageWindow.setScene(scene);
        messageWindow.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
