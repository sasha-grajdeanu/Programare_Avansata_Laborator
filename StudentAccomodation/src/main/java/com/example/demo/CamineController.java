package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
public class CamineController {

    @PostMapping("/camine")
    public ResponseEntity<String> uploadCamineLocuri(@RequestParam("file") MultipartFile file) {
        try {
            String pathStocare = "D:/REPARTITIE";
            String nameOfFile = file.getContentType();
            System.out.println(nameOfFile);
            String extension = nameOfFile.split("/")[1];
            if(extension.equals("csv")) {
                String fileName = "info_locuri."+extension;
                System.out.println(fileName);
                // Save the file to the upload path
                Path filePath = Path.of(pathStocare + File.separator + fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                return ResponseEntity.ok("INCARCAT CU SUCCES");
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("FISIERUL NU ESTE CSV");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("EROARE LA INCARCAREA FISIERULUI");
        }
    }
}