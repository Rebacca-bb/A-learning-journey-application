package org.example.content.utils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
/**
 * @author ShiYuang
 * @date 2023/4/1 8:55
 */
public class Resources {
    /**
     * @author ShiYuang
     * @date 2023/4/1 8:56
     * Quickly get the URL of an image in the src directory after packing
     */
    public static URL getImgByName(String imgFullName) {
//        return Resources.class.getClassLoader().getResource("src/main/java/org/example/resources/img/" + imgFullName);
        String imgPath="/img/"+imgFullName;
//        String imgPath="/";
        return Resources.class.getResource(imgPath);
    }


    /**
     * Extract the specified resource file to the current running directory
     * @author ShiYuang
     * @date 2023/4/1 8:59
     */
    public static void extractFile(String fileName) {
        String filePath = "src/main/resources/data/" + fileName;
        InputStream is = Resources.class.getClassLoader().getResourceAsStream(filePath);
        File file = new File(filePath);
        try {
            file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            int index = 0;
            byte[] bytes = new byte[1024];
            while ((index = is.read(bytes)) != -1) {
                os.write(bytes, 0, index);
            }
            os.flush();
            os.close();
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Extract all data files to the current running directory
     *
     * @author ShiYuang
     * @date 2023/4/1 9:01
     */
    public static void extractData() {
        File folder = new File("src/main/resources/data/");
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
            extractFile("student.json");
        } else {
            System.out.println("Data already exist, start loading.");
        }

    }
    /**
     * Reads the contents of the specified file as String
     *
     * @author ShiYuang
     * @date 2023/4/1 9:04
     */
    public static String readDataToString(String fileName) {
        String encoding = "UTF-8";
        String fileURL = "src/main/resources/data/" + fileName;
        File file = new File(fileURL);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
