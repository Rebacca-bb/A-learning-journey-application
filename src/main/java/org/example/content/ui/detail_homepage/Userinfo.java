package org.example.content.ui.detail_homepage;
import org.example.content.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * @author Sichen Liu, Xiangyi Zhou
 * @date 2023/4/3 0:18
 */
public class Userinfo extends JPanel{
Border blackline = BorderFactory.createLineBorder(Color.black);

//
    public Userinfo(MainFrame frame,String username){
        setLayout(null);
        setBounds(320,10,384,460);
        setVisible(true);
        setOpaque(false);//透明
        class CircleBorder extends AbstractBorder {
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.GRAY);
            int diameter = Math.min(width, height);
            int x2 = x + width - diameter;
            int y2 = y + height - diameter;
            g2d.drawOval(x2, y2, diameter, diameter);
            g2d.dispose();
        }
    }
//        setBorder(blackline);

//        JLabel avatarLabel = new JLabel();
//        avatarLabel.setPreferredSize(new Dimension(200, 200));
//        avatarLabel.setHorizontalAlignment(JLabel.CENTER);
//        avatarLabel.setVerticalAlignment(JLabel.CENTER);
//        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
//        avatarLabel.setBounds(80,150,200,200);
        JLabel avatarLabel = new JLabel();
        avatarLabel.setPreferredSize(new Dimension(200, 200));
        avatarLabel.setBounds(80, 150, 200, 200);
        avatarLabel.setHorizontalAlignment(JLabel.CENTER);
        avatarLabel.setVerticalAlignment(JLabel.CENTER);
        avatarLabel.setBorder(new CircleBorder());
        this.add(avatarLabel);
        File file = new File("src/main/resources/avatars/" + username+".jpg");
        try {
            BufferedImage image = ImageIO.read(file);
            ImageIcon icon = new ImageIcon(getRoundImage(image, avatarLabel.getWidth(), avatarLabel.getHeight()));
            avatarLabel.setIcon(icon);
        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(frame, "Error loading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JButton chooseButton = new JButton("↑");
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        BufferedImage image = ImageIO.read(file);
                        ImageIcon icon = new ImageIcon(getRoundImage(image, avatarLabel.getWidth(), avatarLabel.getHeight()));
                        avatarLabel.setIcon(icon);
                        String fileName = file.getName();
                        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                        File outputFile = new File("src/main/resources/avatars/" + username+".jpg");
                        ImageIO.write(image, extension, outputFile);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error loading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        chooseButton.setBounds(280,330,30,30);
        this.add(chooseButton);
    }
    private static BufferedImage getRoundImage(BufferedImage image, int width, int height) {
        BufferedImage roundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = roundImage.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, height));
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
        return roundImage;
    }
//
//    public void paint(Graphics g){
//        super.paint(g);
//        g.drawOval(92,190,200,200);
//    }

}