package org.example.content.ui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PanelWithPopupMenu extends JPanel {
    private String Type;
    private String Score;
    private String Description;
    private JSONObject achievements;
    private void loadAchievements(String username) throws IOException {
        String path = "./src/main/resources/data/Achieve.json";
        String jsonStr = new String(Files.readAllBytes(Paths.get(path)));
        JSONArray array = JSON.parseArray(jsonStr);

        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("username").equals(username)) {
                achievements = obj;
                break;
            }
        }
    }
    private void update(String username,JSONObject NewObj) throws IOException {
        String path = "./src/main/resources/data/Achieve.json";
        String jsonStr = new String(Files.readAllBytes(Paths.get(path)));
        JSONArray array = JSON.parseArray(jsonStr);
        System.out.println(username);
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("username").equals(username)) {
                array.remove(i);
                array.add(NewObj);
            }
        }
        try (FileWriter file = new FileWriter("./src/main/resources/data/Achieve.json")) {
            String jsonString = JSON.toJSONString(array, SerializerFeature.PrettyFormat);
            file.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PanelWithPopupMenu(JSONObject achievement,String username,String subcategory) throws IOException {
        // 创建一个右键菜单
        loadAchievements(username);
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editMenuItem = new JMenuItem("Edit");
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);
        Type = achievement.getString("Type");
        Score = achievement.getString("Score");
        Description = achievement.getString("Description");
        JLabel TypeLabel = new JLabel("    Type: " + achievement.getString("Type"));
        add(TypeLabel);
        TypeLabel.setBounds(5,5,190,30);

        JLabel ScoreLabel = new JLabel("    Score: " + achievement.getString("Score"));
        add(ScoreLabel);
        ScoreLabel.setBounds(5,40,190,30);

        JTextArea descriptionArea = new JTextArea(achievement.getString("Description"));
        Color backgroundColor = this.getBackground(); // 获取容器的背景颜色
        descriptionArea.setBackground(backgroundColor);
        descriptionArea.setBounds(10,80,190,120);
        descriptionArea.setFocusable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        add(descriptionArea);
        // 添加鼠标右键单击事件
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        editMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 创建一个带有三个输入框和确认按钮的对话框
                JPanel panel = new JPanel(new GridLayout(0, 2));
                panel.add(new JLabel("Type:"));
                JTextField typeField = new JTextField(Type,10);
                panel.add(typeField);
                panel.add(new JLabel("Score:"));
                JTextField scoreField = new JTextField(Score,10);
                panel.add(scoreField);
                panel.add(new JLabel("Description:"));
                JTextField descriptionField = new JTextField(Description,10);
                panel.add(descriptionField);
                int result = JOptionPane.showConfirmDialog(
                        PanelWithPopupMenu.this,
                        panel,
                        "编辑",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    JSONArray jsonArray = achievements.getJSONArray(subcategory);
                    for(int i =0;i<jsonArray.size();i++){
                        JSONObject o = jsonArray.getJSONObject(i);
                        System.out.println(o.getString("Type"));
                        System.out.println(Type);
                        if(o.getString("Type").equals(Type)&&o.getString("Score").equals(Score)){
                            jsonArray.remove(i);
                            System.out.println("update  array!");
                            o.put("Type",typeField.getText());
                            o.put("Score",scoreField.getText());
                            o.put("Description",descriptionField.getText());
                            jsonArray.add(o);
                        }
                    }
                    // 将输入框的内容保存到变量中
                    Type = typeField.getText();
                    Score = scoreField.getText();
                    Description = descriptionField.getText();
                    achievements.put(subcategory,jsonArray);
                    try {
                        update(username,achievements);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                TypeLabel.setText("    Type: "+Type);
                ScoreLabel.setText("    Score: "+Score);
                descriptionArea.setText(Description);

            }
        });
        deleteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JSONArray jsonArray = achievements.getJSONArray(subcategory);
                for(int i =0;i<jsonArray.size();i++){
                    JSONObject o = jsonArray.getJSONObject(i);
                    System.out.println(o.getString("Type"));
                    System.out.println(Type);
                    if(o.getString("Type").equals(Type)&&o.getString("Score").equals(Score)){
                        jsonArray.remove(i);
                    }
                }
                achievements.put(subcategory,jsonArray);
                try {
                    update(username,achievements);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                TypeLabel.setText("");
                ScoreLabel.setText("");
                descriptionArea.setText("");
            }
        });
    }
}