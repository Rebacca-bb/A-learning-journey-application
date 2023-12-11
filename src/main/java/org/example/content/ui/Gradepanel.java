package org.example.content.ui;
/*
 * @author：Shengyao Gao
 * */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.formdev.flatlaf.FlatDarculaLaf;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Gradepanel extends JFrame implements ActionListener {
    private JPanel contPanel,panel1,panel2;
    private String username;
    private JTabbedPane tabbedPane;
    private JTable table;
    private JButton AddButton,SetButton,DelButton;
    private JScrollPane scrollPane;
    private JLabel label2;
    private DefaultCategoryDataset dataset;
    public double GetGrades(){
        JSONArray gradeArray = null;
        double g=0;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/Grade.json")));
            JSONObject jsonObject = JSON.parseObject(jsonString);
            gradeArray = jsonObject.getJSONObject(username).getJSONArray("scores");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] columnName = {"subject","grade"};
        DefaultTableModel tableModel = new DefaultTableModel(columnName,0);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        for(int i = 0;i<gradeArray.size();i++){
            JSONObject grade = gradeArray.getJSONObject(i);
            String subject = grade.getString("subject");
            double score = grade.getDouble("score");
            g+=score;
            tableModel.addRow(new Object[]{subject,score});
        }
        table.setModel(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,600,400);
        panel1.add(scrollPane);
        double v = g / (double) gradeArray.size();
        return v;
    }
    public void update(){
        JSONArray gradeArray = null;
        double g=0;
        panel1.remove(scrollPane);
        panel1.remove(label2);
        SetButton.setEnabled(false);
        DelButton.setEnabled(false);
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/Grade.json")));
            JSONObject jsonObject = JSON.parseObject(jsonString);
            gradeArray = jsonObject.getJSONObject(username).getJSONArray("scores");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] columnName = {"subject","grade"};
        DefaultTableModel tableModel = new DefaultTableModel(columnName,0);
        table = new JTable(tableModel);
        for(int i = 0;i<gradeArray.size();i++){
            JSONObject grade = gradeArray.getJSONObject(i);
            String subject = grade.getString("subject");
            double score = grade.getDouble("score");
            g+=score;
            tableModel.addRow(new Object[]{subject,score});
        }
        table.setModel(tableModel);
        table.setDefaultEditor(Object.class, null);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,600,400);
        panel1.add(scrollPane);
        double v = g / (double) gradeArray.size();
        label2 = new JLabel("Average: "+v);
        label2.setBounds(620,250,100,40);
        panel1.add(label2);
        ListSelectionListener selectionListener = e -> {
            int r = table.getSelectedRow();
            if(r != -1){
                SetButton.setEnabled(true);
                DelButton.setEnabled(true);
            }
        };
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(selectionListener);
        panel1.revalidate();
        panel1.repaint();

    }
    public void addGrades(String sub,double scr){
        JSONArray gradeArray = null;
        JSONObject jsonObject = null;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/Grade.json")));
            jsonObject = JSON.parseObject(jsonString);
            gradeArray = jsonObject.getJSONObject(username).getJSONArray("scores");
        } catch (IOException e) {
            e.printStackTrace();
        }
        gradeArray.add(new JSONObject().fluentPut("subject",sub).fluentPut("score",scr));
        try {
            FileWriter writer = new FileWriter("./src/main/resources/data/Grade.json");
            writer.write(jsonObject.toJSONString(jsonObject,SerializerFeature.PrettyFormat));
            System.out.println(jsonObject.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        update();
    }
    public void delGrades(String sub){
        JSONArray gradeArray = null;
        JSONObject jsonObject = null;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/Grade.json")));
            jsonObject = JSON.parseObject(jsonString);
            gradeArray = jsonObject.getJSONObject(username).getJSONArray("scores");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0;i<gradeArray.size();i++){
            JSONObject a = gradeArray.getJSONObject(i);
            if(a.getString("subject").equals(sub)){
                gradeArray.remove(i);
                break;
            }
        }
        try {
            FileWriter writer = new FileWriter("./src/main/resources/data/Grade.json");
            writer.write(jsonObject.toJSONString(jsonObject,SerializerFeature.PrettyFormat));
            System.out.println(jsonObject.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        update();
    }
    public void editGrades(String sub,double num){
        JSONArray gradeArray = null;
        JSONObject jsonObject = null;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/Grade.json")));
            jsonObject = JSON.parseObject(jsonString);
            gradeArray = jsonObject.getJSONObject(username).getJSONArray("scores");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0;i<gradeArray.size();i++){
            JSONObject a = gradeArray.getJSONObject(i);
            if(a.getString("subject").equals(sub)){
                a.put("score",num);
                System.out.println(sub);
                gradeArray.set(i,a);
                break;
            }
        }
        try {
            FileWriter writer = new FileWriter("./src/main/resources/data/Grade.json");
            writer.write(jsonObject.toJSONString(jsonObject,SerializerFeature.PrettyFormat));
            System.out.println(jsonObject.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        update();
    }
    public static boolean isNum(String str){
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public Gradepanel(String username){
        FlatDarculaLaf.install();
        this.username = username;
        setBounds(0,0,850,600);
        contPanel =new JPanel();
        contPanel.setLayout(null);
        setVisible(true);
        this.setResizable(false);

        JLabel label1 = new JLabel("Welcome, "+this.username+"!");
        label1.setBounds(5,0,200,10);
        contPanel.add(label1);

        tabbedPane = new JTabbedPane();
        panel1 = new JPanel();
        panel1.setLayout(null);
        tabbedPane.addTab("Grade",panel1);

        double a = GetGrades();

        label2 = new JLabel("Average: "+a);
        label2.setBounds(620,250,100,40);
        panel1.add(label2);

        SetButton = new JButton("Edit");
        SetButton.addActionListener(this);
        SetButton.setActionCommand("Set");
        SetButton.setBounds(620,300,100,40);
        panel1.add(SetButton);
        SetButton.setEnabled(false);

        DelButton = new JButton("Delete");
        DelButton.addActionListener(this);
        DelButton.setActionCommand("Delete");
        DelButton.setBounds(620,350,100,40);
        panel1.add(DelButton);
        DelButton.setEnabled(false);

        AddButton = new JButton("add Grades");
        AddButton.addActionListener(this);
        AddButton.setActionCommand("Add");
        AddButton.setBounds(620,400,100,40);
        panel1.add(AddButton);

        JButton ExButton = new JButton("Export");
        ExButton.addActionListener(this);
        ExButton.setActionCommand("export");
        ExButton.setBounds(620,20,100,40);
        panel1.add(ExButton);

        ListSelectionListener selectionListener = e -> {
            int r = table.getSelectedRow();
            if(r != -1){
                SetButton.setEnabled(true);
                DelButton.setEnabled(true);
            }
        };
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(selectionListener);
        panel2 = new BarChart(table);
        tabbedPane.addTab("information",panel2);
        update();
        tabbedPane.setBounds(40,40,750,650);
        contPanel.add(tabbedPane);
        setContentPane(contPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")){
            JPanel panel = new JPanel(new GridLayout(2,2));
            JLabel SubLabel = new JLabel("Subject:");
            JLabel ScrLabel = new JLabel("Grade:");
            JTextField SubField = new JTextField();
            JTextField ScrField = new JTextField();
            panel.add(SubLabel);
            panel.add(SubField);
            panel.add(ScrLabel);
            panel.add(ScrField);
            boolean flag=false;
            while (!flag) {
                int dialog = JOptionPane.showConfirmDialog(null, panel, "input Grades", JOptionPane.OK_CANCEL_OPTION);
                if (dialog == JOptionPane.OK_OPTION) {
                    String sub = SubField.getText();
                    String scoreStr = ScrField.getText();
                    if (sub.isEmpty() || scoreStr.isEmpty()) {
                        JOptionPane.showMessageDialog(panel, "不能为空！！");
                    }
                    else {
                        double score=0;
                        if(isNum(scoreStr))
                            score =Double.parseDouble(scoreStr);
                        addGrades(sub,score);
                        flag=true;
                    }
                }
                else flag=true;
            }
        }
        if(e.getActionCommand().equals("Delete")){
            int r = table.getSelectedRow();
            String data = (String) table.getValueAt(r,0);
            System.out.println(data);
            delGrades(data);
        }
        if(e.getActionCommand().equals("Set")){
            int r = table.getSelectedRow();
            String data = (String) table.getValueAt(r,0);
            boolean flag=false;
            while (!flag) {
                String str = JOptionPane.showInputDialog(null,"input new grade","input dialog",JOptionPane.PLAIN_MESSAGE);
                if (str.isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "不能为空！！");
                }
                else {
                    double score=0;
                    if(isNum(str)) {
                        score = Double.parseDouble(str);
                        editGrades(data, score);
                    }
                    flag=true;
                }

            }
        }
        if(e.getActionCommand().equals("export")){
            String desktopDir = System.getProperty("user.home") + "/Desktop";
            ExcelExporter.export(table, desktopDir + "/"+username+".xlsx");
            JOptionPane.showMessageDialog(null, "Transcript has been successfully exported to desktop.");
        }
    }

}
