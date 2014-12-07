package ru.ncedu.gulyy.utils1.ziparchiver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/*
TODO http://stackoverflow.com/questions/14209085/how-to-define-relative-path-in-java
I cant execute this tests because they contain absolute path. So it is better to use relative path +
properties in resource folder (file like config.properties)

+ instead of // it is better to use File.separator - platform independent separator
 */

/**
 * Created by Константин on 01.12.2014.
 */
public class ZipperAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test(expected = NullPointerException.class)
    public void createZipArchiveTest_caseNullZipFileName() {
        ZipperApp.createZipArchive(null, "D:\\Java\\ZipArchiver\\src\\test\\workspace\\files\\1.xml");
    }

    @Test(expected = NullPointerException.class)
    public void createZipArchiveTest_caseNullFileNames() {
        ZipperApp.createZipArchive("D:\\Java\\ZipArchiver\\src\\test\\workspace\\zip\\result.zip", null);
    }

    @Test(expected = NullPointerException.class)
    public void createZipArchiveTest_caseNullFilesName() {
        ZipperApp.createZipArchive("D:\\Java\\ZipArchiver\\src\\test\\workspace\\zip\\result.zip",
                "D:\\Java\\ZipArchiver\\src\\test\\workspace\\files\\1.xml", null);
    }

    @Test
    //TODO Even I have exception test is marked as successful - As the result you can check that some folder exists
    public void createZipArchiveTest_caseFileNotFound() {
        ZipperApp.createZipArchive(System.getProperty("user.dir") + File.separator + "src\\test\\workspace\\zip",
                                    "classpath:ZipArchiver\\src\\test\\workspace\\files\\1.xml");
    }

    @Test
    public void createZipArchiveTest_caseXmlFile() {
        ZipperApp.createZipArchive("D:\\Java\\ZipArchiver\\src\\test\\workspace\\zip\\result.zip",
                "D:\\Java\\ZipArchiver\\src\\test\\workspace\\files\\1.xml");

        assertEquals("Successfully archived", outContent.toString());
    }

    @Test
    public void createZipArchiveTest_caseXmlTxtPptxDatFiles(){
        ZipperApp.createZipArchive("D:\\Java\\ZipArchiver\\src\\test\\workspace\\zip\\result.zip",
                "D:\\Java\\ZipArchiver\\src\\test\\workspace\\files\\1.xml",
                "D:\\Java\\ZipArchiver\\src\\test\\workspace\\files\\app.txt",
                "D:\\Java\\ZipArchiver\\src\\test\\workspace\\files\\xtex.dat",
                "D:\\Java\\ZipArchiver\\src\\test\\workspace\\files\\02-Java_basics.pptx");

        assertEquals("Successfully archived", outContent.toString());
    }

    @Test(expected = NullPointerException.class)
    public void extractZipArchiveTest_caseNullZipFileName() {
        ZipperApp.exctractZipArchive(null, "D:\\Java\\ZipArchiver\\src\\test\\workspace\\zip");
    }

    @Test
    public void extractZipArchiveTest_caseNullPath() {
        ZipperApp.exctractZipArchive("D:\\Java\\ZipArchiver\\src\\test\\workspace\\zip\\result.zip", null);
        assertEquals("Successfully unzipped", outContent.toString());
    }

    @Test
    public void extractZipArchiveTest_caseFull() {
        ZipperApp.exctractZipArchive("D:\\Java\\ZipArchiver\\src\\test\\workspace\\zip\\result.zip", "D:\\Java\\ZipArchiver\\src\\test\\workspace\\unzip");
        assertEquals("Successfully unzipped", outContent.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }


}
