# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksella voi pelata sudokua. 

## Toimintaympäristön rajoitteet

Ohjelmisto on suoritettavissa, käännettävissä ja testattavissa uusimmat päivitykset sisältävällä cubbli-linuxilla. Graafisen käyttöliittymän toteuttamiseen käytetään JavaFX:ää.

## Käyttöliittymäluonnos

![Kayttoliittymaluonnos](https://raw.githubusercontent.com/sannahan/ot-harjoitustyo/master/dokumentaatio/kuvat/kayttoliittymaluonnos.jpg)

## Sovelluksen tarjoama toiminnallisuus

* käyttäjä voi valita ratkaistavan sudokun kolmesta vaihtoehdosta: easy, medium ja hard tai tallentaa sovellukseen 1-25 omaa sudokua
* käyttäjä voi lisätä sudokuun numeroita (numeroita on kahta kokoa: pieni ja iso)
* käyttäjä voi poistaa sudokuun lisäämiään numeroita
* käyttäjä voi korostaa valitsemansa ruudut keltaisella värillä 
* jos käyttäjä lisää sudokuun numeron, joka rikkoo pelin sääntöjä, virhe näytetään käyttäjälle
* käyttäjä voi tallentaa keskeneräisen sudokun
* käyttäjä näkee onnitteluviestin onnistuneen ratkaisun seurauksena

## Jatkokehitysideoita

Sovellukseen voitaisiin lisätä jatkokehityksessä esimerkiksi seuraavat ominaisuudet:

* käyttäjätunnukset
* ratkomiseen käytetyn ajan laskeminen
