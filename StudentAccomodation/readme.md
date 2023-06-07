# Automat de repartizare a studentilor de la Facultatea de Informatica din Iasi
(StudentAccomodation)

Acest program are rolul de a repartiza studentii in camine, in functie de preferintele lor.
Administratorul va importa prima oara un csv in care sunt prezente numele caminelor si capacitatea alocata pentru fiecare gen. Dupa importare, automat datele din csv vor fi inserate in baza de date.
Apoi, acesta va importa un csv cu studentii si 5 preferinte de camin. Dupa importare, automat datele din csv vor fi inserate in baza de date.
Apoi, administratorul va apasa pe butonul *Repartizare studenti*, ce va apela un algoritm de repartizare a studentilor, ce se ocupa si de introducerea acestei repartitii in baza de date.
Apoi, cand administratorul va apasa pe butonul *Exportare pdf*, serverul va genera un fisier pdf cu rezultatul algoritmului de repartizare.
