package org.example.content.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

import org.example.content.MainFrame;
import org.example.content.ui.detail_homepage.Gpa;
import org.example.content.ui.detail_homepage.Modules;
import org.example.content.ui.detail_homepage.Ranking;
import org.example.content.ui.detail_homepage.Other;
import org.example.content.ui.detail_homepage.Skill;
import org.example.content.ui.detail_homepage.Userinfo;

import org.example.content.MainFrame;
import org.example.content.utils.Resources;
import org.example.content.utils.Typings;

/**
 * @author Sichen Liu, Xiangyi Zhou
 * @date 2023/4/3 0:18
 */

public class HomePanel extends BasePanel implements ActionListener{
    private MainFrame mainFrame;
    private JPanel top, bot;
    private JButton  jb_back;
    private JPanel P1, P2, P5, P6, P7, P8, P9, P10;
    private Image backgroundImage = null;

//     画圆 不需要定义类 直接这个函数画
//    public void paint(Graphics g){
//        super.paint(g);
//        g.drawOval(412,200,200,200);
//    }

    public HomePanel(MainFrame mainFrame,String username) throws IOException {
        super(mainFrame);
        this.mainFrame = mainFrame;

        top = new JPanel();
        bot = new JPanel();
        jb_back = new JButton("Back");
        jb_back.addActionListener(this);
        jb_back.setActionCommand("back");
        top.add(jb_back);

        Border lineBorder;
        lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);

        // 设置该页面的布局与大小
        setLayout(null);
        setSize(1024,768);
        setOpaque(true);
        setVisible(true);
        setEnabled(true);
        //setTitle("Homepage");

        jb_back.setBounds(15, 25, 100, 20);
        bot.setBounds(0, 370, 1000, 60);

        this.setLayout(null);
        this.add(jb_back);

        // this.add(bot);

        // 加线
        P1 = new JPanel();
        P1.setBounds(0, 70, 10000, 5);
        P1.setBackground(new Color(100, 100, 200));
        this.add(P1);

        P2 = new JPanel();
        P2.setBounds(0, 655, 10000, 5);
        P2.setBackground(new Color(100, 100, 200));
        this.add(P2);
        // ----------------------------------------------------------------------------------------------
//        P9 = new JPanel();
//        P9.setBounds(203, 716, 30, 4);
//        P9.setBackground(Color.pink);
//
//        this.add(P9);

        JLabel title = new JLabel("Welcome!");
        title.setFont(new Font("Calibri",Font.BOLD,40));
        title.setBounds(412,50,250,100);


        Ranking ranking = new Ranking(username);
        Gpa gpa = new Gpa(username);
        Skill skill = new Skill(username);
        Modules module = new Modules();
        Other other = new Other(username);
        Userinfo userinfo = new Userinfo(mainFrame,username);

        add(title);
        add(ranking);
        add(gpa);
        add(skill);
        add(module);
        add(other);
        add(userinfo);

//        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }
    /**
     * set background
     *
     * @author ShiYuang
     * @date 2023/4/14 20:38
     */
    public void setBackgroundImage(String fileName) throws IOException{
        backgroundImage = ImageIO.read(Resources.getImgByName(fileName));
        this.backgroundImage = backgroundImage;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        // 在这里添加其他的绘制逻辑，例如添加文本、图标等
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            this.setVisible(false);
            this.setEnabled(false);
            mainFrame.goPanel(Typings.Panels.HOME, Typings.Panels. WELCOME);
            System.out.println("回");
        }
    }

}


