package org.example.content.ui;

import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.Color;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class CourseDetailPanel extends JFrame {
    Border borderline = new LineBorder(Color.white, 2);
    private JPanel contPanel,panel1;

    public CourseDetailPanel() {
        FlatDarculaLaf.install();
        contPanel =new JPanel();
        contPanel.setLayout(null);
        this.setResizable(true);
        setLayout(null);
        setBounds(0,0,850,600);
        setVisible(true);
        setBackground(new Color(190, 207, 221));

        // 表格标题
        JLabel title = new JLabel("Course Information");
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        title.setBounds(260, 15, 250, 35);
        add(title);

        // 创建表格
        String[] columnNames = {"Category", "Course Name", "Credit", "Description"};
        Object[][] rowData = {
                {"Math Basic", "Calculus I", "4", "Topics include limits, continuity, differentiation, and integration of algebraic, exponential, and logarithmic functions."},
                {"Math Basic", "Linear Algebra", "3", "Topics include vectors, matrices, systems of linear equations, determinants, eigenvalues, and eigenvectors."},
                {"Math Basic", "Calculus II", "4", "Topics include techniques of integration, sequences and series, conic sections, parametric equations, and polar coordinates."},
                {"Profession Theoretical Basis", "Data Structures and Algorithms", "4", "Topics include the analysis of algorithms, elementary data structures, algorithm design, recursion, searching and sorting."},
                {"Profession Theoretical Basis", "Computer Networks", "4", "Topics include network architecture, protocol design, switching and routing, network security, and wireless networks."},
                {"Profession Theoretical Basis", "Object-Oriented Programming", "3", "Topics include classes, objects, inheritance, polymorphism, and graphical user interface programming."},
                {"Engineering Courses", "Mobile App Development", "3", "Topics include the principles and techniques of mobile application development, user interface design, data storage, and networking."},
                {"Engineering Courses", "Web Development", "3", "Topics include the basics of web development, HTML, CSS, JavaScript, and server-side programming with PHP."},
                {"Engineering Courses", "Artificial Intelligence", "3", "Topics include search algorithms, game-playing, reasoning, machine learning, and natural language processing."}
        };
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);
        table.setFont(new Font("Calibri", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(1, 44, 89));
        table.getTableHeader().setForeground(Color.white);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 60, 620, 350);
        add(scrollPane);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("More Information");
//        CourseDetailPanel courseDetailPanel=new CourseDetailPanel();
//        frame.add(courseDetailPanel);
//        frame.setSize(720, 200);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}
