package sudokuprojekti.domain;

/**
* Luokka, joka sisältää y- ja x-koordinaatin
*/
public class Coordinates {
    private int y;
    private int x;
    
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
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Coordinates)) {
            return false;
        }
        Coordinates otherCoordinates = (Coordinates) other;
        if (this.y == otherCoordinates.getY() && this.x == otherCoordinates.getX()) {
            return true;
        }
        return false;
    }
}
