package org.example;

import javax.swing.*;

import java.awt.Color;

import com.formdev.flatlaf.*;

import org.example.content.MainFrame;
import org.example.content.ui.StudentLoginPanel;
import org.example.content.ui.WelcomePanel;

/**
 * @author ShiYuang
 * @date 2023/4/1 19:10
 */
public class App {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            UIManager.put("Label.foreground", Color.white);
            UIManager.put("Button.foreground", Color.white);
            ;
            UIManager.put("RadioButton.foreground", Color.white);
            UIManager.put("TextField.foreground", Color.white);
            UIManager.put("String.foreground", Color.white);
            UIManager.put("Panel.foreground", Color.white);
            UIManager.put("CheckBox.foreground", Color.white);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new MainFrame();
    }
}

