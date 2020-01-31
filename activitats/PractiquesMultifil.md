# Pràctiques de programació multifil

## Màxim d'un array

L'objectiu de la pràctica és crear un programa que reparteixi una tasca entre fils.

1. Crea una array d'enters aleatoris de mida 1000
2. Crea una subclasse de Thread que cerqui el màxim donat un array, una posició inicial i una posició final
3. Fes que la classe principal divideixi l'array en N parts iguals i iniciï N fils que cerquin el màxim de cada fragment
4. Guarda els màxims en un nou array i utilitza un darrer fil per cerca el màxim de tots els màxims
5. Imprimeix per pantalla el valor màxim de tot l'array

[Solució](../src/fils/activitats/MaximArray.java)

## Suma d'un array

L'objectiu d'aquesta pràctica és calcular la suma de valors d'un array paral·lelitzant la tasca.

1. Crea un array de 1000 posicions i assigna-li un valor aleatori de tipus `double` entre -10 i 10 a cada posició.
2. Crea una classe Fil que hereti de Thread amb els atributs suma, inici, final i nombres.
3. Crea el mètode run que calculi la suma de valors de l'array donades la posició inicial i final.
3. Crea un mètode per obtenir la suma de l'array.
4. Al mètode principal, crea 10 fils i assigna'ls un fragment de l'array a cada un.
5. Sincronitza el final dels fils i recupera la suma de cada fragment en un nou array.
6. Suma els valors del nou array i mostra el resultat per pantalla.

## Control d'accés a un pàrquing

Farem una aplicació que gestioni l'accés a un pàrquing públic. 

* El pàrquing té 10 places i no hem de permetre l'entrada a cotxes si totes les places estan ocupades.
* Simularem l'entrada de 40 cotxes i la seva estada dins del pàrquing.
* Els cotxes arribaran al cap d'un temps aleatori entre 0 i 10 s, intentaran entrar al pàrquing, s'hi estaran un temps aleatori entre 0 i 20 s i sortiran.
* Cal mostrar un missatge cada cop que un cotxe entra o surt.
* També cal portar un comptador de les places lliures, fer esperar un cotxe si no hi ha espai i notificar-li quan pot entrar.

[Solució](../src/fils/activitats/Parquing.java)

## Transaccions en un banc

[Solució](../src/fils/activitats/Transaccions_Banc.java)

## Mitjana d'un flux continu de dades

L'objectiu d'aquesta pràctica és implementar un model productor-consumidor que 
calculi la mitjana aritmètica d'un flux continu de nombres aleatoris.

* Crea una classe de tipus productor i una altra de tipus consumidor; a més, crea una classe que actuï de pila i una classe principal.
* El mètode principal ha de crear una pila de 20 posicions, tres fils de tipus productor i un fil de tipus consumidor.
* Cada fil de tipus productor esperarà un temps aleatori entre 0 i 2 segons i generarà un nombre aleatori de tipus ´double` entre 0 i 10.
* El fil consumidor mostrarà per pantalla la mitjana aritmètica de tots els valors recollits fins al moment cada 5 segons.

[Solució](../src/fils/activitats/MitjanaFluxDades.java)