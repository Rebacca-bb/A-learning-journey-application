package org.example.content.ui;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.example.content.utils.PDFConverter.panelToPDF;

public class ResumePanel extends JFrame {
    public static String formatScores(JSONArray scores) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        for (int i = 0; i < scores.size(); i++) {
            JSONObject score = scores.getJSONObject(i);
            String subject = score.getString("subject");
            int scoreValue = score.getIntValue("score");
            sb.append(subject).append(": ").append(scoreValue);
            if (i < scores.size() - 1) {
                sb.append("<br>");
            }
        }
        sb.append("</html>");
        return sb.toString();
    }

    public static String formatJsonObject(JSONObject jsonObject) {
        StringBuilder formattedString = new StringBuilder("<html>");

        for (String key : jsonObject.keySet()) {
            if (!key.equals("username")) {
                JSONArray innerArray = jsonObject.getJSONArray(key);
                for (Object innerObject : innerArray) {
                    if (innerObject instanceof JSONObject) {
                        JSONObject innerJsonObject = (JSONObject) innerObject;
                        String type = innerJsonObject.getString("Type");
                        String score = innerJsonObject.getString("Score");
                        String description = innerJsonObject.getString("Description");
                        formattedString.append(key).append(": ").append(type).append("  ").append(score).append("<br>");
                    }
                }
            }
        }

        formattedString.append("</html>");
        return formattedString.toString();
    }
    public ResumePanel(String username) throws IOException {
        setLayout(null);
        setBounds(50,50,600,900);
        FlatLightLaf.install();
        SwingUtilities.updateComponentTreeUI(this);

        JPanel panel = new JPanel();
        // 设置 JPanel 的大小为 600x800，使用 null 布局
        panel.setPreferredSize(new Dimension(600, 800));
        panel.setLayout(null);
        add(panel);
        panel.setBounds(10,10,600,800);
        panel.setBackground(Color.WHITE);
        JButton buttonp = new JButton("print");
        buttonp.setBounds(480,820,80,30);
        add(buttonp);
        buttonp.addActionListener(e -> {
            panelToPDF(panel);
        });
        setVisible(true);
        // 添加上半部分
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 600, 200);
        topPanel.setBorder(BorderFactory.createTitledBorder("personal information"));
        topPanel.setLayout(null);
        panel.add(topPanel);

        // 左侧添加图片
        try {
            BufferedImage image = ImageIO.read(new File("src/main/resources/avatars/" + username + ".jpg"));
            BufferedImage scaledImage = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(image, 0, 0, 150, 150, null);
            graphics2D.dispose();
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setBounds(10, 20, 150, 150);
            topPanel.add(imageLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 右侧添加学校和专业名称
        JLabel schoolLabel = new JLabel("<html>Beijing University of Posts and <br>Telecommunications<html>");
        schoolLabel.setBounds(200, 40, 380, 40);
        schoolLabel.setForeground(Color.BLACK);
        schoolLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        topPanel.add(schoolLabel);

        JLabel majorLabel = new JLabel("<html>Telecommunications Engineering and<br>Management<html>");
        majorLabel.setBounds(200, 100,380, 40);
        majorLabel.setForeground(Color.BLACK);
        majorLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        topPanel.add(majorLabel);

        // 添加中间部分
        JPanel middlePanel = new JPanel();
        middlePanel.setBounds(0, 200, 600, 300);
        middlePanel.setBorder(BorderFactory.createTitledBorder("Transcript"));
        middlePanel.setLayout(null);
        panel.add(middlePanel);
        JSONArray gradeArray = null;
        double g=0;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/Grade.json")));
            JSONObject jsonObject = JSON.parseObject(jsonString);
            gradeArray = jsonObject.getJSONObject(username).getJSONArray("scores");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 添加成绩单内容
        String string = formatScores(gradeArray);
        JLabel gradeLabel = new JLabel(string);
        gradeLabel.setForeground(Color.BLACK);
        gradeLabel.setBounds(10, 20, 200, 250);
        middlePanel.add(gradeLabel);

        // 添加下半部分
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 500, 600, 300);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Personal experience"));
        bottomPanel.setLayout(null);
        panel.add(bottomPanel);

        // 添加获奖经历内容
        String path = "./src/main/resources/data/Achieve.json";
        String jsonStr = new String(Files.readAllBytes(Paths.get(path)));
        JSONArray array = new JSONArray();
        array = JSON.parseArray(jsonStr);
        JSONObject achievements= new JSONObject();
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("username").equals(username)) {
                achievements = obj;
                break;
            }
        }
        JLabel awardLabel = new JLabel(formatJsonObject(achievements));
        awardLabel.setForeground(Color.BLACK);
        awardLabel.setBounds(10, 20, 200, 250);
        bottomPanel.add(awardLabel);
    }
}
