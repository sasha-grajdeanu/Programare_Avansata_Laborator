# Automat de repartizare a studenților de la Facultatea de Informatică din Iasi
(Student Accomodation)

Acest program are rolul de a realiza repartizarea studenților în cămine, în funcție de preferințele acestora.

Administratorul va importa prima oară un fisier de tip *csv* în care sunt prezente numele căminelor și capacitatea alocata pentru fiecare gen. 
Dupa importare, datele prezente în fișierul de tip csv vor fi inserate în baza de date.

Apoi, acesta va importa un fișier de tip *csv* cu studenții și cele 5 preferinte de cămin. Dupa importare, datele din fișierul csv vor fi inserate în baza de date.

Apoi, administratorul va apăsa pe butonul *Repartizare studenti*, ce va apela un algoritm de repartizare a studenților, ce apoi se va ocupa și cu introducerea acestei repartiții în baza de date.

Apoi, când administratorul va apăsa pe butonul *Exportare pdf*, serverul va genera un fisier de tip *pdf* cu rezultatul algoritmului de repartizare (repartizarea studenților în cămine).

O parte din cod este scris în limbajul *Java*, iar unele funcții scrise în limbajul *Java* apelează funcții și proceduri scrise în cod *PLSQL*. În cadrul aplicației s-a utilizat o bază de date *Oracle*. 
