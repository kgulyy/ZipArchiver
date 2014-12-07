package ru.ncedu.gulyy.utils1.ziparchiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Константин on 25.04.2014.
 */
public class ZipArchiver implements Archiver{
    /**
     * method creates new zip archive content some files
     *
     * @param zipArchiveName name of created zip archive
     * @param fileNames      names of added files
     * @throws java.io.IOException
     */
    @Override
    public void createZipArchiveWithFile(String zipArchiveName, String... fileNames) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipArchiveName));

        for (String fileName: fileNames) {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);

            writeFromFisToZos(fis, zos);
            fis.close();
            zos.closeEntry();
        }

        zos.close();
    }

    /**
     * method extract zip archive to the specified path
     *
     * @param zipArchiveName name of zip archive
     * @param path           name of specified path
     * @throws java.io.IOException
     */
    @Override
    public void extractZipArchiveFromFile(String zipArchiveName, String path) throws IOException {
        // open the zip file
        ZipInputStream in = new ZipInputStream(new FileInputStream(zipArchiveName));
        FileOutputStream out = null;
        // get first entry
        ZipEntry entry = null;
        if (!Files.exists(Paths.get(path))) {
            new File(path).mkdirs();
        }
        while ((entry = in.getNextEntry()) != null) {
            String outFileName = entry.getName();

            // open the output file
            if (entry.isDirectory()) {
                new File(path, outFileName).mkdirs();
            } else {
                out = new FileOutputStream(new File(path, outFileName));
            }
            writeFromZisToFos(in, out);
            out.close();
        }

    }

    protected void writeFromZisToFos(ZipInputStream in, FileOutputStream out) throws IOException {
        byte[] buffer = new byte[8000];
        int length;
        while (true) {
            length = in.read(buffer);
            if (length < 0) break;
            out.write(buffer, 0, length);
        }
    }


    protected void writeFromFisToZos(FileInputStream inputStream, ZipOutputStream zipOutputStream) throws IOException {
        byte[] buffer = new byte[8000];
        int length;
        while (true) {
            length = inputStream.read(buffer);
            if (length < 0) break;
            zipOutputStream.write(buffer, 0, length);
        }
    }
}
