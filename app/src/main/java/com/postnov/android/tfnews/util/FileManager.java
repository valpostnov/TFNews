package com.postnov.android.tfnews.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import timber.log.Timber;

/**
 * Created by platon on 01.11.2016.
 */

public class FileManager {

    public boolean mkDir(File dir) {
        return dir.mkdir();
    }

    public void writeToFile(File file, String fileContent) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(fileContent);
        } catch (IOException e) {
            Timber.tag("FileManager").e(e, e.getMessage());
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    Timber.tag("FileManager").e(e, e.getMessage());
                }
            }
        }
    }

    public String readFileContent(File file) {
        StringBuilder fileContentBuilder = new StringBuilder();
        if (file.exists()) {
            String stringLine;
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while ((stringLine = bufferedReader.readLine()) != null) {
                    fileContentBuilder.append(stringLine).append("\n");
                }
                bufferedReader.close();

            } catch (IOException e) {
                Timber.tag("FileManager").e(e, e.getMessage());
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        Timber.tag("FileManager").e(e, e.getMessage());
                    }
                }
            }
        }

        return fileContentBuilder.toString();
    }
}
