package org.example.content.ui;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

import javax.swing.border.Border;

import org.example.content.MainFrame;
import org.example.content.utils.Typings.Panels;

/**
 * @author ShiYuang
 * @date 2023/4/1 18:51
 */
public class StudentLoginPanel extends BasePanel implements ActionListener {
    private MainFrame mainFrame;
    private JPanel top, bot;
    private JPanel bnp;
    private JPanel jp2, jp3;
    private JButton jb3, jb_back;
    private JLabel title_zong;
    private JTextField jtf;
    private JPasswordField jtf2;
    private String backgroundImage = null;

    private JPanel P1, P2, P5, P6, P7, P8, P9, P10;
    private JLabel PL1, PL2, PL3, PL4, PLL;

    /**
     * This is a constructor for class StudentLoginPanel, which create the main interface.
     */
    public StudentLoginPanel(MainFrame mainFrame){
        super(mainFrame);
        this.mainFrame = mainFrame;

        title_zong = new JLabel("Input your studentAccount and password", JLabel.CENTER);
        title_zong.setFont(new Font("", Font.BOLD, 30));

        top = new JPanel();
        bot = new JPanel();
        jb_back = new JButton("Back");
        jb_back.addActionListener(this);
        jb_back.setActionCommand("back");
        top.add(jb_back);

        // jp_title = new JPanel();
        // jp_title.add(title_zong);
        // bnp=bookingnumPanel();

        Border lineBorder;
        lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);

        jb3 = new JButton("Check In!");
        jb3.addActionListener(this);
        jb3.setActionCommand("Check In!bn");
        jtf = new JTextField(15);
        jtf.setBorder(BorderFactory.createTitledBorder(lineBorder, "StudentAccount"));
        jtf2 = new JPasswordField(15);
        jtf2.setBorder(BorderFactory.createTitledBorder(lineBorder, "Password"));


        jtf.setBounds(410, 240, 190, 50);
        jtf2.setBounds(410, 370, 190, 50);
        jb3.setBounds(450, 500, 110, 40);

        title_zong.setBounds(70, 100, 900, 100);

        jb_back.setBounds(15, 25, 100, 20);
        bot.setBounds(0, 370, 1000, 60);

        this.setLayout(null);
        this.add(jb3);
        this.add(jtf);
        this.add(jtf2);
        this.add(title_zong);
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
    }

    /**
     * This method is used to create the panel for using studentAccount and password to
     * check in.
     */
    protected JPanel bookingnumPanel() {

        bnp = new JPanel();

        Border lineBorder;
        lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);

        jp2 = new JPanel();
        jp3 = new JPanel();
        jb3 = new JButton("Check In!");
        jb3.addActionListener(this);
        jb3.setActionCommand("Check In!bn");
        jtf = new JTextField(15);
        jtf.setBorder(BorderFactory.createTitledBorder(lineBorder, "booking number"));
        jp2.add(jtf);
        jp3.add(jb3);

        bnp.setLayout(new GridLayout(3, 1));
        bnp.add(jp2);
        bnp.add(jp3);
        // bnp.setBorder(BorderFactory.createTitledBorder(etchedBorder));
        // bnp.setBounds(100,200,800,400);
        return bnp;
    }


    public void onCalled() {
        System.out.println("来到信息填写页");
    }

    /**
     * This method is used to reponse to user's action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Check In!bn")) {

            String name = jtf.getText();
            String pwd = jtf2.getText();

            if (name.equals("")||pwd.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in the blank!",
                        "Missing information", JOptionPane.ERROR_MESSAGE);
            }

            else if(!mainFrame.getDataService().checkStudentPassword(name, pwd)){
                JOptionPane.showMessageDialog(null, "User name and password doesn't match!",
                        "Wrong information", JOptionPane.ERROR_MESSAGE);
            }
            else{
                //mainFrame.goPanel(Panels.STUDENT_LOGIN, Panels.HOME);
                this.setVisible(false);
                this.setEnabled(false);
                HomePanel homePanel = null;
                try {
                    homePanel = new HomePanel(mainFrame,name);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                mainFrame.add(homePanel);
                /**
 * background
 *
 * @author ShiYuang
 * @date 2023/4/14 18:28
 */
                backgroundImage ="test.jpg"; // 加载背景图片
                try {
                    homePanel.setBackgroundImage(backgroundImage);
                } catch (IOException a) {
                    a.printStackTrace();
                }
            }
        }

        if (e.getActionCommand().equals("back")) {
            mainFrame.goPanel(Panels.STUDENT_LOGIN, Panels. WELCOME);

            System.out.println("回");
        }
    }
}
