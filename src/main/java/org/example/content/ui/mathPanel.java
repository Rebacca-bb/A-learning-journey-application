package org.example.content.ui;

import javax.swing.*;

public class mathPanel extends JPanel{
    public mathPanel(AchievementFrame frame,String username){
        JButton button = new JButton();
        this.add(button);
        button.addActionListener(e -> {
            this.setVisible(false);
            frame.remove(this);
        });
    }

}