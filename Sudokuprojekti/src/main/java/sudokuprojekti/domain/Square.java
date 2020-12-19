package sudokuprojekti.domain;

import java.util.ArrayList;

/**
* Luokka, joka kuvaa yhtä ruutua sudokussa
*/
public class Square {
    private ArrayList<Integer> notation;
    private int number;

    /**
    * Konstruktori luo uuden listan muistiinpanoille ja asettaa numeroksi 0
    */
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
    
    /**
    * Metodi tyhjentää muistiinpanot, jos käyttäjä painaa raksia
    * ja lisää muistiinpanon, jos numeroa ei ole lisätty muistiinpanoihin 
    * aikaisemmin
    */
    public void setNotation(int n) {
        if (n == 0) {
            this.notation.clear();
        } else if (this.notation.contains(n)) {
            return;
        } else {
            this.notation.add(n);
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }   
}
