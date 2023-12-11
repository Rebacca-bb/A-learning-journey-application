package org.example.content.ui.detail_homepage;

/*
 * @author：Shengyao Gao
 * */

import java.awt.*;
import javax.swing.JPanel;

public class ProgressCircle extends JPanel {
    private double progress;
    private String label;

    public ProgressCircle(double progress, String label) {
        this.progress = progress;
        this.label = label;
    }

    public Dimension getPreferredSize() {
        return new Dimension(150, 150);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height);
        int x = (width - diameter) / 2;
        int y = (height - diameter) / 2;



        g2d.setPaint(Color.GREEN);
        g2d.setStroke(new BasicStroke(20));
        g2d.drawArc(x+9, y+9, diameter-18, diameter-18, 90, (int) (-progress * 360));

        g2d.setPaint(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 24);
        g2d.setFont(font);
        g2d.setPaint(Color.white);
        g2d.drawString(label, x + diameter / 2 - 40, y + diameter / 2);
    }
}
