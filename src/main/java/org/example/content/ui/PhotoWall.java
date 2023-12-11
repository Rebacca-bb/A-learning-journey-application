package org.example.content.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PhotoWall extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton uploadButton;
    private ArrayList<File> fileList;
    private String username;

    public PhotoWall(String username) {
        super("Produces");
        this.username = username;
        fileList = new ArrayList<File>();
        // 创建面板和上传按钮
        panel = new JPanel();
        uploadButton = new JButton("upload");
        uploadButton.addActionListener(this);
        // 将面板和上传按钮添加到窗口中
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(uploadButton, BorderLayout.SOUTH);
        File dir = new File(username);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                    addImageToPanel(file);
                }
            }
        }
        // 设置窗口大小和关闭操作
        setSize(500, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // 处理上传按钮的点击事件
        if (e.getSource() == uploadButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                // 检查目录是否存在，否则创建目录
                String dirPath = username;
                File dir = new File(dirPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 将文件复制到目录中
                String filePath = dirPath + "/" + file.getName();
                File destFile = new File(filePath);
                try {
                    copyFile(file, destFile);
                    fileList.add(destFile);
                    addImageToPanel(destFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    private void addImageToPanel(File file) {
        try {
            // 创建缩略图
            BufferedImage image = ImageIO.read(file);
            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);

            // 创建标签并将其添加到面板中
            JLabel label = new JLabel(icon);
            panel.add(label);
            panel.revalidate();
            panel.repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
