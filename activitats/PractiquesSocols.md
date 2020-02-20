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

Crearem una aplicació que permeti xatejar entre dos dispositius.
Per poder establir una connexió bidireccional i assegurar la recepció dels paquets
utilitzarem el protocol TCP.
A més, per poder enviar i rebre dades en qualsevol moment haurem de crear dos fils
d'execució per a cada dispositiu: un dedicat a rebre missatges i l'altre només a
enviar-los. En total necessitarem 4 classes.

Classe `Listen`:

* Crea una classe `Listen` que sigui subclasse de `Thread`.
* Declara-hi dos atributs de tipus `BufferedReader` i `Socket`.
* Crea un constructor que permeti assignar un socket a l'atribut, creï un flux 
d'entrada de dades a partir d'aquest socket i el guardi a l'atribut BufferedReader.
* Implementa el mètode `run()` fent un bucle que llegeixi els missatges entrants 
i els imprimeixi per pantalla.

Classe `Talk`:

* Crea una classe `Talk`, que també sigui subclasse de `Thread`.
* Declara-hi dos atributs de tipus `PrintStream` i `Socket`.
* Crea un constructor que permeti assignar un socket a l'atribut, creï un flux 
de sortida de dades a partir d'aquest socket i el guardi a l'atribut de tipus PrintStream.
* Implementa el mètode `run()` fent un bucle que llegeixi per teclat els missatges
i els enviï a l'altre dispositiu.

Classe `Server`:

* Crea una classe Server amb un mètode principal.
* Inicia un `ServerSocket` escoltant a un port determinat.
* Quan s'accepti la connexió crea un objecte `Socket` i inicia la comunicació 
a través de dos fils, un de tipus Talk i l'altre de tipus Listen.
 
Classe `Client`:

* Crea una classe Client amb un mètode principal.
* Inicia una connexió al servidor i port especificat.
* Quan s'accepti la connexió crea un objecte `Socket` i inicia la comunicació 
a través de dos fils, un de tipus Talk i l'altre de tipus Listen.

Afegeix totes les funcionalitats que creguis convenients per millorar el 
funcionament de l'aplicació.

