package com.analyze.multithreaded.fileprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessingUtils {
    public static File getFileAtLocation (String directoryName, String fileName) {
        if (directoryName == null) directoryName = "";
        String filePath = "%s/%s".formatted(directoryName, fileName);
        var file = new File(filePath);

        return file.exists() && file.isFile() ? file : null;
    }
    public static List<String> readFile (File file) {
        var textList = new ArrayList<String>();
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file))) {

            while ((line = bufferedReader.readLine()) != null)
                textList.add(line);

        } catch (IOException e) { throw new RuntimeException(e); }
        return textList;
    }

    public static List<String> readFileLocation (String directoryName, String fileName) {
        var file = getFileAtLocation(directoryName, fileName);
        if (file == null) return null;
        return readFile(file);
    }

    public static boolean isValidDirectory(File directoryPath) {
        return directoryPath.exists() && directoryPath.isDirectory();
    }
}
