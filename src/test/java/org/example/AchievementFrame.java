package org.example;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*
 * @author：Qinding Zhang，Jieran Zhang
 * */
public class AchievementFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private Achievement achievement;

    private JPanel mainPanel;
    private JButton mathButton;
    private JButton englishButton;
    private JButton projectButton;
    private JButton scienceButton;
    private JButton editButton;

    private JTextArea mathTextArea;
    private JTextArea englishTextArea;
    private JTextArea projectTextArea;
    private JTextArea scienceTextArea;

    public AchievementFrame() {
        super("Achievement Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 初始化 Achievement 对象
        achievement = new Achievement();

        // 创建一个主面板，并设置布局为GridLayout，分成两行两列
        mainPanel = new JPanel(new GridLayout(2, 2, 50, 50));
        mainPanel.setPreferredSize(new Dimension(400, 300));

        // 创建四个按钮，并添加到主面板上
        mathButton = new JButton("Math");
        mathButton.addActionListener(this);
        englishButton = new JButton("English");
        englishButton.addActionListener(this);
        projectButton = new JButton("Project Experience");
        projectButton.addActionListener(this);
        scienceButton = new JButton("Science Research");
        scienceButton.addActionListener(this);
        mainPanel.add(mathButton);
        mainPanel.add(englishButton);
        mainPanel.add(projectButton);
        mainPanel.add(scienceButton);

        // 添加一个Edit按钮到右下角
        JPanel bottomPanel = new JPanel(new BorderLayout());
        editButton = new JButton("Edit");
        bottomPanel.add(editButton, BorderLayout.SOUTH);

        // 设置Edit按钮的监听器，用于生成小界面
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame dialogFrame = new JFrame("Edit Dialog");
                JPanel dialogPanel = new JPanel(new GridLayout(3, 1, 10, 10));
                dialogPanel.setPreferredSize(new Dimension(300, 200));

                // 创建下拉框和输入框，并添加到小界面上
                String[] options = { "Math", "English", "Project Experience", "Science Research" };
                JComboBox<String> comboBox = new JComboBox<String>(options);
                JTextArea textField = new JTextArea();
                dialogPanel.add(new JLabel("Select a category:"));
                dialogPanel.add(comboBox);
                dialogPanel.add(new JLabel("Enter your text:"));
                dialogPanel.add(textField);

                // 添加一个Check In按钮到小界面上
                JButton checkInButton = new JButton("Check In");
                checkInButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 将用户输入的内容添加到相应的页面中
                        String category = (String) comboBox.getSelectedItem();
                        String text = textField.getText();
                        if (category.equals("Math")) {
                            achievement.setMathContent(text);
                        } else if (category.equals("English")) {
                            achievement.setEnglishContent(text);
                        } else if (category.equals("Project Experience")) {
                            achievement.setProjectContent(text);
                        } else if (category.equals("Science Research")) {
                            achievement.setScienceContent(text);
                        }
                        // 关闭小界面
                        dialogFrame.dispose();
                    }
                });
                dialogPanel.add(checkInButton);

                // 将小界面添加到新的窗口中，并显示
                dialogFrame.add(dialogPanel);
                dialogFrame.pack();
                dialogFrame.setVisible(true);
            }
        });

        // 在每个类别页面上添加一个文本框用于显示用户添加的文本内容
        mathTextArea = new JTextArea();
        englishTextArea = new JTextArea();
        projectTextArea = new JTextArea();
        scienceTextArea = new JTextArea();

        // 将主面板和底部面板添加到主窗口中
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // 设置主窗口大小并显示
        setSize(600, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mathButton) {
            // 执行跳转到Math页面的操作，显示先前用户所写的对应类别的文本内容
            mathTextArea.setText(achievement.getMathContent());
            JFrame frame = new JFrame("Math Page");
            frame.setLayout(new FlowLayout());
            frame.add(mathTextArea);
            frame.pack();
            frame.setVisible(true);
        } else if (e.getSource() == englishButton) {
            // 执行跳转到English页面的操作，显示先前用户所写的对应类别的文本内容
            englishTextArea.setText(achievement.getEnglishContent());
            JFrame frame = new JFrame("English Page");
            frame.setLayout(new FlowLayout());
            frame.add(englishTextArea);
            frame.pack();
            frame.setVisible(true);
        } else if (e.getSource() == projectButton) {
            // 执行跳转到Project Experience页面的操作，显示先前用户所写的对应类别的文本内容
            projectTextArea.setText(achievement.getProjectContent());
            JFrame frame = new JFrame("Project Experience Page");
            frame.setLayout(new FlowLayout());
            frame.add(projectTextArea);
            frame.pack();
            frame.setVisible(true);
        } else if (e.getSource() == scienceButton) {
            // 执行跳转到Science Research页面的操作，显示先前用户所写的对应类别的文本内容
            scienceTextArea.setText(achievement.getScienceContent());
            JFrame frame = new JFrame("Science Research Page");
            frame.setLayout(new FlowLayout());
            frame.add(scienceTextArea);
            frame.pack();
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new AchievementFrame();
    }
}
