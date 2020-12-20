# Arkkitehtuurikuvaus

### Rakenne

Koodin pakkausrakenne on seuraava:

![pakkausrakenne](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kuva1.jpg)

Pakkaus Sudokuprojekti.ui sisältää JavaFX:llä toteutetun graafisen käyttöliittymän. Sudokuprojekti.domain sisältää sovelluslogiikan ja Sudokuprojekti.dao vastaa puolestaan tietojen pysyväistallennuksesta tiedostoon.

### Käyttöliittymä

Luokka sudokuprojekti.ui.SudokuView vastaa käyttöliittymän rakentamisesta. Käyttöliittymässä on yksi näkymä, joka sisältää valikon ja pelikentän sudokulle.

Käyttöliittymä on pyritty eristämään sovelluslogiikasta. Se kutsuu luokan sudokuprojekti.domain.SudokuService metodeja.

Kun sudokun tilanne muuttuu (numeron lisääminen tai poistaminen, ruutujen korostaminen, sudokun vaihtaminen), kutsutaan käyttöliittymän metodia drawSudoku(), joka renderöi näkymän vastaamaan sovelluslogiikalta saatavaa sudokun tilaa.

### Sovelluslogiikka

Sudoku- ja Square-luokat ovat sovelluksen ydin. Square kuvastaa yhtä ruutua sudokussa: sen oliomuuttujat säilövät tiedon ruutuun lisätystä numerosta ja ruutuun tehdyistä muistiinpanoista. Sudokuluokka sisältää Square-olioista tehdyn 9x9 sudokumatriisin. Se säilöö tietoa lisäksi esimerkiksi siitä, ollaanko sudokuun lisäämässä varsinaista numeroa vai muistiinpanoa ja tarkistaa, onko lisättävä numero sääntöjen mukainen.

![luokkakaavio](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kuva2.jpg)

Sovelluslogiikka sisältää lisäksi Coordinates-luokan, jota käytetään helpottamaan virheellisten ja valmiiksi annettujen numeroiden sijaintien säilömistä.

SudokuService-luokka tarjoaa käyttöliittymän toiminnoille omat metodit. Näitä ovat esim.

boolean createSudokuBase(int index)
boolean saveSudoku()
void setNumberToSquareIfPossible(int y, int x)
void controlHighlight(boolean value)

SudokuService-luokalla on oliomuuttuja FileSudokuDao, jonka metodeja kutsumalla TodoService kontrolloi tiedoston lukemista ja kirjoittamista. sudokuprojekti.dao.FileSudokuDao-Luokan toteutus injektoidaan TodoServicelle konstruktorikutsun yhteydessä.

Seuraava luokka/pakkauskaavio kuvaa SudokuServicen ja muun koodin suhdetta:

![luokka/pakkauskaavio](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kuva3.jpg)

### Tietojen pysyväistallennus

FileSudokuDao-luokka vastaa tiedoston lukemisesta ja kirjoittamisesta.

#### Tiedostot

Sovelluksen juureen tulee sijoittaa konfiguraatiotiedosto config.properties, jonka sisältö on seuraava:

	sudokuFile=sudokufile.txt

Tiedosto sudokufile.txt sisältää kaksi riviä yhtä sudokua kohden. Ensimmäinen rivi säilöö sudokun varsinaiset numerot:

	sudokunIndeksi;1;sudokunNumerotRiveittäinPuolipisteelläEroteltuna

Numero 1 merkitsee sitä, että rivi sisältää varsinaiset numerot. Toinen rivi sisältää muistiinpanot:

	sudokunIndeksi;2;sudokunMuistiinpanotRiveittäinPuolipisteelläEroteltuna

Numero 2 merkitsee sitä, että rivi sisältää muistiinpanot. Rivissä on 83 puolipisteellä eroteltua kenttää: sudokun indeksi, numero 1 varsinaisen numeron tai numero 2 muistiinpanon merkiksi ja 81 kenttää kuvaamaan sudokun kenttiä riveittäin vasemmalta oikealle ja ylhäältä alas.

### Päätoiminnallisuudet

Tämän kohdan tarkoituksena on kuvata sovelluksen toimintalogiikkaa sekvenssikaavioiden avulla muutaman päätoiminnallisuuden osalta.

#### Numeron lisääminen sudokuun

![sekvenssikaavioNumeronLisääminen](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kuva4.jpg)

#### Sudokun tallentaminen

![sekvenssikaavioSudokunTallentaminen](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kuva5.jpg)

### Ohjelman rakenteeseen jääneet heikkoudet

#### Käyttöliittymän ja SudokuServicen refaktorointi

Refaktorointi ei ole täysin onnistunut erityisesti käyttöliittymän maintainSudokuView()- ja drawSudoku()-metodien osalta: käyttöliittymä kutsuu edelleen muutamaa Sudoku-luokan metodia. 

#### DAO

Luokkaa FileSudokuDao ei ole eristetty FileDao-rajapinnan taakse. Tästä voi aiheutua ongelmia, jos sovelluksen talletustapaa päätetään vaihtaa.
 
