# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus sisältää sata sudokua. Käyttäjä voi vapaasti valita, minkä sudokun hän haluaa pelata.

## Toimintaympäristön rajoitteet

Ohjelmiston tulee olla suoritettavissa, käännettävissä ja testattavissa Linux-koneen komentoriviltä. Graafisen käyttöliittymän toteuttamiseen käytetään JavaFX:ää.

## Käyttöliittymäluonnos

![Kayttoliittymaluonnos](https://raw.githubusercontent.com/sannahan/ot-harjoitustyo/master/dokumentaatio/kuvat/kayttoliittymaluonnos.jpg)

## Suunnitellut toiminnallisuudet (x = tehty)

* käyttäjä näkee 10x10 ruudukon, josta hän voi valita sudokun ratkottavaksi
* käyttäjä näkee valitsemansa sudokun
* käyttäjä voi lisätä sudokuun numeroita (numeroita on kahta kokoa: pieni ja iso) x
* käyttäjä voi poistaa sudokuun lisäämiään numeroita x
* käyttäjä voi merkitä valitsemansa ruudut valitsemallaan värillä 
* jos käyttäjä lisää sudokuun numeron, joka rikkoo pelin sääntöjä, virhe näytetään käyttäjälle

## Jatkokehitysideoita

* mahdollisuus luoda käyttäjätunnus: keskeneräisen pelin tallentaminen, omien tilastojen tarkastelu, käyttäjätunnuksen hallinta 
* sudokun ratkomiseen käytetyn ajan laskeminen
* vaikeustasojen merkitseminen
* tyhjä sudokupohja, johon on mahdollista kopioida oma sudoku
* vinkit sudokutekniikoiden, kuten x-siiven ja pilvenpiirtäjän harjoittelemiseen
* onnitteluviesti onnistuneen ratkaisun seurauksena
