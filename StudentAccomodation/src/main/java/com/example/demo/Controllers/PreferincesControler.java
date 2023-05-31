package com.example.demo.Controllers;

import com.example.demo.JDBCProceduresAndFunction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

@RestController
public class PreferincesControler {

    private InsertInCSVPreferencies insertInCSVPreferencies;
    private AlgoritmDeRepartizare algoritmDeRepartizare;

    private ListOfCamine listOfCamine;
    private ListOfStudent listOfStudent;
    private PreferenciesOfStudent preferenciesOfStudent;

    @Autowired
    public PreferincesControler(InsertInCSVPreferencies insert, AlgoritmDeRepartizare algoritmDeRepartizare, ListOfCamine listOfCamine, ListOfStudent listOfStudent, PreferenciesOfStudent preferenciesOfStudent) {
        this.insertInCSVPreferencies = insert;
        this.listOfCamine=listOfCamine;
        this.listOfStudent=listOfStudent;
        this.preferenciesOfStudent=preferenciesOfStudent;
        this.algoritmDeRepartizare = algoritmDeRepartizare;

    }

    @PostMapping("/preferinte")
    public ResponseEntity<String> uploadPreferinte(@RequestParam("file") MultipartFile file) {
        try {
            String pathStocare = "D:/REPARTITIE";
            String nameOfFile = file.getContentType();
            System.out.println(nameOfFile);
            String extension = nameOfFile.split("/")[1];
            if (extension.equals("csv")) {
                String fileName = "preferinte." + extension;
                System.out.println(fileName);
                // Save the file to the upload path
                Path filePath = Path.of(pathStocare + File.separator + fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                boolean success = this.insertInCSVPreferencies.callInsertPreferencies();
                if (success)
                    return ResponseEntity.ok("INCARCAT CU SUCCES");
                else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("EROARE LA BAZA DE DATE");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("FISIERUL NU ESTE CSV");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("EROARE LA INCARCAREA FISIERULUI");
        }
    }

    @PostMapping("/repartitii")
    public ResponseEntity<String> repartitie() throws SQLException {

        algoritmDeRepartizare.repartitie();
        return ResponseEntity.ok("INCARCAT CU SUCCES");
    }
}
