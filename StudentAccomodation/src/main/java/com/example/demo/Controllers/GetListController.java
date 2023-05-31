package com.example.demo.Controllers;

import com.example.demo.JDBCProceduresAndFunction.CreateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class GetListController {

    private CreateList createList;

    @Autowired
    public GetListController(CreateList createList) {
        this.createList = createList;
    }

    @GetMapping("/repartizare")
    public ResponseEntity<FileSystemResource> downloadFile() {

        boolean success = createList.callLista();
        if (success) {
            String path = "D:\\REPARTITIE\\lista_repartitie.csv";

            File file = new File(path);

            if (file.exists()) {

                MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

                HttpHeaders headers = new HttpHeaders();
                headers.setContentDispositionFormData("attachment", "lista-repartitie.csv");

                FileSystemResource resource = new FileSystemResource(file);

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(mediaType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(500).build();
    }
}
