# Arkkitehtuurikuvaus

### Rakenne

Koodin pakkausrakenne on seuraava:

![luokka/pakkauskaavio](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokka_pakkauskaavio.jpg)

Pakkaus Sudokuprojekti.ui sisältää JavaFX:llä toteutetun graafisen käyttöliittymän. Sudokuprojekti.domain sisältää sovelluslogiikan ja Sudokuprojekti.dao vastaa puolestaan tietojen pysyväistallennuksesta tiedostoon.

### Käyttöliittymä

Luokka sudokuprojekti.ui.SudokuView vastaa käyttöliittymän rakentamisesta. Käyttöliittymässä on yksi näkymä, joka sisältää valikon ja pelikentän sudokulle.

Käyttöliittymä on pyritty eristämään sovelluslogiikasta. Se kutsuu luokan sudokuprojekti.domain.SudokuService metodeja.

Kun sudokun tilanne muuttuu (numeron lisääminen tai poistaminen, ruutujen korostaminen, sudokun vaihtaminen), kutsutaan käyttöliittymän metodia drawSudoku(), joka renderöi näkymän vastaamaan sovelluslogiikalta saatavaa sudokun tilaa.

### Sovelluslogiikka

Sudoku- ja Square-luokat ovat sovelluksen ydin. Square kuvastaa yhtä ruutua sudokussa: sen oliomuuttujat säilövät tiedon ruutuun lisätystä numerosta ja ruutuun tehdyistä muistiinpanoista. Sudokuluokka sisältää Square-olioista tehdyn 9x9 sudokumatriisin. Se säilöö tietoa lisäksi esimerkiksi siitä, ollaanko sudokuun lisäämässä varsinaista numeroa vai muistiinpanoa ja tarkistaa, onko lisättävä numero sääntöjen mukainen.

Sovelluslogiikka sisältää lisäksi Coordinates-luokan, jota käytetään helpottamaan virheellisten ja valmiiksi annettujen numeroiden sijaintien säilömistä.

SudokuService-luokka tarjoaa käyttöliittymän toiminnoille oman metodin. Näitä ovat esim.



SudokuService-luokalla on oliomuuttuja FileSudokuDao, jonka metodeja kutsumalla TodoService kontrolloi tiedoston lukemista ja kirjoittamista. sudokuprojekti.dao.FileSudokuDao-Luokan toteutus injektoidaan TodoServicelle konstruktorikutsun yhteydessä.

Seuraava luokka/pakkauskaavio kuvaa SudokuServicen ja muun koodin suhdetta:

 

#### Päätoiminnallisuudet
Numeron lisääminen sudokuun:

![sekvenssikaavio1](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio.jpg)

SudokuService-luokka toimii rajapintana käyttöliittymän ja tietojen pysyväistallennuksesta vastaavan FileSudokuDao-luokan välillä. 
