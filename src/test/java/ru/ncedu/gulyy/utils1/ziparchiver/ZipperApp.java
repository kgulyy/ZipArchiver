package ru.ncedu.gulyy.utils1.ziparchiver;

import java.io.File;
import java.io.IOException;

/**
 * Created by Константин on 25.04.2014.
 */
public class ZipperApp {
    /**
     * args[0] - Input Zip File Name
     * args[1] - Output folder
     * args[2] - 1st file name
     * args[3] - 2nd file name
     * args[4] - 3rd file name
     * args[5] - 4th file name
     */
    public static void main(String args[]) {
        ZipperApp.createZipArchive(args[0], args[2], args[3], args[4], args[5]);
        ZipperApp.exctractZipArchive(args[0], args[1]);
    }

    public static void createZipArchive(String zipArchiveName, String... fileNames) {
       //TODO Throw Null Pointer exception is a very bad practice!
        //Generally some new custom exception is provided with some code, how it is implemented in ORACLE (like ORA-XXX)
        // In this case you can throw IllegalArgumentException, or extend from this exception
        // and create your own with message
        // throwing NPE say nothing to customer which is using your application
        if (zipArchiveName == null || fileNames == null) {
            throw new NullPointerException();
        }
        for (String s : fileNames) {
            if (s == null) {
                throw new NullPointerException();
            }
        }

        Archiver zipper = new ZipArchiver();
        try {
            zipper.createZipArchiveWithFile(zipArchiveName, fileNames);
            System.out.print("Successfully archived");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exctractZipArchive(String zipArchiveName, String path) {
        if (zipArchiveName == null) {
            throw new NullPointerException();
        }
        if (path == null) {
            path = new File(System.getProperty("user.dir")).getPath() + "\\src\\test\\workspace\\unzip";
        }
        Archiver unzipper = new ZipArchiver();
        try {
            unzipper.extractZipArchiveFromFile(zipArchiveName, path);
            System.out.print("Successfully unzipped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

