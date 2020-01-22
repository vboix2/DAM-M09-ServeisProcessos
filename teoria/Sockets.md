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
InetAddress[] google = InetAddress.getAllByName("google.com");
```

* `getLoopbackAddress()` - crea un objecte InetAddress amb l'adreça loopback del sistema (127.0.0.1)
```java
InetAddress[] loopback = InetAddress.getLoopbackAddress();
```

* `getLocalHost()` - crea un objecte InetAddress amb l'adreça localhost del sistema
```java
InetAddress[] localhost = InetAddress.getLocalHost();
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

### 1.2. Recursos


### 1.3. Sòcols no orientats a connexió (UDP)


### 1.4. Sòcols orientats a connexió (TCP)


## 2. Programació de serveis en xarxa

### 2.1. JavaMail


## 3. Recursos

* [Documentació de la classe InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)

