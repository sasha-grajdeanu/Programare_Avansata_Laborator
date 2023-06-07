package com.example.demo.Controllers;

import com.example.demo.JDBCProceduresAndFunction.CsvToPdf;
import com.example.demo.JDBCProceduresAndFunction.CreateList;
import com.itextpdf.text.DocumentException;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * this controller is responsible with getting the result of repartition in pdf format
 */
@RestController
public class GetListController {

    private final CreateList createList;

    @Autowired
    public GetListController(CreateList createList) {
        this.createList = createList;
    }

    @GetMapping("/lists")
    public ResponseEntity<FileSystemResource> downloadFile() {
        boolean success = createList.callLista();
        if (success) {
            String path = "D:\\REPARTITIE\\lista_repartitie.csv";
            CsvToPdf csvToPdf = new CsvToPdf();
            try {
                csvToPdf.convert(path);
            } catch (CsvValidationException | IOException | DocumentException e) {
                System.err.println(e.getMessage());
                return ResponseEntity.status(500).build();
            }
            File file = new File("D:\\REPARTITIE\\repartizare.pdf");
            if (file.exists()) {
                MediaType mediaType = MediaType.APPLICATION_PDF;
                HttpHeaders headers = new HttpHeaders();
                headers.setContentDispositionFormData("attachment", "repartizare.pdf");
                FileSystemResource resource = new FileSystemResource(file);
                return ResponseEntity.ok().headers(headers).contentType(mediaType).body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(500).build();
    }
}
