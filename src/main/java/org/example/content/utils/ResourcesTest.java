package org.example.content.utils;

import org.example.content.utils.Resources;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class ResourcesTest {
    @Test
//        File file = new File("src/main/java/org/example/resources/img/bg-768.jpeg");
//    if(file.exists())
//    {
//        System.out.println("File exists: " + file.getAbsolutePath());
//    } else
//    {
//        System.out.println("File does not exist: " + file.getAbsolutePath());
    public void testGetImgByName() {
        URL url = Resources.getImgByName("test.jpg");
        System.out.println(url.toString());
    }
    }



