package com.example.demo.Controllers;

import com.example.demo.JDBCProceduresAndFunction.AlgoritmDeRepartizare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
public class RepartitionGenerateController {

    private final AlgoritmDeRepartizare algoritmDeRepartizare;

    @Autowired
    public RepartitionGenerateController(AlgoritmDeRepartizare algoritmDeRepartizare) {
        this.algoritmDeRepartizare = algoritmDeRepartizare;
    }

    @PostMapping("/repartitions")
    public ResponseEntity<String> repartitionStudentsInCampus() {

        boolean success = algoritmDeRepartizare.repartitie();
        if (success) {
            return ResponseEntity.ok("INCARCAT CU SUCCES");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("EROARE LA NIVELUL BAZEI DE DATE");
        }
    }
}
