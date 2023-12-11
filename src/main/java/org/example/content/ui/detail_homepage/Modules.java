package org.example.content.ui.detail_homepage;
import org.example.content.ui.CourseDetailPanel;
import org.example.content.ui.Gradepanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Sichen Liu, Xiangyi Zhou
 * @date 2023/4/3 0:18
 */
public class Modules extends JPanel implements ActionListener {
    Border borderline = BorderFactory.createLineBorder(Color.white);

    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(195, 40, 195, 170);
        g.drawLine(390, 40, 390, 170);
        g.drawLine(585, 40, 585, 170);
    }

    public Modules() {
        setLayout(null);
        setBounds(320, 480, 680, 170);
        setVisible(true);
        Color bgcolor = new Color(1, 44, 89);
        setBackground(bgcolor);
        setOpaque(true);// 不透明
        setBorder(borderline);

        JLabel Module_title1 = new JLabel("Basic Module");
        Module_title1.setFont(new Font("Calibri", Font.BOLD, 25));
        Module_title1.setBounds(10, 10, 180, 35);
        this.add(Module_title1);

        // 在Basic Module板块中添加展示课程信息的标签
        JLabel course1 = new JLabel("Calculus I");
        course1.setFont(new Font("Calibri", Font.PLAIN, 20));
        course1.setForeground(Color.white);
        course1.setBounds(15, 50, 150, 20);
        this.add(course1);

        JLabel course2 = new JLabel("Linear Algebra");
        course2.setFont(new Font("Calibri", Font.PLAIN, 20));
        course2.setForeground(Color.white);
        course2.setBounds(15, 85, 150, 20);
        this.add(course2);

        JLabel course3 = new JLabel("<html>Programming<br>Fundamentals<html>");
        course3.setFont(new Font("Calibri", Font.PLAIN, 20));
        course3.setForeground(Color.white);
        course3.setBounds(15, 110, 220, 45);
        this.add(course3);

        JLabel ignore = new JLabel("....");
        ignore.setFont(new Font("Calibri",Font.PLAIN,20));
        ignore.setForeground(Color.white);
        ignore.setBounds(15,170,100,20);
        this.add(ignore);

        JLabel Module_title2 = new JLabel("Major Module");
        Module_title2.setFont(new Font("Calibri", Font.BOLD, 25));
        Module_title2.setBounds(205, 10, 180, 35);
        this.add(Module_title2);

        // 在Major Module板块中添加展示课程信息的标签
        JLabel course4 = new JLabel("<html>Data Structures and<br> Algorithms<html>");
        course4.setFont(new Font("Calibri", Font.PLAIN, 20));
        course4.setForeground(Color.white);
        course4.setBounds(210, 40, 280, 45);
        this.add(course4);

        JLabel course5 = new JLabel("<html>Object-Oriented<br>Programming<html>");
        course5.setFont(new Font("Calibri", Font.PLAIN, 20));
        course5.setForeground(Color.white);
        course5.setBounds(210, 90, 260, 45);
        this.add(course5);

        JLabel course6 = new JLabel("Computer Networks");
        course6.setFont(new Font("Calibri", Font.PLAIN, 20));
        course6.setForeground(Color.white);
        course6.setBounds(210, 145, 200, 20);
        this.add(course6);



        JLabel Module_title3 = new JLabel("Optional Module");
        Module_title3.setFont(new Font("Calibri", Font.BOLD, 25));
        Module_title3.setBounds(400, 10, 180, 35);
        this.add(Module_title3);

        // 在Optional Module板块中添加展示课程信息的标签
        JLabel course7 = new JLabel("Artificial Intelligence");
        course7.setFont(new Font("Calibri", Font.PLAIN, 20));
        course7.setForeground(Color.white);
        course7.setBounds(405, 50, 180, 20);
        this.add(course7);

        JLabel course8 = new JLabel("Mobile Development");
        course8.setFont(new Font("Calibri", Font.PLAIN, 20));
        course8.setForeground(Color.white);
        course8.setBounds(405, 85, 220, 20);
        this.add(course8);

        JLabel course9 = new JLabel("Web Development");
        course9.setFont(new Font("Calibri", Font.PLAIN, 20));
        course9.setForeground(Color.white);
        course9.setBounds(405, 120, 170, 20);
        this.add(course9);

        JLabel ignore_2 = new JLabel("....");
        ignore_2.setFont(new Font("Calibri",Font.PLAIN,20));
        ignore_2.setForeground(Color.white);
        ignore_2.setBounds(405,155,100,20);
        this.add(ignore_2);

        JButton lookmore = new JButton("<html>Look<br>More!</html>");
        Font font = lookmore.getFont();
        lookmore.setFont(new Font(font.getName(), font.getStyle(), 25));
        lookmore.setLayout(null);
        lookmore.setBounds(590, 55, 85, 100);
        lookmore.setVisible(true);
        lookmore.addActionListener(this);
        lookmore.setActionCommand("look");
        this.add(lookmore);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("look")) {
//            CourseDetailPanel detailPanel = new CourseDetailPanel();
//            JFrame frame = new JFrame("Course Detail");
//            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            frame.add(detailPanel);
//            frame.pack();
//            frame.setLocationRelativeTo(this);
//            frame.setVisible(true);
            new CourseDetailPanel();
        }
    }
}


