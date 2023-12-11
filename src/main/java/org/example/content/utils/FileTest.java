package org.example.content.utils;

import java.io.File;

public class FileTest {
    public static void main(String[] args){
        File file = new File("/");
        if (file.exists()) {
            System.out.println("File exists: " + file.getAbsolutePath());
        } else {
            System.out.println("File does not exist: " + file.getAbsolutePath());
        }
    }
}
