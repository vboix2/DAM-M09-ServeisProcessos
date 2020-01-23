# Sòcols i serveis

## 1. Programació de comunicacions en xarxa

### 1.1. Adreces IP

L'adreçament de xarxa permet identificar els equips i proporciona mecanismes perquè es puguin comunicar,
tant si es troben a la mateixa xarxa com en xarxes diferents.
El protocol d'adreçament més utilitzat és el protocol IP i estableix la comunicació 
a partir d'unes adreces lògiques anomenades adreces IP.
L'adreça IP és una adreça lògica que fixa l'administrador de la xarxa per 
identificar cadascun dels dispositius i la xarxa on es troben connectats.
El protocol IP compta amb diferents versions, la versió 4 (IPv4) utilitza 
adreces de 32 bits formades per 4 grups de 8 octets.
Degut a la limitació en el nombre d'adreces i al gran creixement d'internet 
aquesta versió està sent substituïda per la versió 6 (IPv6) que compta amb adreces de 128 bits.

Java disposa de la classe `InetAddress` per gestionar adreces IP.
Aquesta classe és una generalització de les subclasses `Inet4Address` i 
`Inet6Adress` i permet treballar amb adreces IP independentment de la versió utilitzada.
Per crear una adreça IP hem d'utilitzar algun dels mètodes estàtics:

* `getByName()` - crea un objecte InetAddress a partir d'un nom de domini o una adreça IP
```java
InetAddress ipGoogle = InetAddress.getByName("google.com");
InetAddress ipOpenDNS = InetAddress.getByName("208.67.222.222");
```

* `getAllByName()` - crea un array d'adreces amb totes les IPs associades a un nom de domini
```java
InetAddress[] ipGoogle = InetAddress.getAllByName("google.com");
```

* `getLoopbackAddress()` - crea un objecte InetAddress amb l'adreça loopback del sistema (127.0.0.1)
```java
InetAddress ipLoopback = InetAddress.getLoopbackAddress();
```

* `getLocalHost()` - crea un objecte InetAddress amb l'adreça localhost del sistema
```java
InetAddress ipLocal = InetAddress.getLocalHost();
```

En el cas que el format de l'adreça no sigui correcte o no es pugui resoldre es 
llançarà una excepció del tipus `UnknownHostException`.

Els objectes de la classe `InetAddress` permeten obtenir informació del host 
associat a la IP a través dels mètodes:

* `getHostName()` - obté el nom del dispositiu associat
* `getHostAddress()` - obté l'adreça IP associada
* `getAddress()` - obté un `byte[]` amb el valor de la IP associada
* `isLoopbackAddress()` - obté un `boolean` indicant si l'adreça és del tipus 127.0.0.x
* `isSiteLocalAddress()` - obté un `boolean` indicant si es tracta d'una adreça privada de xarxa

```java
InetAddress ipLocal = InetAddress.getLocalHost();

String ip = ipLocal.getHostAddress();
String name = ipLocal.getHostName();
byte[] ip = ipLocal.getAddress();
boolean esLoopback = ipLocal.isLoopbackAddress();
boolean esLocal = ipLocal.isSiteLocalAddress();
```

### 1.2. Recursos

En una xarxa, els ordinadors poden posar a disposició de la resta de dispositius 
diferents tipus de recursos (dades, fitxers, impressores...).
Cada recurs s'identifica utilitzant una cadena de caràcters única 
anomenada URL (*Uniform Resource Locator*) amb l'estructura següent:

```
esquema://autoritat/ruta
```

* esquema - nom del protocol d'accés per accedir al recurs (http, ftp, file, jdbc...)
* autoritat - identificador del dispositiu a la xarxa (adreça ip o el nom del domini)
* ruta - identificador del recurs dins del dispositiu

Aquesta estructura es pot ampliar amb altres paràmetres optatius.
La seva sintaxi més general és la següent:

```
esquema://usuari:contrasenya@autoritat:port/ruta?consulta#fragment
```

* consulta - parelles atribut=valor (separades pel delimitador &) que proporcionen
paràmetres per a la cerca d'informació dins del recurs.
* fragment - identificador que enllaça a un recurs secundari o a una part del recurs

Java disposa de dues classes per treballar amb recursos: `URL` i `URLConnection`.
La classe `URL` representa una URL, és a dir, l'adreça a un recurs d'internet.
Alguns dels seus constructors són:

* `URL(String cadenaURL)` - crea un objecte URL a partir de la seva adreça
* `URL(String protocol, String autoritat, String ruta)` - crea un objecte URL a
partir del protocol, l'identificador del dispositiu i l'identificador del recurs

Els principals mètodes per obtenir informació dels objectes `URL` són:

* `getProtocol()` - obté el nom del protocol de la URL
* `getAuthority()` - obté el nom de l'autoritat de la URL
* `getPath()` - obté el nom de la ruta, l'identificador del recurs dins del dispositiu
* `getPort()` - obté el nom del port de la URL
* `toString()` - obté un `String` amb tota la cadena de la URL

Per obtenir informació d'un recurs podem utilitzar el mètode `openStream()` per aconseguir 
un flux d'entrada de dades des del contingut remot.

```java
URL url = new URL("https://...");
BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
String linia;
String html = "":
while ((linia = in.readLine()) != null) {
    html += linia;
}
in.close();
```

La classe `URLConnection` permet obrir una connexió amb el recurs i realitzar-hi 
operacions de lectura i escriptura.
Per crear una connexió utilitzarem el mètode de URL `openConnection()`.
```java
URL url = new URL("https://...");
URLConnection con = url.openConnection();
```

A més, aquesta classe permet obtenir informació addicional (metadades) sobre el contingut.
Alguns dels mètodes són:

* `getContentEncoding()` - obté la codificació de caràcters del contingut
* `getContentLength()` - obté la longitud del contingut
* `getContentType()` - obté el tipus de contingut
* `getDate()` - obté la data de creació del recurs

```java
String tipus = con.getContentType();
String codificacio = con.getContentEncoding();
int longitud = con.getContentLength();
long data = con.getDate();
```

Aquesta classe també ofereix un flux de dades per obtenir el contingut del recurs 
invocant el mètode `getInputStream()`.

```java
InputStream in = con.getInputStream();
BufferedReader br = new BufferedReader(new InputStreamReader(in));
String html="";
String linia;
while((linia = br.readLine())!= null){
    html += linia;
}
in.close();
```

[Exemples d'utilització de les classes URL i URLConnection](../src/socols/Recursos.java)

### 1.3. Sòcols no orientats a connexió (UDP)


### 1.4. Sòcols orientats a connexió (TCP)


## 2. Programació de serveis en xarxa

### 2.1. JavaMail


## 3. Recursos

Documentació de Java:

* [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)
* [URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html)
* [URLConnection](https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html)
* [DatagramSocket](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramSocket.html)
* [DatagramPacket](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramPacket.html)
* [Socket](https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html)
* [ServerSocket](https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html)


Apunts de l'IOC:

* [1.Programació de comunicacions en xarxa](https://ioc.xtec.cat/materials/FP/Materials/2252_DAM/DAM_2252_M09/web/html/WebContent/u2/a1/continguts.html)
* [2.Generació de serveis en xarxa](https://ioc.xtec.cat/materials/FP/Materials/2252_DAM/DAM_2252_M09/web/html/WebContent/u2/a2/continguts.html)



