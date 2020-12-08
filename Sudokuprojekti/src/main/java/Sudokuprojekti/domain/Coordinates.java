package Sudokuprojekti.domain;

/**
* Luokka, joka sisältää x- ja y-koordinaatin
*/
public class Coordinates {
    /**
    * Y-koordinaatti
    */
    public int y;
    /**
    * X-koordinaatti
    */
    public int x;
    
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
