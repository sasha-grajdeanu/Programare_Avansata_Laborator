package com.example.demo.Controllers;

import com.example.demo.JDBCProceduresAndFunction.InsertInCSVLocuri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.util.HashMap;
import java.util.Map;

@RestController
public class InsertCampusInDatabaseController {
    private InsertInCSVLocuri insertInCSVLocuri;

    @Autowired
    public InsertCampusInDatabaseController(InsertInCSVLocuri insert) {
        this.insertInCSVLocuri = insert;
    }

    @PostMapping(value = "/campus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> uploadCampus(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            String pathStocare = "D:/REPARTITIE";
            String nameOfFile = file.getOriginalFilename();
            System.out.println(nameOfFile);
            String extension = nameOfFile.substring(nameOfFile.lastIndexOf(".") + 1);
            if (extension.equalsIgnoreCase("csv")) {
                String fileName = "info_locuri." + extension;

                Path filePath = Path.of(pathStocare + File.separator + fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                boolean success = insertInCSVLocuri.callInsertInCamine();
                if (success) {
                    response.put("status", "success");
                    response.put("message", "S-a incarcat cu succes");
                    return ResponseEntity.ok(response);
                } else {
                    response.put("status", "error");
                    response.put("message", "Eroare la baza de date");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            } else {
                response.put("status", "error");
                response.put("message", "Fisierul nu este csv.");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Eroare la incarcarea fisierului");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
