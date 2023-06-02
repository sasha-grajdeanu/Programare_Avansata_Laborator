package com.example.demo.Controllers;

import com.example.demo.JDBCProceduresAndFunction.InsertInCSVLocuri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Repository
public class InsertCampusInDatabaseController {
    private InsertInCSVLocuri insertInCSVLocuri;

    @Autowired
    public InsertCampusInDatabaseController(InsertInCSVLocuri insert) {
        this.insertInCSVLocuri = insert;
    }

    @PostMapping("/campus")
    public ResponseEntity<String> uploadCampus(@RequestParam("file") MultipartFile file) {
        try {
            String pathStocare = "D:/REPARTITIE";
            String nameOfFile = file.getContentType();
            String extension = nameOfFile.split("/")[1];
            if (extension.equals("csv")) {
                String fileName = "info_locuri." + extension;
                Path filePath = Path.of(pathStocare + File.separator + fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                boolean success = insertInCSVLocuri.callInsertInCamine();
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
}
