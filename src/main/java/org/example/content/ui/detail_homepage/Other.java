package org.example.content.ui.detail_homepage;
import org.example.content.ui.ExcelImporter;
import org.example.content.ui.PhotoWall;
import org.example.content.ui.ResumePanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * @author Sichen Liu, Xiangyi Zhou
 * @date 2023/4/3 0:18
 */
public class Other extends JPanel{
    Border borderline = BorderFactory.createLineBorder(Color.white);
    public Other(String username){
        setLayout(null);
        setBounds(714,320,285,150);
        setVisible(true);
        Color bgcolor = new Color(1,44,89);
        setBackground(bgcolor);
        setOpaque(true);//不透明
        setBorder(borderline);
        JButton button = new JButton("Schedule");
        button.addActionListener(e -> {new ExcelImporter();
        });
        this.add(button);
        button.setBounds(5,80,90,40);
        JButton button2 = new JButton("resume");
        button2.addActionListener(e -> {
            JPanel panel = null;
            try {
                new ResumePanel(username);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        this.add(button2);
        button2.setBounds(100,80,90,40);
        JButton button3 = new JButton("Portfolio");
        button3.addActionListener(e -> {
            new PhotoWall(username);
        });
        this.add(button3);
        button3.setBounds(195,80,90,40);

        JLabel Other_text = new JLabel("Other information");
        Other_text.setFont(new Font("Calibri",Font.BOLD,35));
        Other_text.setBounds(10,10,280,50);
        this.add(Other_text);
    }
}