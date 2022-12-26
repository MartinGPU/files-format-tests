package com.marat.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
        System.out.println(txt);
        Assertions.assertTrue(txt.contains("Quod equidem non reprehendo;"));
    }

    @Test
    public void docTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("file_doc.doc");
        WordExtractor extractor;
        HWPFDocument document = new HWPFDocument(stream);
        extractor = new WordExtractor(document);
        String s = new String(extractor.getText().getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
        assertThat(s).contains("Curabitur bibendum ante urna");
    }

    @Test
    public void docxTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("file_docx.docx");
        XWPFWordExtractor extractor;
        XWPFDocument document = new XWPFDocument(stream);
        extractor = new XWPFWordExtractor(document);
        String s = new String(extractor.getText().getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
        Assertions.assertTrue(s.contains("Sit sane ista voluptas"));
    }

    @Test
    public void zipTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String entryAsString = null;
        try (ZipInputStream stream = new ZipInputStream(classLoader.getResourceAsStream("zip/file_zip.zip"))) {
            ZipEntry entry;
            while ((entry = stream.getNextEntry()) != null) {
                entryAsString = IOUtils.toString(stream, StandardCharsets.UTF_8);
            }
            Assertions.assertTrue(entryAsString.contains("Nihil sane"));
        }
    }
}


