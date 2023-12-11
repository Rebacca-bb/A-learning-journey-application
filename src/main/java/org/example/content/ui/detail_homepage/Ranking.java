package org.example.content.ui.detail_homepage;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import static org.example.content.utils.GradeAnalyzer.getRank;

/**
 * @author Sichen Liu, Xiangyi Zhou
 * @date 2023/4/3 0:18
 */

public class Ranking extends JPanel{
    Border borderline = BorderFactory.createLineBorder(Color.white);
    public Ranking(String username) throws IOException {
        setLayout(null);
        setBounds(10,80,300,170);
        setVisible(true);
        Color bgcolor = new Color(1,44,89);
        setBackground(bgcolor);
        setOpaque(true);//不透明
        setBorder(borderline);

        JProgressBar rank = new JProgressBar(SwingConstants.HORIZONTAL,0,100);
        int r = (int) getRank(username);
        rank.setValue(r);
        rank.setFont(new Font("Calibri",Font.BOLD,15));
        rank.setStringPainted(true);
        //rank.addChangeListener(this);
        rank.setPreferredSize(new Dimension(240,20));
        rank.setBorderPainted(true);
        rank.setBackground(Color.red);
        rank.setBounds(20,100,260,30);
        this.add(rank,BorderLayout.CENTER);

        JLabel Ranking_text = new JLabel("Ranking");
        Ranking_text.setFont(new Font("Calibri",Font.BOLD,35));
        Ranking_text.setBounds(10,10,180,50);
        this.add(Ranking_text);
    }
}
