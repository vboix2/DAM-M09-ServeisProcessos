# Pràctiques de programació multifil

## Pràctica 1. Sumar un array

L'objectiu d'aquesta pràctica és calcular la suma de valors d'un array paral·lelitzant la tasca.

1. Crea un array de 1000 posicions i assigna-li un valor aleatori de tipus `double` entre -10 i 10 a cada posició.
2. Crea una classe Fil que hereti de Thread amb els atributs suma, inici, final i nombres.
3. Crea el mètode run que calculi la suma de valors de l'array donades la posició inicial i final.
3. Crea un mètode per obtenir la suma de l'array.
4. Al mètode principal, crea 10 fils i assigna'ls un fragment de l'array a cada un.
5. Sincronitza el final dels fils i recupera la suma de cada fragment en un nou array.
6. Suma els valors del nou array i mostra el resultat per pantalla.
