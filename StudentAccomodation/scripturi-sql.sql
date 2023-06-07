create table STUDENT
(
    ID_STUDENT NUMBER       not null
        primary key,
    NUME       VARCHAR2(30) not null,
    PRENUME    VARCHAR2(30) not null,
    AN         NUMBER       not null,
    GEN        VARCHAR2(1)  not null,
    PUNCTAJ    NUMBER       not null
)
/

create trigger INSERT_INDEX_STUD
    before insert
    on STUDENT
    for each row
begin
    select INDEX_STUDENT.nextval
    into :new.id_student
    from dual;
end;
/

create table CAMINE
(
    ID_CAMIN             NUMBER       not null
        primary key,
    NUME_CAMIN           VARCHAR2(10) not null,
    CAPACITATE_ALOCATA_M NUMBER       not null,
    CAPACITATE_ALOCATA_F NUMBER       not null
)
/

create trigger INSERT_INDEX_CAMIN
    before insert
    on CAMINE
    for each row
begin
    select INDEX_CAMIN.nextval
    into :new.id_camin
    from dual;
end;
/

create table PREFERINTE_CAMIN
(
    ID_STUDENT   NUMBER not null
        primary key
        constraint FK_PREF_ID_STUDENT
            references STUDENT,
    PREFERINTA_1 NUMBER not null
        constraint FK_PREF_ID_CAMIN1
            references CAMINE,
    PREFERINTA_2 NUMBER not null
        constraint FK_PREF_ID_CAMIN2
            references CAMINE,
    PREFERINTA_3 NUMBER not null
        constraint FK_PREF_ID_CAMIN3
            references CAMINE,
    PREFERINTA_4 NUMBER not null
        constraint FK_PREF_ID_CAMIN4
            references CAMINE,
    PREFERINTA_5 NUMBER not null
        constraint FK_PREF_ID_CAMIN5
            references CAMINE
)
/

create table REPARTIZARE
(
    ID_STUDENT NUMBER not null
        constraint FK_REP_ID_STUDENT
            references STUDENT,
    ID_CAMIN   NUMBER not null
        constraint FK_REP_ID_CAMIN
            references CAMINE,
    primary key (ID_STUDENT, ID_CAMIN)
)
/

create sequence INDEX_CAMIN
/
create sequence INDEX_STUDENT
/

create procedure insert_in_camine as
    v_fisier UTL_FILE.FILE_TYPE;
    v_linie  varchar2(300);
        v_id int;
        v_ok int;
BEGIN
    delete from REPARTIZARE;
    delete from PREFERINTE_CAMIN;
    delete from CAMINE;
    v_fisier := UTL_FILE.FOPEN('MYDIR', 'info_locuri.csv', 'R');
    loop
        utl_file.get_line(v_fisier, v_linie);
        exit when v_linie is null;
        insert into CAMINE (nume_camin, capacitate_alocata_M, CAPACITATE_ALOCATA_F)
        values (regexp_substr(v_linie, '[^,]+', 1, 1),
                regexp_substr(v_linie, '[^,]+', 1, 2),
                regexp_substr(v_linie, '[^,]+', 1, 3));
    end loop;
    begin
        UTL_FILE.FCLOSE(v_fisier);
    exception
        WHEN OTHERS THEN
            UTL_FILE.FCLOSE(v_fisier);
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END;
exception
    WHEN no_data_found THEN UTL_FILE.FCLOSE(v_fisier);
END insert_in_camine;
/

create procedure insert_preferinte as
    v_fisier  UTL_FILE.FILE_TYPE;
    v_linie   varchar2(300);
    v_id_stud number;
    v_id1     number;
    v_id2     number;
    v_id3     number;
    v_id4     number;
    v_id5     number;
        nume varchar2(30);
begin
    delete from PREFERINTE_CAMIN;
    v_fisier := UTL_FILE.FOPEN('MYDIR', 'preferinte.csv', 'R');
    loop
        UTL_FILE.GET_LINE(v_fisier, v_linie);
        DBMS_OUTPUT.PUT_LINE('CITIT');
        exit when v_linie is null;
        nume := regexp_substr(v_linie, '[^,]+', 1, 1);
        DBMS_OUTPUT.PUT_LINE(nume);
        select ID_STUDENT
        into v_id_stud
        from STUDENT
        where TRIM(nume) = TRIM(regexp_substr(v_linie, '[^,]+', 1, 1))
          and prenume = regexp_substr(v_linie, '[^,]+', 1, 2);
        DBMS_OUTPUT.PUT_LINE(v_id_stud);
        select Id_camin into v_id1 from CAMINE where nume_camin = regexp_substr(v_linie, '[^,]+', 1, 3);
        DBMS_OUTPUT.PUT_LINE(v_id1);
        select Id_camin into v_id2 from CAMINE where nume_camin = regexp_substr(v_linie, '[^,]+', 1, 4);
        DBMS_OUTPUT.PUT_LINE(v_id2);
        select Id_camin into v_id3 from CAMINE where nume_camin = regexp_substr(v_linie, '[^,]+', 1, 5);
        DBMS_OUTPUT.PUT_LINE(v_id3);

        select Id_camin into v_id4 from CAMINE where nume_camin = regexp_substr(v_linie, '[^,]+', 1, 6);
        DBMS_OUTPUT.PUT_LINE(v_id4);

        select Id_camin into v_id5 from CAMINE where nume_camin = regexp_substr(v_linie, '[^,]+', 1, 7);
        DBMS_OUTPUT.PUT_LINE(v_id5);

        INSERT INTO PREFERINTE_CAMIN (ID_STUDENT, PREFERINTA_1, PREFERINTA_2, PREFERINTA_3, PREFERINTA_4, PREFERINTA_5)
            VALUES (v_id_stud, v_id1, v_id2, v_id3, v_id4, v_id5);
    end loop;
    BEGIN
        UTL_FILE.FCLOSE(v_fisier);
    EXCEPTION
        WHEN OTHERS THEN
            UTL_FILE.FCLOSE(v_fisier);
            DBMS_OUTPUT.PUT_LINE('Error1: ' || SQLERRM);
    END;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error2: ' || SQLERRM);
        UTL_FILE.FCLOSE(v_fisier);
END insert_preferinte;
/

create procedure lista_repartizare as
    v_fisier UTL_FILE.FILE_TYPE;
    v_camin  varchar2(10);
    Cursor informatii (ani STUDENT.AN%type, genul STUDENT.GEN%type) IS
        SELECT student.nume, student.prenume, STUDENT.AN, student.punctaj, REPARTIZARE.ID_CAMIN
        from STUDENT
                 join REPARTIZARE
                      on student.id_student = REPARTIZARE.ID_STUDENT and STUDENT.an = ani and STUDENT.gen = genul
        order by student.punctaj desc;
BEGIN
    v_fisier := UTL_FILE.FOPEN('MYDIR', 'lista_repartitie.csv', 'W');
    UTL_FILE.PUT_LINE(v_fisier, 'NUME STUDENT, PRENUME STUDENT, AN, PUNCTAJ, CAMIN REPARTIZAT');
    for v_linie in informatii(1, 'M')
        loop
            select NUME_CAMIN into v_camin from CAMINE where id_camin = v_linie.ID_CAMIN;
            UTL_FILE.PUT_LINE(v_fisier,
                              v_linie.nume || ',' || v_linie.prenume || ',' || v_linie.AN || ',' || v_linie.punctaj ||
                              ',' || v_camin);
        end loop;
    for v_linie in informatii(1, 'F')
        loop
            select NUME_CAMIN into v_camin from CAMINE where id_camin = v_linie.ID_CAMIN;
            UTL_FILE.PUT_LINE(v_fisier,
                              v_linie.nume || ',' || v_linie.prenume || ',' || v_linie.AN || ',' || v_linie.punctaj ||
                              ',' || v_camin);

        end loop;
    for v_linie in informatii(2, 'M')
        loop
            select NUME_CAMIN into v_camin from CAMINE where id_camin = v_linie.ID_CAMIN;
            UTL_FILE.PUT_LINE(v_fisier,
                              v_linie.nume || ',' || v_linie.prenume || ',' || v_linie.AN || ',' || v_linie.punctaj ||
                              ',' || v_camin);

        end loop;
    for v_linie in informatii(2, 'F')
        loop
            select NUME_CAMIN into v_camin from CAMINE where id_camin = v_linie.ID_CAMIN;
            UTL_FILE.PUT_LINE(v_fisier,
                              v_linie.nume || ',' || v_linie.prenume || ',' || v_linie.AN || ',' || v_linie.punctaj ||
                              ',' || v_camin);

        end loop;
    for v_linie in informatii(3, 'M')
        loop
            select NUME_CAMIN into v_camin from CAMINE where id_camin = v_linie.ID_CAMIN;
            UTL_FILE.PUT_LINE(v_fisier,
                              v_linie.nume || ',' || v_linie.prenume || ',' || v_linie.AN || ',' || v_linie.punctaj ||
                              ',' || v_camin);

        end loop;
    for v_linie in informatii(3, 'F')
        loop
            select NUME_CAMIN into v_camin from CAMINE where id_camin = v_linie.ID_CAMIN;
            UTL_FILE.PUT_LINE(v_fisier,
                              v_linie.nume || ',' || v_linie.prenume || ',' || v_linie.AN || ',' || v_linie.punctaj ||
                              ',' || v_camin);

        end loop;
    UTL_FILE.FCLOSE(v_fisier);
END lista_repartizare;
/

create procedure insertation as
    TYPE varr IS VARRAY(1000) OF varchar2(255);
    lista_nume           varr := varr('Ababei', 'Acasandrei', 'Adascalitei', 'Afanasie', 'Agafitei', 'Agape', 'Aioanei',
                                      'Alexandrescu', 'Alexandru', 'Alexe', 'Alexii', 'Amarghioalei', 'Ambroci',
                                      'Andonesei', 'Andrei', 'Andrian', 'Andrici', 'Andronic', 'Andros', 'Anghelina',
                                      'Anita', 'Antochi', 'Antonie', 'Apetrei', 'Apostol', 'Arhip', 'Arhire', 'Arteni',
                                      'Arvinte', 'Asaftei', 'Asofiei', 'Aungurenci', 'Avadanei', 'Avram', 'Babei',
                                      'Baciu', 'Baetu', 'Balan', 'Balica', 'Banu', 'Barbieru', 'Barzu', 'Bazgan',
                                      'Bejan', 'Bejenaru', 'Belcescu', 'Belciuganu', 'Benchea', 'Bilan', 'Birsanu',
                                      'Bivol', 'Bizu', 'Boca', 'Bodnar', 'Boistean', 'Borcan', 'Bordeianu', 'Botezatu',
                                      'Bradea', 'Braescu', 'Budaca', 'Bulai', 'Bulbuc-aioanei', 'Burlacu', 'Burloiu',
                                      'Bursuc', 'Butacu', 'Bute', 'Buza', 'Calancea', 'Calinescu', 'Capusneanu',
                                      'Caraiman', 'Carbune', 'Carp', 'Catana', 'Catiru', 'Catonoiu', 'Cazacu',
                                      'Cazamir', 'Cebere', 'Cehan', 'Cernescu', 'Chelaru', 'Chelmu', 'Chelmus',
                                      'Chibici', 'Chicos', 'Chilaboc', 'Chile', 'Chiriac', 'Chirila', 'Chistol',
                                      'Chitic', 'Chmilevski', 'Cimpoesu', 'Ciobanu', 'Ciobotaru', 'Ciocoiu', 'Ciofu',
                                      'Ciornei', 'Citea', 'Ciucanu', 'Clatinici', 'Clim', 'Cobuz', 'Coca', 'Cojocariu',
                                      'Cojocaru', 'Condurache', 'Corciu', 'Corduneanu', 'Corfu', 'Corneanu',
                                      'Corodescu', 'Coseru', 'Cosnita', 'Costan', 'Covatariu', 'Cozma', 'Cozmiuc',
                                      'Craciunas', 'Crainiceanu', 'Creanga', 'Cretu', 'Cristea', 'Crucerescu',
                                      'Cumpata', 'Curca', 'Cusmuliuc', 'Damian', 'Damoc', 'Daneliuc', 'Daniel',
                                      'Danila', 'Darie', 'Dascalescu', 'Dascalu', 'Diaconu', 'Dima', 'Dimache', 'Dinu',
                                      'Dobos', 'Dochitei', 'Dochitoiu', 'Dodan', 'Dogaru', 'Domnaru', 'Dorneanu',
                                      'Dragan', 'Dragoman', 'Dragomir', 'Dragomirescu', 'Duceac', 'Dudau', 'Durnea',
                                      'Edu', 'Eduard', 'Eusebiu', 'Fedeles', 'Ferestraoaru', 'Filibiu', 'Filimon',
                                      'Filip', 'Florescu', 'Folvaiter', 'Frumosu', 'Frunza', 'Galatanu', 'Gavrilita',
                                      'Gavriliuc', 'Gavrilovici', 'Gherase', 'Gherca', 'Ghergu', 'Gherman', 'Ghibirdic',
                                      'Giosanu', 'Gitlan', 'Giurgila', 'Glodeanu', 'Goldan', 'Gorgan', 'Grama',
                                      'Grigore', 'Grigoriu', 'Grosu', 'Grozavu', 'Gurau', 'Haba', 'Harabula', 'Hardon',
                                      'Harpa', 'Herdes', 'Herscovici', 'Hociung', 'Hodoreanu', 'Hostiuc', 'Huma',
                                      'Hutanu', 'Huzum', 'Iacob', 'Iacobuta', 'Iancu', 'Ichim', 'Iftimesei', 'Ilie',
                                      'Insuratelu', 'Ionesei', 'Ionesi', 'Ionita', 'Iordache', 'Iordache-tiroiu',
                                      'Iordan', 'Iosub', 'Iovu', 'Irimia', 'Ivascu', 'Jecu', 'Jitariuc', 'Jitca',
                                      'Joldescu', 'Juravle', 'Larion', 'Lates', 'Latu', 'Lazar', 'Leleu', 'Leon',
                                      'Leonte', 'Leuciuc', 'Leustean', 'Luca', 'Lucaci', 'Lucasi', 'Luncasu',
                                      'Lungeanu', 'Lungu', 'Lupascu', 'Lupu', 'Macariu', 'Macoveschi', 'Maftei',
                                      'Maganu', 'Mangalagiu', 'Manolache', 'Manole', 'Marcu', 'Marinov', 'Martinas',
                                      'Marton', 'Mataca', 'Matcovici', 'Matei', 'Maties', 'Matrana', 'Maxim',
                                      'Mazareanu', 'Mazilu', 'Mazur', 'Melniciuc-puica', 'Micu', 'Mihaela', 'Mihai',
                                      'Mihaila', 'Mihailescu', 'Mihalachi', 'Mihalcea', 'Mihociu', 'Milut', 'Minea',
                                      'Minghel', 'Minuti', 'Miron', 'Mitan', 'Moisa', 'Moniry-abyaneh', 'Morarescu',
                                      'Morosanu', 'Moscu', 'Motrescu', 'Motroi', 'Munteanu', 'Murarasu', 'Musca',
                                      'Mutescu', 'Nastaca', 'Nechita', 'Neghina', 'Negrus', 'Negruser', 'Negrutu',
                                      'Nemtoc', 'Netedu', 'Nica', 'Nicu', 'Oana', 'Olanuta', 'Olarasu', 'Olariu',
                                      'Olaru', 'Onu', 'Opariuc', 'Oprea', 'Ostafe', 'Otrocol', 'Palihovici', 'Pantiru',
                                      'Pantiruc', 'Paparuz', 'Pascaru', 'Patachi', 'Patras', 'Patriche', 'Perciun',
                                      'Perju', 'Petcu', 'Pila', 'Pintilie', 'Piriu', 'Platon', 'Plugariu', 'Podaru',
                                      'Poenariu', 'Pojar', 'Popa', 'Popescu', 'Popovici', 'Poputoaia', 'Postolache',
                                      'Predoaia', 'Prisecaru', 'Procop', 'Prodan', 'Puiu', 'Purice', 'Rachieru',
                                      'Razvan', 'Reut', 'Riscanu', 'Riza', 'Robu', 'Roman', 'Romanescu', 'Romaniuc',
                                      'Rosca', 'Rusu', 'Samson', 'Sandu', 'Sandulache', 'Sava', 'Savescu', 'Schifirnet',
                                      'Scortanu', 'Scurtu', 'Sfarghiu', 'Silitra', 'Simiganoschi', 'Simion',
                                      'Simionescu', 'Simionesei', 'Simon', 'Sitaru', 'Sleghel', 'Sofian', 'Soficu',
                                      'Sparhat', 'Spiridon', 'Stan', 'Stavarache', 'Stefan', 'Stefanita', 'Stingaciu',
                                      'Stiufliuc', 'Stoian', 'Stoica', 'Stoleru', 'Stolniceanu', 'Stolnicu', 'Strainu',
                                      'Strimtu', 'Suhani', 'Tabusca', 'Talif', 'Tanasa', 'Teclici', 'Teodorescu',
                                      'Tesu', 'Tifrea', 'Timofte', 'Tincu', 'Tirpescu', 'Toader', 'Tofan', 'Toma',
                                      'Toncu', 'Trifan', 'Tudosa', 'Tudose', 'Tuduri', 'Tuiu', 'Turcu', 'Ulinici',
                                      'Unghianu', 'Ungureanu', 'Ursache', 'Ursachi', 'Urse', 'Ursu', 'Varlan',
                                      'Varteniuc', 'Varvaroi', 'Vasilache', 'Vasiliu', 'Ventaniuc', 'Vicol', 'Vidru',
                                      'Vinatoru', 'Vlad', 'Voaides', 'Vrabie', 'Vulpescu', 'Zamosteanu', 'Zazuleac');
    lista_prenume_fete   varr := varr('Adina', 'Alexandra', 'Alina', 'Ana', 'Anca', 'Anda', 'Andra', 'Andreea',
                                      'Andreia', 'Antonia', 'Bianca', 'Camelia', 'Claudia', 'Codrina', 'Cristina',
                                      'Daniela', 'Daria', 'Delia', 'Denisa', 'Diana', 'Ecaterina', 'Elena', 'Eleonora',
                                      'Elisa', 'Ema', 'Emanuela', 'Emma', 'Gabriela', 'Georgiana', 'Ileana', 'Ilona',
                                      'Ioana', 'Iolanda', 'Irina', 'Iulia', 'Iuliana', 'Larisa', 'Laura', 'Loredana',
                                      'Madalina', 'Malina', 'Manuela', 'Maria', 'Mihaela', 'Mirela', 'Monica', 'Oana',
                                      'Paula', 'Petruta', 'Raluca', 'Sabina', 'Sanziana', 'Simina', 'Simona', 'Stefana',
                                      'Stefania', 'Tamara', 'Teodora', 'Theodora', 'Vasilica', 'Xena');
    lista_prenume_baieti varr := varr('Adrian', 'Alex', 'Alexandru', 'Alin', 'Andreas', 'Andrei', 'Aurelian',
                                      'Beniamin', 'Bogdan', 'Camil', 'Catalin', 'Cezar', 'Ciprian', 'Claudiu', 'Codrin',
                                      'Constantin', 'Corneliu', 'Cosmin', 'Costel', 'Cristian', 'Damian', 'Dan',
                                      'Daniel', 'Danut', 'Darius', 'Denise', 'Dimitrie', 'Dorian', 'Dorin', 'Dragos',
                                      'Dumitru', 'Eduard', 'Elvis', 'Emil', 'Ervin', 'Eugen', 'Eusebiu', 'Fabian',
                                      'Filip', 'Florian', 'Florin', 'Gabriel', 'George', 'Gheorghe', 'Giani', 'Giulio',
                                      'Iaroslav', 'Ilie', 'Ioan', 'Ion', 'Ionel', 'Ionut', 'Iosif', 'Irinel', 'Iulian',
                                      'Iustin', 'Laurentiu', 'Liviu', 'Lucian', 'Marian', 'Marius', 'Matei', 'Mihai',
                                      'Mihail', 'Nicolae', 'Nicu', 'Nicusor', 'Octavian', 'Ovidiu', 'Paul', 'Petru',
                                      'Petrut', 'Radu', 'Rares', 'Razvan', 'Richard', 'Robert', 'Roland', 'Rolland',
                                      'Romanescu', 'Sabin', 'Samuel', 'Sebastian', 'Sergiu', 'Silviu', 'Stefan',
                                      'Teodor', 'Teofil', 'Theodor', 'Tudor', 'Vadim', 'Valentin', 'Valeriu', 'Vasile',
                                      'Victor', 'Vlad', 'Vladimir', 'Vladut');
    v_nume               VARCHAR2(255);
    v_prenume            VARCHAR2(255);
    v_prenume1           VARCHAR2(255);
    v_prenume2           VARCHAR2(255);
    v_gen                varchar2(1);
    v_an                 int;
    v_contor             int;
    v_medie              number;
    v_punctaj            integer;
        v_i integer;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Inserarea a 1200 studenti...');
    FOR v_i IN 1..1200
        LOOP
        DBMS_OUTPUT.PUT_LINE('HELLO');
        --SELECT SUBSTR(NAME, INSTR(NAME,' ')) INTO v_nume FROM (SELECT * FROM USERS WHERE INSTR(NAME,' ')>1 ORDER BY DBMS_RANDOM.RANDOM) WHERE ROWNUM=1;
        --IF length(v_nume)>20 then v_nume:=substr(v_nume,1,20); end if;
            v_nume := lista_nume(TRUNC(DBMS_RANDOM.VALUE(0, lista_nume.count)) + 1);
            IF (DBMS_RANDOM.VALUE(0, 100) < 50) THEN
                v_prenume1 := lista_prenume_fete(TRUNC(DBMS_RANDOM.VALUE(0, lista_prenume_fete.count)) + 1);
                LOOP
                    v_prenume2 := lista_prenume_fete(TRUNC(DBMS_RANDOM.VALUE(0, lista_prenume_fete.count)) + 1);
                    exit when v_prenume1 <> v_prenume2;
                END LOOP;
                v_gen := 'F';
            ELSE
                v_prenume1 := lista_prenume_baieti(TRUNC(DBMS_RANDOM.VALUE(0, lista_prenume_baieti.count)) + 1);
                LOOP
                    v_prenume2 := lista_prenume_baieti(TRUNC(DBMS_RANDOM.VALUE(0, lista_prenume_baieti.count)) + 1);
                    exit when v_prenume1 <> v_prenume2;
                END LOOP;
                v_gen := 'M';
            END IF;

            IF (DBMS_RANDOM.VALUE(0, 100) < 60) THEN
                IF LENGTH(v_prenume1 || ' ' || v_prenume2) <= 20 THEN
                    v_prenume := v_prenume1 || ' ' || v_prenume2;
                END IF;
            else
                v_prenume := v_prenume1;
            END IF;
            loop
                v_an := TRUNC(DBMS_RANDOM.VALUE(0, 3)) + 1;
                select count(*) into v_contor from student where an = v_an;
                exit when v_contor < 400;
            end loop;

            if (v_an = 1) then
                v_medie := DBMS_RANDOM.VALUE(8, 10);
                v_punctaj := (v_medie * 300) / 10;
            else
                v_punctaj := DBMS_RANDOM.VALUE(150, 300);
            end if;


            DBMS_OUTPUT.PUT_LINE (v_nume||' '||v_prenume ||' '|| v_an ||' '|| v_gen||' '|| v_punctaj);
            insert into student values (1, v_nume, v_prenume, v_an, v_gen, v_punctaj);
        END LOOP;
    DBMS_OUTPUT.PUT_LINE('Inserarea a 1200 studenti... GATA !');
end insertation;
/

