# Ohjelmistotekniikka, harjoitustyö

## SudokuApp

Sovelluksessa voi pelata sudokua. Sovellus toimii Helsingin yliopiston tietojenkäsittelytieteen kurssin Ohjelmistotekniikka harjoitussovelluksena.

###  Dokumentaatio
[alustava määrittelydokumentti](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[työaikakirjanpito](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[arkkitehtuuri](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[käyttöohje](https://github.com/sannahan/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)
### Releaset

[Viikko 5](https://github.com/sannahan/ot-harjoitustyo/releases)

[Viikko 6](https://github.com/sannahan/ot-harjoitustyo/releases/tag/viikko6)

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

#### Suoritettavan jarin generointi

Komento

	mvn package

generoi hakemistoon target suoritettavan jar-tiedoston Sudokuprojekti-1.0-SNAPSHOT.jar

#### JavaDocin generointi

JavaDoc voidaan generoida komennolla

	mvn javadoc:javadoc
