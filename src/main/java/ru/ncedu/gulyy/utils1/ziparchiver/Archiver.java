package ru.ncedu.gulyy.utils1.ziparchiver;

import java.io.IOException;

/**
 * Created by Константин on 25.04.2014.
 */
public interface Archiver {
    /**
     * method creates new zip archive contenting some files
     *
     * @param zipArchiveName name of created zip archive
     * @param fileNames       names of added files
     * @throws java.io.IOException
     */
    public void createZipArchiveWithFile(String zipArchiveName, String... fileNames)
            throws IOException;

    /**
     * method extract zip archive to the specified path
     *
     * @param zipArchiveName name of zip archive
     * @param path name of specified path
     * @throws java.io.IOException
     */
    public void extractZipArchiveFromFile(String zipArchiveName, String path)
            throws IOException;
}
