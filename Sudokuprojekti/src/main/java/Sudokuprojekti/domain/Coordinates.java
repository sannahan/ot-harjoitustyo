package Sudokuprojekti.domain;

public class Coordinates {
    public int y, x;
    
    public Coordinates(int y, int x) {
        this.y = y;
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getX() {
        return this.x;
    }
}
