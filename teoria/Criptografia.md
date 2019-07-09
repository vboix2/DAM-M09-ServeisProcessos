
# Criptografia

La criptografia s'encarrega d'estudiar com protegir la informació mitjançant la seva transformació. El procés de **xifrat** consisteix a transformar un conjunt de dades original (*dades en clar*) a un nou conjunt de dades totalment diferent i incomprensible (*dades xifrades*). El procés invers permet recuperar la informació original i s'anomena **desxifrat**.

Avui en dia és molt important garantir que les aplicacions gestionin les dades de manera segura, és a dir, garantint que la informació que es guarda i s'envia només pugui ser llegida per les persones interessades. La **Java Cryptography Extension (JCE)** és una extensió de la plataforma Java que proporciona implementacions d'algorismes per tal de generar i intercanviar claus, encriptar dades i autentificar missatges.

## 1. Xifrat simètric

El mecanisme de xifrat més utilitzat és el de **clau simètrica**. En aquest mecanisme existeix una clau o secret, compartida entre tots els que han de llegir les dades, que permet xifrar-les i desxifrar-les.

Un dels primers en utilitzar un algorisme de xifrat simètric va ser Juli Cèsar, que aplicava un desplaçament de 3 lletres a cada caràcter del text (xifrat Cèsar). Un altre mecanisme de xifrat per desplaçament, molt habitual en jocs, és ROT13, que aplica un desplaçament de 13 lletres.

## 2. Xifrat assimètric
