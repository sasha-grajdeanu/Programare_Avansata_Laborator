package com.example.demo.Controllers;

import com.example.demo.Entities.Camin;
import com.example.demo.JDBCProceduresAndFunction.InsertInCSVLocuri;
import com.example.demo.JDBCProceduresAndFunction.ListOfCamine;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
public class CamineController {

    private InsertInCSVLocuri insertInCSVLocuri;
    private ListOfCamine listOfCamine;
    @Autowired
    public CamineController(InsertInCSVLocuri insert, ListOfCamine listOfCamine){
        this.insertInCSVLocuri = insert;
        this.listOfCamine = listOfCamine;
    }

    @SneakyThrows
    @GetMapping("/camine")
    public List<Camin> getCamine(){
        return listOfCamine.camineDate();
    }


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
                boolean success = insertInCSVLocuri.callInsertInCamine();
                if(success)
                    return ResponseEntity.ok("INCARCAT CU SUCCES");
                else{
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("EROARE LA BAZA DE DATE");
                }
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