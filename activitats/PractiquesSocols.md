# Pràctiques de sòcols i serveis

## WebScraper

L'objectiu d'aquesta pràctica és aprendre a capturar el contingut d'una pàgina web.

Crearem una aplicació que guardi el títol, l'autor i la data de publicació 
de totes les notícies d'una pàgina web.

* Crea una classe `Noticia` amb tres atributs de tipus `String`: titol, autor i data.
Crea també els mètodes get i set de cada atribut i el mètode toString().
* Crea una classe `Scraper` amb un atribut de tipus `ArrayList<Noticia>` i tres mètodes:
* `String download(String url)` ha de descarregar una pàgina web (indicada amb 
un link al paràmetre d'entrada) i tornar tot el seu codi html en un String.
* `void scrape_link(String html)` ha d'obtenir el codi html d'una pàgina,
parseritzar-ne el contingut, recórrer totes les notícies i crear un objecte Noticia 
amb les dades de cada notícia que es trobi a la pàgina.
Per obtenir les dades del codi html (parseritzar el contingut) utilitza la llibreria 
[jsoup](https://jsoup.org/).
* `void scrape(String url)` ha de recórrer totes les pàgines de notícies de la web, 
descarregar-ne el contingut (download) i generar les notícies (scrape_link).
Entre cada descàrrega afegeix un temps d'espera de 3 segons.
* Crea una classe principal per obtenir totes les notícies de la pàgina
[https://agora.xtec.cat/iesgabrielamistral/](https://agora.xtec.cat/iesgabrielamistral/)


## Xat TCP

L'objectiu d'aquesta pràctica és aprendre a comunicar dos dispositius utilitzant el 
protocol TCP.

Crearem una aplicació que permeti xatejar entre dos ordinadors coneixent 
les seves adreces ip.