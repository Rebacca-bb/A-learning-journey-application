package org.example.content.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.formdev.flatlaf.FlatDarculaLaf;
import org.example.content.MainFrame;

import static java.lang.Math.max;


public class AchievementViewer extends JFrame{
    public AchievementViewer(JSONArray achievements,String username,String subcategory) throws IOException {
        setLayout(new GridLayout(3, 3));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        if(achievements.size()>0){
        for (int i = 0; i < max(achievements.size(),9); i++) {
            if(i<achievements.size()) {
                JSONObject achievement = achievements.getJSONObject(i);
                PanelWithPopupMenu panel = new PanelWithPopupMenu(achievement,username,subcategory);
                panel.setLayout(null);
                panel.setBorder(border);
                panel.setPreferredSize(new Dimension(200, 200)); // 设置面板大小为正方形

                add(panel);
            }
            else {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3, 1));
                panel.setBorder(border);
                panel.setPreferredSize(new Dimension(200, 200)); // 设置面板大小为正方形
                panel.add(new JLabel(" "));
                add(panel);
            }
        }
        }
        pack();
        setVisible(true);
    }
}