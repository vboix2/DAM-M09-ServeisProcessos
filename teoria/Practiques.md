# Pràctiques

## Criptografia

### Pràctica 1. Algorisme de hash

L'objectiu de la pràctica és aprendre a utilitzar la classe *MessageDigest*.

Fes una aplicació amb les dues funcions següents:

**public String resumFitxer(String fitxer)**.
Donada la ruta d'un fitxer ha de calcular el resum (SHA-256) de tot el seu contingut i retornar-lo com a String en format hexadecimal.

**public String trobaCombinacio(String hash)**.
Donada una cadena hexadecimal de 64 caràcters (SHA-256), la funció ha de trobar el text que l'ha generat (utilitzant un atac de força bruta) i tornar-lo com a String.
El text original només pot ser un nombre de 4 dígits i la funció ha de provar totes les combinacions fins a trobar la correcta.

Pots validar les funcions utilitzant les següents proves: [Tests pràctica 1](../test/criptografia/activitats/Practica1_HashTest.java)

[Solució](../src/criptografia/activitats/Practica1_Hash.java)


### Pràctica 2. Xifrat simètric

L'objectiu de la pràctica és aprendre a xifrar i desxifrar text utilitzant una clau simètrica.

Fes una aplicació amb un mètode principal que executi els següents passos:

* Genera una clau simètrica (*SecretKey*) a partir d'una contrasenya.
* Llegeix el contingut d'un fitxer de text (nom_fitxer.txt) i encripta'l utilitzant la clau generada.
* Guarda el text xifrat en un nou fitxer (nom_fitxer.enc)
* Llegeix el contingut del fitxer encriptat i desencripta'l utilitzant novament la clau simètrica.
* Mostra per pantalla el contingut del fitxer ja desencriptat.

Per facilitar les transformacions entre bytes i notació hexadecimal pots utilitzar:

* *byte[] to String*: DatatypeConverter.printHexBinary(bytes)
* *String to byte[]*: DatatypeConverter.parseHexBinary(string)

A més, recorda que disposes dels mètodes getBytes() i toString() per transformar bytes a text i a l'inversa.

[Solució](../src/criptografia/activitats/Practica2_XifratSimetric.java)
