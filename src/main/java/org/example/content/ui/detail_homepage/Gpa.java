package org.example.content.ui.detail_homepage;
import org.example.content.ui.Gradepanel;
import org.example.content.utils.DataService;

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
public class Gpa extends JPanel implements ActionListener{
    Border borderline = BorderFactory.createLineBorder(Color.white);
    String username;
    public Gpa(String username){
        this.username = username;
        setLayout(null);
        setBounds(10,270,300,380);
        setVisible(true);
        Color bgcolor = new Color(1,44,89);
        setBackground(bgcolor);
        setOpaque(true);   //不透明
        setBorder(borderline);

        JLabel GPA_title = new JLabel("GPA");
        GPA_title.setFont(new Font("Calibri",Font.BOLD,35));
        GPA_title.setBounds(10,10,250,50);
        this.add(GPA_title);

        DataService dataService = new DataService();
        double d = dataService.GetGrades(username);
        ProgressCircle progressCircle = new ProgressCircle((double) d/100.0, String.format("%.2f",d/ 100.0 * 4 ) +"/4.0");
        progressCircle.setBounds(50,90,200,180);
        progressCircle.setBackground(bgcolor);
        this.add(progressCircle);

        JButton lookmore = new JButton("Look More!");
        Font font = lookmore.getFont();
        lookmore.setFont(new Font(font.getName(), font.getStyle(), 16));
        lookmore.setLayout(null);
        lookmore.setBounds(50,320,200,50);
        lookmore.setVisible(true);
        lookmore.addActionListener(this);
        lookmore.setActionCommand("look");
        this.add(lookmore);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("look")){
            new Gradepanel(this.username);
        }
    }
}
