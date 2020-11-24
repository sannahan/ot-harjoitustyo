package Sudokuprojekti.domain;

import java.util.ArrayList;

public class Square {
    ArrayList<Integer> notation;
    public int number;

    public Square() {
        this.notation = new ArrayList<>();
        this.number = 0;
    }

    public ArrayList<Integer> getNotation() {
        return notation;
    }

    public int getNumber() {
        return number;
    }

    public void setNotation(int n) {
        this.notation.add(n);
    }

    public void setNumber(int number) {
        this.number = number;
    }   
}
