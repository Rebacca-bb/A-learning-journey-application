package org.example.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import com.alibaba.fastjson.*;
import org.example.content.ui.BasePanel;
import org.example.content.ui.StudentLoginPanel;
import org.example.content.ui.WelcomePanel;
import org.example.content.ui.HomePanel;
import org.example.content.utils.DataService;
import org.example.content.utils.Resources;
import org.example.content.utils.Typings;
import org.example.content.utils.Typings.Panels;
/**
 * @author ShiYuang
 * @date 2023/4/1 18:19
 */
public class MainFrame extends JFrame implements ActionListener {

    private MainFrame mainFrame;
    private BasePanel panels[] = new BasePanel[Typings.Panels.values().length];
    private String operatingBookingNo;
    private DataService dataService = new DataService();

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public BasePanel[] getPanels() {
        return panels;
    }

    public void setPanels(BasePanel[] panels) {
        this.panels = panels;
    }

    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public void goPanel(Panels nowPanel, Panels targetPanel) {
        goPanel(nowPanel, targetPanel, new JSONObject());
    }
    public void goPanel(Panels nowPanel, Panels targetPanel, JSONObject param) {

        // set now panel invisible

        panels[nowPanel.ordinal()].setEnabled(false);
        panels[nowPanel.ordinal()].setVisible(false);

        // c.all onCall w..ith or without param

        if (param.size() < 1) {

            panels[targetPanel.ordinal()].onCalled();

        } else {
            panels[targetPanel.ordinal()].onCalled(param);

        }
        // set target visible
        panels[targetPanel.ordinal()].setEnabled(true);
        panels[targetPanel.ordinal()].setVisible(true);

        this.repaint();
    }
/**
 * config panel backgroud
 *
 * @author ShiYuang
 * @date 2023/4/1 18:22
 */
    public static class JPanelWithBackground extends JComponent {

        private Image backgroundImage;

        // Some code to initialize the background image.
        // Here, we use the constructor to load the image. This
        // can vary depending on the use case of the panel.
        public JPanelWithBackground(String fileName) throws IOException {
            backgroundImage = ImageIO.read(Resources.getImgByName(fileName));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the background image.
            g.drawImage(backgroundImage.getScaledInstance(1024, 768, Image.SCALE_SMOOTH), 0, 0, this);
        }
    }
/**
 * construct mainFrame
 * @author ShiYuang
 * @date 2023/4/1 18:24
 */
    public MainFrame() throws IOException {
        mainFrame = this;

        try {
            this.setContentPane(new JPanelWithBackground("test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panels[Panels.WELCOME.ordinal()] = new WelcomePanel(mainFrame);
        panels[Panels.STUDENT_LOGIN.ordinal()] = new StudentLoginPanel(mainFrame);
        panels[Panels.HOME.ordinal()] = new HomePanel(mainFrame,"2020213319");
        for (BasePanel p : panels) {
            if (p == null) {
                continue;
            }
            p.setEnabled(false);
            p.setBounds(0, 0, 1024, 768);
            p.setVisible(false);
            p.setOpaque(false);
            this.getContentPane().add(p);
        }
        panels[0].setEnabled(true);
        panels[0].setVisible(true);
        this.setTitle("G1's study journey");
        this.setSize(1024, 768);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {

    }
}
