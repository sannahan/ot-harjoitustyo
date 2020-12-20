# Käyttöohje

## Käyttöönotto

Lataa tiedosto [Sudokuprojekti.jar](https://github.com/sannahan/ot-harjoitustyo/releases/tag/Loppupalautus).

Sovelluksen juuresta tulee löytyä lisäksi tiedostot
sudokufile.txt
testfile.txt (tyhjä tiedosto, joka tulee luoda itse)
black.png
config.properties

Ohjelma käynnistetään komennolla java -jar Sudokuprojekti.jar

## Sudokun valinta

Voit valita haluamasi vaikeusasteen oikeasta valintapalkista. Sinulla on kolme vaihtoehtoa: helppo, keskivaikea ja vaikea sudoku. Saat pelin näkyviin klikkaamalla sopivaa vaikeusastetta.

![choosegame](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/choosegame.png)

Jos olet tallentanut tiedostoon omia sudokuja tai keskeneräisen ratkaisun, voit valita sudokun 25 sudokun valintapalkista jatkaaksesi sen ratkaisua. Jos haluat tyhjän pohjan uutta sudokua varten, aloita valitsemalla 25 sudokun valintapalkista tyhjä sudokupohja. 

## Numeron lisääminen ruutuun

Kun haluat lisätä sudokuun ison numeron, valitse oikeasta valintapalkista "Big" ja haluamasi numero. Klikkaa sitten sitä sudokun ruutua, johon haluat lisätä numeron. 

## Virheellinen numero

Jos lisäät sudokuun numeron, joka sotii sen sääntöjä vastaan, sovellus merkitsee virheen aiheuttavat numerot punaisella värillä. Sudokussa ei saa olla kahta samaa numeroa samalla rivillä, samassa sarakkeessa tai 3x3 ruudukossa.

![showmistake](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/showmistake.png)

## Muistiinpanon lisääminen ruutuun

Jos haluat lisätä sudokuun muistiinpanoja mahdollisista numeroista, valitse oikeasta valintapalkista "Small" ja valitse haluamasi numero. Klikkaa sen jälkeen sitä sudokun kohtaa, johon haluat lisätä muistiinpanon. Jos ruudussa on aikaisempia muistiinpanoja, näet listan kaikista muistiinpanoista.

## Numeron tai muistiinpanon poistaminen

Jos teet virheen, voit poistaa numeron tai muistiinpanon valitsemalla oikeasta sivupalkista raksin ja klikkaamalla sitä kohtaa sudokussa, missä virhe esiintyy.
