# Laboratorul nr. 1

Fisierele se regasesc in directorul src. 

## Compulsory

S-au rezolvat toate cerintele date. 
Pentru calculul sumei unui numar, s-a creat o functie compute ce va returna un int.

Mod de functionare:
1. Cat timp numarul dat ca parametru are mai mult de 2 cifre, adunam intr-o variabila result ultima cifra din numar, apoi impartim numarul la 10.
2. Dupa iesirea din bucla while, se returneaza variabila result.

In main, s-au realizat urmatoarele:

1. Afisarea pe ecran a mesajului "Hello world" (linia 22);
2. Definirea unui vector de stringuri, ce contine numele unor limbaje de programare (linia 23);
3. Generarea unui numar aleator (linia 24)
4. Calcularea unui rezultat, dupa indicatiile date in cerinta (linia 25)
5. Calcularea sumei cifrelor din rezultatul de mai sus (linia 26), in cazul in care numarul are mai mult de 2 cifre atunci se apeleaza functia compute, pana cand numarul va avea doar o cifra (liniile 27-29)
6. Afisarea pe ecran a mesaului dat in cerinta (linia 30) 

## Homework

S-au realizat toate cerintele date.

S-a creat o functie printMatrix, cu rolul de a rezolva cerinta nr. 3

Aceasta functie primeste ca parametrii un "Latin Square" de tip int, o valoare booleana sense (pentru a stii daca vrem sa afisam in functie de linii sau in functie de coloane), si numarul de linii si coloane.

Parcurgem matricea prin 2 for-uri.  In cadrul primului for, se creeaza un StringBuffer (alegere facuta pentru a adauga in acesta elementele de pe linia/coloana matricei date). In al doilea for, verificam daca variabila sense este true (vrem crearea si afisarea string-urilor pe linii), caz in care adaugam in string elementul de pe linia i, coloana j, alftel (vrem crearea si afisarea string-urilor pe coloane), caz in care adaugam in string elementul de pe linia j coloana i. Apoi inseram un space pentru a fi lizibil.

Daca n-ul dat argument este mai mic sau egal cu 30_000, atunci afisam stringul (valoare luata din ceritna nr. 4).

In main s-au realizat urmatoarele:
1. Atribuirea variabilei n (int), data ca argument (linia 37);
2. Crearea unei matrici de tip int cu proprietatea de "Latin Square", si completarea acesteia (liniile 39-44);
3. Afisarea numerelor din matrice pe linii si pe coloane (liniile 46-48) *la linia 47 am afisat un mic mesaj pentru a realiza o separare intre comenzi*. Mentionez ca, daca n>30_000, nu se va afisa numerele, dar se va executa functia apelata, cu scopul de a determina timpul de rulare;
4. Afisarea timpului de executie in milisecunde (linia 35, 50 si 51). La linia 35 am extras timpul la care a inceput executia, la linia 50 timpul la care s-a terminat executia, si la 51 afisam diferenta dintre ele (adica timpul de executie).

Pentru cerinta nr. 5 s-a anexat o captura de ecran.
