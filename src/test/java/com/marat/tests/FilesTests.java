package com.marat.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesTests {

    @Test
    public void pdfTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("file_pdf.pdf");
        PDF parsedPdf = new PDF(stream);
        assertThat(parsedPdf.text).contains("Welcome to Smallpdf");
    }

    @Test
    public void xlsTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("file_xls.xls");
        XLS xlsFile = new XLS(stream);
        Assertions.assertEquals("Weiland", xlsFile.excel.getSheetAt(0).getRow(9).getCell(2).getStringCellValue());
    }

    @Test
    public void xlsxTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("file_xlsx.xlsx");
        XLS xlsxFile = new XLS(stream);
        Assertions.assertEquals("макароны отварные", xlsxFile.excel.getSheetAt(0).getRow(3).getCell(3).getStringCellValue());
    }

    @Test
    public void txtTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("file_txt.txt");
        String txt = new String(stream.readAllBytes());
        Assertions.assertTrue(txt.contains("Quod equidem non reprehendo;"));
    }

    @Test
    public void docTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("file_doc.doc");
        String doc = new String(stream.readAllBytes());
        System.out.println(doc);
        Assertions.assertTrue(doc.contains("Curabitur bibendum ante urna, sed blandit libero egestas id."));
    }

    @Test
    public void docxTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("file_docx.docx");
        String docx = new String(stream.readAllBytes());
        Assertions.assertTrue(docx.contains("Авторизация"));
    }
}


