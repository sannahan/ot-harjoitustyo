# Ohjelmistotekniikka, harjoitustyö

## SudokuApp

Sovelluksessa voi pelata sudokua. Sovellus toimii Helsingin yliopiston tietojenkäsittelytieteen kurssin Ohjelmistotekniikka harjoitussovelluksena.

###  Dokumentaatio
[alustava määrittelydokumentti](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[työaikakirjanpito](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[arkkitehtuuri](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

### Releaset

### Komentorivitoiminnot

Projekti suoritetaan komennolla

	mvn compile exec:java -Dexec.mainClass=Sudokuprojekti.ui.Main


#### Testit

Testit suoritetaan komennolla

	mvn test

Testikattavuusraportti luodaan komennolla

	mvn test jacoco:report


#### Checkstyle

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

	mvn jxr:jxr checkstyle:checkstyle

Virheilmoitukset löytyvät tiedostosta target/site/checkstyle.html
