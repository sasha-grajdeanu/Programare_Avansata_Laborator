package com.example.demo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class CsvToPdf {
    /**
     * method: generate a pdf with the repartition of students
     */
    public void convert(String name) throws CsvValidationException, IOException, DocumentException {
        CSVReader reader = new CSVReader(new FileReader(name));
        String[] line;
        int lineNumber = 0;
        Document pdfData= new Document();
        PdfWriter.getInstance(pdfData, new FileOutputStream("D:\\REPARTITIE\\repartizare.pdf"));
        pdfData.open();
        PdfPTable tabel = new PdfPTable(5);
        PdfPCell celula;
        while((line = reader.readNext())!=null){
            lineNumber++;
            celula = new PdfPCell(new Phrase(line[0]));
            tabel.addCell(celula);
            celula = new PdfPCell(new Phrase(line[1]));
            tabel.addCell(celula);
            celula = new PdfPCell(new Phrase(line[2]));
            tabel.addCell(celula);
            celula = new PdfPCell(new Phrase(line[3]));
            tabel.addCell(celula);
            celula = new PdfPCell(new Phrase(line[4]));
            tabel.addCell(celula);
        }
        pdfData.add(tabel);
        pdfData.close();
    }
}
