# Criptografia

Víctor Boix

## 1. Introducció

La criptografia s'encarrega d'estudiar com protegir la informació mitjançant la seva transformació.
El procés de **xifrat** consisteix a transformar un conjunt de dades original (*dades en clar*) a un nou conjunt de dades totalment diferent i incomprensible (*dades xifrades*).
El procés invers permet recuperar la informació original i s'anomena **desxifrat**.

Avui en dia és molt important garantir que les aplicacions gestionin les dades de manera segura, és a dir, garantint que la informació que es guarda i s'envia només pugui ser llegida per les persones interessades.
La **Java Cryptography Extension (JCE)** és una extensió de la plataforma Java que proporciona implementacions d'algorismes per tal de generar i intercanviar claus, encriptar dades i autentificar missatges.

## 2. Xifrat simètric

El mecanisme de xifrat més utilitzat és el de **clau simètrica**.
En aquest mecanisme existeix una clau o secret, compartida entre tots els que han de llegir les dades, que permet xifrar-les i desxifrar-les.
Un exemple el trobem en l'establiment de la contrasenya de la xarxa sense fils d'un encaminador ADSL.

Un dels primers en utilitzar un algorisme de xifrat simètric va ser Juli Cèsar, que aplicava un desplaçament de 3 lletres a cada caràcter del text (xifrat Cèsar).
Un altre mecanisme de xifrat per desplaçament, molt habitual en jocs, és ROT13, que aplica un desplaçament de 13 lletres.

[Exemples de xifrat per desplaçament](https://github.com/vboix2/DAM-M09-ServeisProcessos/blob/master/src/criptografia/Xifrat_Rotacio.java)

Actualment existeixen diferents algorismes de xifrat simètric.
En aquest apartat ens centrarem en l'algorisme AES (Advanced Encryption Standard), però existeixen altres com DES i TripleDES.

En Java, la clau simètrica és un objecte de la classe `SecretKey`.
Aquesta clau pot generar-se aleatòriament o construir-se a partir d'una contrasenya utilitzant un algorisme de hash.

Exemple de creació d'una SecretKey de manera aleatòria.

```java
KeyGenerator kgen = KeyGenerator.getInstance("AES");
kgen.init(128);
SecretKey clau = kgen.generateKey();
```

### 2.1. Algorisme de hash
Un algorisme de hash és una transformació que s'aplica a un conjunt de dades i les converteix en una cadena de longitud fixa, de tal manera que el valor resultant és una representació compacta de les dades d'entrada.
La funció de transformació ha de complir un seguit de condicions:

* El resultat sempre ha de ser el mateix. Una mateixa entrada sempre ha de produir la mateixa sortida.
* Dues sortides diferents sempre vindran d'entrades diferents.
* No és reversible. És impossible efectuar la transformació inversa, és a dir, obtenir les dades originals.

Alguns algorismes hash són MD5, SHA-1 i SHA-256.

[Generador de hash SHA-256](https://passwordsgenerator.net/sha256-hash-generator/)

En Java podem aplicar un algorisme de hash utilitzant la classe `MessageDigest`.
Aquest algorisme només es pot aplicar sobre dades representades en bytes, per tant, haurem de convertir el text a un array de bytes amb la funció `getBytes()`; el resultat també serà de tipus `byte[]`.

```java
byte[] dades = text.getBytes();
MessageDigest md = MessageDigest.getInstance("SHA-256");
byte[] hash = md.digest(dades);
```

Per imprimir aquest resultat podem utilitzar el mètode estàtic `DatatypeConverter.printHexBinary()` o recórrer l'array i mostrar-lo en format hexadecimal:

```java
for (byte t:text){
    System.out.print(String.format("%02X", t));
}
```

El resultat d'un algorisme de hash podem utilitzar-lo per generar una clau simètrica, com es mostra a continuació.
D'aquesta manera crearem la clau de xifrat a partir d'una contrasenya, més fàcil de recordar o anotar.

```java
byte[] data = text.getBytes();
MessageDigest md = MessageDigest.getInstance("SHA-256");
byte[] hash = md.digest(data);
byte[] key = Arrays.copyOf(hash, 32);
sKey = new SecretKeySpec(key, "AES");
```

[Exemple d'utilització de l'algorisme de hash](https://github.com/vboix2/DAM-M09-ServeisProcessos/blob/master/src/criptografia/Hash.java)

### 2.2. Algorisme AES
L'algorisme AES només xifra blocs de bytes de mida fixa (16 bytes), per tant, per transformar dades de qualsevol mida cal dividir-les en blocs de la mida adequada mantenint-ne l'ordre.
L'algorisme s'encarrega de processar cada bloc individualment i concatenar els resultats parcials en el mateix ordre.
Quan la mida de les dades no coincideix amb un múltiple del tamany dels blocs es genera un *padding*, un farcit que omple les dades fins a la mida del bloc.

Aquest mètode, anomenat mode **ECB**, és poc segur perquè permet detectar blocs repetits o eliminar blocs del missatge.
El mètode **CBC** és més segur perquè el xifrat de cada bloc no depèn només del bloc de dades sinó també del resultat del bloc anterior. 

Java ofereix la classe `Cipher` per xifrar i desxifrar les dades.
Aquesta classe no té constructors i cal utilitzar el mètode estàtic `getInstance(...)`, aquest mètode té com a paràmetre d'entrada una cadena de text on cal especificar:

* Nom de l'algorisme: "AES"
* Mode d'operació: "ECB" o "CBC"
* Tipus de padding: "PKCS5PADDING"

Per xifrar les dades s'utilitza el mètode `init()` amb la clau de xifrat i la constant `Cipher.ENCRYPT_MODE`.
Les dades encriptades s'obtenen en un array de bytes amb el mètode `doFinal()`.
Per desxifrar les dades cal utilitzar les dades encriptades i la constant `Cipher.DECRYPT_MODE`.

[Exemple de mètodes per xifrar i desxifrar les dades](https://github.com/vboix2/DAM-M09-ServeisProcessos/blob/master/src/criptografia/Xifrat_Simetric.java)

## 3. Xifrat assimètric

La criptografia assimètrica o de clau pública es basa en la utilització de dues claus per a cada usuari.

* La **clau privada** s'ha de guardar de manera segura i només la pot conèixer el seu creador.
* La **clau pública** es distribueix a tothom a través de la xarxa.

Aquestes dues claus es generen conjuntament seguint un esquema matemàtic.
En Java, la classe `KeyPairGenerator` permet generar parelles de claus correctes de manera segura; la funció `generarClaus()` de l'exemple mostra com fer-ho.

[Exemple de creació d'una parella de claus](https://github.com/vboix2/DAM-M09-ServeisProcessos/blob/master/src/criptografia/Xifrat_Assimetric.java)

Les dues claus funcionen conjuntament, de tal manera que un missatge xifrat amb la clau pública d'un usuari només es pot desxifrar amb la clau privada del mateix usuari i a l'inversa.
Aquesta característica permet que el xifrat assimètric tingui dos esquemes de funcionament que estudiarem a continuació.

### 3.1. Encriptació amb clau pública
L'emissor utilitza la clau pública del receptor per xifrar el missatge.
Per tant, només el receptor pot desxifrar el missatge utilitzant la seva clau privada.
Aquest esquema garanteix la **confidencialitat** del missatge.

![Encriptació amb clau pública](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Public_key_encryption.svg/280px-Public_key_encryption.svg.png)

Per xifrar i desxifrar les dades també utilitzem la classe `Cipher`. Les funcions `encriptar()` i `desencriptar()` mostren com xifrar i desxifrar un missatge utilitzant l'algorisme RSA, el més habitual.

[Exemple de funcions per al xifrat assimètric RSA](https://github.com/vboix2/DAM-M09-ServeisProcessos/blob/master/src/criptografia/Xifrat_Assimetric.java)

### 3.2. Signatura digital
L'emissor xifra el missatge utilitzant la seva clau privada.
D'aquesta manera, qualsevol receptor pot desxifrar i verificar el missatge utilitzant la clau pública de l'emissor.
Aquest esquema garanteix l'**autenticitat** del missatge perquè no és possible alterar-ne el contingut.

![Signatura digital](https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Public_key_signing.svg/280px-Public_key_signing.svg.png)


## 4. Xifrat amb clau embolcallada

Els algorismes de xifrat assimètric tenen una limitació per a la longitud de les dades a xifrar i acostumen a ser lents.
Per aquest motiu, generalment les comunicacions es realitzen amb una combinació de xifrat simètric i assimètric.

En un sistema de **clau embolcallada (Key Encapsulation Mechanism)** les dades es xifren utilitzant una clau simètrica d'un sol ús generada a l'atzar.
Aquesta clau es xifra utilitzant la clau pública del receptor i s'envien conjuntament el missatge xifrat i la clau xifrada.
D'aquesta manera, el receptor ha de desxifrar la clau simètrica amb la seva clau privada i utilitzar-la per desxifrar el missatge.

[Exemple de xifrat de clau embolcallada](https://github.com/vboix2/DAM-M09-ServeisProcessos/blob/master/src/criptografia/Xifrat_KEM.java)

Aquest mecanisme és utilitzat en el protocol **TLS** per a l'establiment de connexions segures amb un servidor.


## 5. Certificat digitals


## 6. Recursos

* [Apunts de l'IOC](https://ioc.xtec.cat/materials/FP/Materials/2252_DAM/DAM_2252_M09/web/html/WebContent/u3/a1/continguts.html)
* [Documentació de la classe Cipher](https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html)
* [Criptografia de clau pública](https://ca.wikipedia.org/wiki/Criptografia_de_clau_pública)
* [Criptografía y seguridad](https://www.adictosaltrabajo.com/2016/11/10/criptografia-y-seguridad/)
* [Key encapsulation](https://en.wikipedia.org/wiki/Key_encapsulation)
