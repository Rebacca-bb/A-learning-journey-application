package org.example.content.ui;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AchievementFrame extends JFrame {
    private static final String JSON_FILE = "./src/main/resources/data/Achieve.json";
    private JSONArray array;
    private static JSONObject achievements;
    private String username;
    public AchievementFrame(String username) throws IOException {
        FlatDarculaLaf.install();
        this.username = username;
        updateAchievements(username);
        loadAchievements(username);
        setTitle("Achievement App");
        setSize(600, 400);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2));
        // Add categories
        JButton mathButton = createCategoryButton("Math", "Math Modeling", "Math Competition");
        Font font = mathButton.getFont();
        mathButton.setFont(new Font(font.getName(), font.getStyle(), 25));
        JButton projectButton = createCategoryButton("Project Experience", "Hardware Design", "Software Production");
        font = projectButton.getFont();
        projectButton.setFont(new Font(font.getName(), font.getStyle(), 25));
        JButton englishButton = createCategoryButton("English Ability", "Standardized Language Score", "English Competition", "Study Abroad Experience");
        font = englishButton.getFont();
        englishButton.setFont(new Font(font.getName(), font.getStyle(), 25));
        JButton scienceButton = createCategoryButton("Scientific Research", "Computer", "Electronic", "Communication");
        font = scienceButton.getFont();
        scienceButton.setFont(new Font(font.getName(), font.getStyle(), 25));
        mainPanel.add(mathButton);
        mainPanel.add(projectButton);
        mainPanel.add(englishButton);
        mainPanel.add(scienceButton);
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            JFrame editFrame = new EditFrame();
            editFrame.setVisible(true);
        });
        add(mainPanel, BorderLayout.CENTER);
        add(editButton, BorderLayout.SOUTH);
        setVisible(true);
    }
    private JButton createCategoryButton(String text, String... subcategories) throws IOException {
        JButton button = new JButton(text);
        JPopupMenu menu = new JPopupMenu();

        String jsonString = JSON.toJSONString(achievements, SerializerFeature.PrettyFormat);
        System.out.println(jsonString);
        for (String subcategory : subcategories) {
            JMenuItem menuItem = new JMenuItem(subcategory);
            menuItem.addActionListener(e -> {
                if(achievements.getJSONArray(subcategory)==null)JOptionPane.showMessageDialog(null, "nothing here!");
                else {
                    try {
                        loadAchievements(username);
                        new AchievementViewer(achievements.getJSONArray(subcategory),username,subcategory);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            menu.add(menuItem);
        }

        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        return button;
    }

    private void loadAchievements(String username) throws IOException {
        String path = "./src/main/resources/data/Achieve.json";
        String jsonStr = new String(Files.readAllBytes(Paths.get(path)));
        array = JSON.parseArray(jsonStr);

        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("username").equals(username)) {
                achievements = obj;
                break;
            }
        }
    }

    private void saveAchievements() {
        try (FileWriter file = new FileWriter(JSON_FILE)) {
            String jsonString = JSON.toJSONString(array, SerializerFeature.PrettyFormat);
            file.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateAchievements(String username) throws IOException {
        // 读取 JSON 文件
        String path = "./src/main/resources/data/Achieve.json";
        String jsonStr = new String(Files.readAllBytes(Paths.get(path)));
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        // 查找是否已存在对应的用户数据
        JSONObject userObj = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if (obj.getString("username").equals(username)) {
                userObj = obj;
                break;
            }
        }
        // 如果不存在对应的用户数据，则新建一个对象并添加到 JSONArray 中
        if (userObj == null) {
            userObj = new JSONObject();
            userObj.put("username", username);
            jsonArray.add(userObj);
        }
        // 将更新后的 JSONArray 写入 JSON 文件
        Files.write(Paths.get(path), jsonArray.toJSONString(jsonArray, SerializerFeature.PrettyFormat).getBytes());
    }

    class EditFrame extends JFrame {
        private static final Dimension INPUT_SIZE = new Dimension(200, 24);

        public EditFrame() {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 200);
            setTitle("Edit Achievement");
            GridLayout gridLayout = new GridLayout(5, 1);
            setLayout(gridLayout);
            JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Math", "Project Experience", "English Ability", "Scientific Research"});
            JComboBox<String> subcategoryBox = new JComboBox<>();
            JTextField activityTypeField = new JTextField("input activityType");
            JTextField activityScoreField = new JTextField("input activityScore");
            JTextField activityDescriptionField = new JTextField("input activityDescription");

            updateSubcategoryBox(categoryBox.getSelectedItem().toString(), subcategoryBox);

            categoryBox.addActionListener(e -> {
                updateSubcategoryBox(categoryBox.getSelectedItem().toString(), subcategoryBox);
            });
            JButton checkButton = new JButton("Check");
            checkButton.addActionListener(e -> {
                String subcategory = subcategoryBox.getSelectedItem().toString();
                String activityType = activityTypeField.getText();
                String activityScore = activityScoreField.getText();
                String activityDescription = activityDescriptionField.getText();
                JSONObject activity = new JSONObject();
                activity.put("Type", activityType);
                activity.put("Score", activityScore);
                activity.put("Description", activityDescription);
                JSONArray jsonArray = achievements.getJSONArray(subcategory);
                if (jsonArray == null)
                    jsonArray = new JSONArray();
                jsonArray.add(activity);
                achievements.put(subcategory,jsonArray);
                for (int i = 0; i < array.size(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    String name = jsonObject.getString("username");
                    if (username.equals(username)) {
                        // 找到了目标 JSON 对象，更新其内容
                        array.set(i, achievements);
                        break;
                    }
                }
                saveAchievements();
                dispose();
            });

            activityTypeField.setPreferredSize(INPUT_SIZE);
            activityScoreField.setPreferredSize(INPUT_SIZE);
            activityDescriptionField.setPreferredSize(INPUT_SIZE);

            add(categoryBox);
            add(subcategoryBox);
            add(new JLabel());
            add(new JLabel());

            add(activityTypeField);
            addprompt(activityTypeField,"input activityType");
            add(new JLabel());
            add(activityScoreField);
            addprompt(activityScoreField,"input activityScore");
            add(new JLabel());
            add(activityDescriptionField);
            addprompt(activityDescriptionField,"input activityDescription");
            add(checkButton);
        }
        private void addprompt(JTextField textField,String text) {
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // 当 JTextField 获得焦点时，清空文本
                    if (textField.getText().equals(text)) {
                        textField.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // 当 JTextField 失去焦点时，如果文本为空，则设置提示文本
                    if (textField.getText().isEmpty()) {
                        textField.setText(text);
                    }
                }
            });
        }
        private void updateSubcategoryBox(String category, JComboBox<String> subcategoryBox) {
            subcategoryBox.removeAllItems();
            if (category.equals("Math")) {
                subcategoryBox.addItem("Math Modeling");
                subcategoryBox.addItem("Math Competition");
            } else if (category.equals("Project Experience")) {
                subcategoryBox.addItem("Hardware Design");
                subcategoryBox.addItem("Software Production");
            } else if (category.equals("English Ability")) {
                subcategoryBox.addItem("Standardized Language Score");
                subcategoryBox.addItem("English Competition");
                subcategoryBox.addItem("Study Abroad Experience");
            } else if (category.equals("Scientific Research")) {
                subcategoryBox.addItem("Computer");
                subcategoryBox.addItem("Electronic");
                subcategoryBox.addItem("Communication");
            }
        }
    }
}