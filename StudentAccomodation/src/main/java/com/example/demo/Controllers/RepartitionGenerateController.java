package com.example.demo.Controllers;

import com.example.demo.JDBCProceduresAndFunction.AlgoritmDeRepartizare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * this controller is responsible with calling the method which make the repartition
 */
@RestController
public class RepartitionGenerateController {

    private final AlgoritmDeRepartizare algoritmDeRepartizare;

    @Autowired
    public RepartitionGenerateController(AlgoritmDeRepartizare algoritmDeRepartizare) {
        this.algoritmDeRepartizare = algoritmDeRepartizare;
    }

    @PostMapping(value = "/repartitions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> repartitionStudentsInCampus() {
        Map<String, Object> response = new HashMap<>();
        boolean success = algoritmDeRepartizare.repartitie();
        if (success) {
            response.put("status", "success");
            response.put("message", "S-a incarcat cu succes");
            return ResponseEntity.ok(response);
        }else{
            response.put("status", "error");
            response.put("message", "Eroare la baza de date");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
