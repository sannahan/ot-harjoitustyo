### Rakenne

Ohjelman pakkausrakenne on seuraava:

![luokka/pakkauskaavio](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokka_pakkauskaavio.jpg)

Pakkaus Sudokuprojekti.ui sisältää JavaFX:llä toteutetun graafisen käyttöliittymän. Sudokuprojekti.domain sisältää sovelluslogiikan ja Sudokuprojekti.dao vastaa puolestaan tiedoston käsittelystä.

### Sovelluslogiikka

Sudoku- ja square-luokat ovat sovelluksen ydin. Square kuvastaa yhtä ruutua sudokussa: sen oliomuuttujat säilövät tiedon ruutuun lisätystä numerosta ja ruutuun tehdyistä muistiinpanoista. Sudokuluokka sisältää Square-olioista tehdyn 9x9 sudokumatriisin. Se säilöö tietoa lisäksi siitä, ollaanko sudokuun lisäämässä varsinaista numeroa vai muistiinpanoa ja tarkistaa, onko lisättävä numero sääntöjen mukainen.

Sovelluslogiikka sisältää lisäksi Coordinates-luokan, jota käytetään helpottamaan virheellisten numeroiden sijaintien säilömistä.

Numeron lisääminen sudokuun:

![sekvenssikaavio1](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio.jpg)

SudokuService-luokka toimii rajapintana käyttöliittymän ja tietojen pysyväistallennuksesta vastaavan FileSudokuDao-luokan välillä. 
