package com.main;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import javax.swing.filechooser.FileSystemView;
/**

/**
 * @author Shrisowdhaman on 2019-09-03.
 */
public class DocToByte {

    public static void main(String[] args) {

        try {
            byte[] bFile = null;
            Path path = null;
             JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {

                File selectedFile = jfc.getSelectedFile();
                bFile = readBytesFromFile(selectedFile.getAbsolutePath());

                path = Paths.get(selectedFile.getParent()+"\\converted.txt");
                bFile = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
            }

            String encodedString = Base64
                    .getEncoder()
                    .encodeToString(bFile);
            Files.write(path, encodedString.getBytes());

            System.out.println("Done");

            JOptionPane.showMessageDialog(null, " File converted to Byte array and moved to \n"+ path.getParent(), "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];
            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }
}
