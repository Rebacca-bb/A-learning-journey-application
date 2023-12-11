package org.example.content.ui.detail_homepage;
import org.example.content.ui.AchievementFrame;
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
public class Skill extends JPanel implements ActionListener{
    private String username;
    Border borderline = BorderFactory.createLineBorder(Color.white);
    public Skill(String name){
        username = name;
        setLayout(null);
        setBounds(714,80,285,210);
        setVisible(true);
        Color bgcolor = new Color(1,44,89);
        setBackground(bgcolor);
        setOpaque(true);//不透明
        setBorder(borderline);

        JLabel Skills = new JLabel("Skills");
        Skills.setFont(new Font("Calibri",Font.BOLD,35));
        Skills.setBounds(10,10,180,50);
        this.add(Skills);

        // 调用DataService 类得到账户的skills的内容写入
        DataService myJSON = new DataService();
        String things = myJSON.GetAchievement(username);
//        System.out.println(things);

        // 将成绩信息显示在 panel 中
        String[] arrays =things.split(",", 5);
        int n=0;  //记录循环次数
        for(String a: arrays){
            if (a.length()!=0) {
                JLabel ability = new JLabel(a);
                ability.setFont(new Font("Calibri", Font.PLAIN, 15));
                ability.setBounds(10, 50 + n * 20, 260, 20);
//                ability.setBorder(borderline);
                this.add(ability);
                n += 1;
            }
        }

        JButton lookmore = new JButton("Look More!");
        Font font = lookmore.getFont();
        lookmore.setFont(new Font(font.getName(), font.getStyle(), 25));
        lookmore.setLayout(null);
        lookmore.setBounds(50,140,200,50);
        lookmore.setVisible(true);
        lookmore.addActionListener(this);
        lookmore.setActionCommand("look");
        this.add(lookmore);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new AchievementFrame(username);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
