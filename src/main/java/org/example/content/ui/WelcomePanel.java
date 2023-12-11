package org.example.content.ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.example.content.MainFrame;
import org.example.content.utils.DataService;
import org.example.content.utils.Typings.Panels;

/**
 * @author ShiYuang
 * @date 2023/4/1 18:34
 */
public class WelcomePanel extends BasePanel implements ActionListener{

    private MainFrame mainFrame;
    private JPanel jp1, jp2, P1, P2;
    private JButton jb1, jb2;
    private JLabel title;

    public WelcomePanel(MainFrame mainFrame) {
        super(mainFrame);
        this.mainFrame = mainFrame;

        title = new JLabel("Welcome to your study journey!", JLabel.CENTER);
        title.setFont(new Font("", Font.BOLD, 50));
        // Border etchedBorder;
        // etchedBorder = BorderFactory.createEtchedBorder();
        jp1 = new JPanel();
        jp1.setOpaque(false);
        jp2 = new JPanel();
        jp2.setOpaque(false);
        jb1 = new JButton("Start your journey");
        jb1.setFont(new Font("", Font.BOLD, 25));
        jb1.setForeground(new Color(255, 100, 100, 255));
        jb1.addActionListener(this);
        jb1.setActionCommand("start your journey");
        jb1.setOpaque(false);
        jb1.setBackground(new Color(0, 0, 0, 150));
        jb2 = new JButton("Student sign up");
        jb2.setBackground(new Color(0, 0, 0, 150));

        jb2.addActionListener(this);
        jb2.setActionCommand("Student sign up");

        jp1.add(title, BorderLayout.CENTER);
        jp2.add(jb1);
        jp1.setBounds(0, 80, 1024, 200);
        jb1.setBounds(350, 400, 290, 50);
        jb2.setBounds(820, 20, 150, 40);
//         jp1.setBorder(BorderFactory.createTitledBorder(etchedBorder));
//         jb1.setBorder(BorderFactory.createTitledBorder(etchedBorder));
//         this.setSize(1024,768);
        this.setLayout(null);
        this.add(jp1);
        this.add(jb1);
        this.add(jb2);
        P1 = new JPanel();
        P1.setBounds(0, 70, 10000, 5);
        P1.setBackground(new Color(100, 100, 200));
        this.add(P1);

        P2 = new JPanel();
        P2.setBounds(0, 655, 10000, 5);
        P2.setBackground(new Color(100, 100, 200));
        this.add(P2);
    }
    public void onCalled() {
        System.out.println("来到了初始界面");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start your journey")) {
            // System.out.print("lallala");
            mainFrame.goPanel(Panels.WELCOME, Panels.STUDENT_LOGIN);
        } else if (e.getActionCommand().equals("Student sign up")) {
            JPanel panel = new JPanel(new GridLayout(2,2));
            JLabel SubLabel = new JLabel("username:");
            JLabel ScrLabel = new JLabel("password:");
            JTextField SubField = new JTextField();
            JTextField ScrField = new JTextField();
            panel.add(SubLabel);
            panel.add(SubField);
            panel.add(ScrLabel);
            panel.add(ScrField);
            boolean flag=false;
            while (!flag) {
                int dialog = JOptionPane.showConfirmDialog(null, panel, "input information", JOptionPane.OK_CANCEL_OPTION);
                if (dialog == JOptionPane.OK_OPTION) {
                    String username = SubField.getText();
                    String password = ScrField.getText();
                    if (username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(panel, "不能为空！！");
                    }
                    else {
                        DataService dataService = new DataService();
                        dataService.adddata(username,password);
                        flag=true;
                    }
                }
                else flag=true;
            }
        }
    }
}
