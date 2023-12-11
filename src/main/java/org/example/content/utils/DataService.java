package org.example.content.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.example.content.entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author ShiYuang
 * @date 2023/3/31 22:38
 * @version 1.0
 */
public class DataService {
    private List<Student> allStudent;

    public DataService() {
        Resources.extractData();
        refreshData();
    }

    public List<Student> getAllStudent() {
        return allStudent;
    }

    public void setAllStudent(List<Student> allStudent) {
        this.allStudent = allStudent;
    }
    /**
     * check student account and password match
     *
     * @author ShiYuang
     * @date 2023/4/1 9:09
     */
    public boolean checkStudentPassword(String studentAccount, String password) {
        for (Student student : this.allStudent) {
            if (student.getStudentAccount().equals(studentAccount)) {
                if (student.getPassword().equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void refreshData() {
        this.allStudent = JSON.parseArray(Resources.readDataToString("student.json"), Student.class);
    }
    /**
     * get student grade and add new students
     *
     * @author GaoShengyao
     * @date 2023/4/9 9:09
     */
    public double GetGrades(String username){
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
        for(int i = 0;i<gradeArray.size();i++){
            JSONObject grade = gradeArray.getJSONObject(i);
            String subject = grade.getString("subject");
            double score = grade.getDouble("score");
            g+=score;
            tableModel.addRow(new Object[]{subject,score});
        }
        double v = g / (double) gradeArray.size();
        return v;
    }

    public static void adddata(String username,String password){
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/student.json")));
            jsonArray = JSON.parseArray(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonArray.add(new JSONObject().fluentPut("studentAccount",username).fluentPut("password",password));
        try {
            FileWriter writer = new FileWriter("./src/main/resources/data/student.json");
            writer.write(jsonArray.toJSONString(jsonArray, SerializerFeature.PrettyFormat));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/Grade.json")));
            jsonObject = JSON.parseObject(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject stu = new JSONObject();
        stu.put("scores",new JSONArray());
        jsonObject.put(username,stu);
        try {
            FileWriter writer = new FileWriter("./src/main/resources/data/Grade.json");
            writer.write(JSONObject.toJSONString(jsonObject, SerializerFeature.PrettyFormat));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String GetSkills(String username){
        JSONObject skillObject = null;
        double g=0;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/Achieve.json")));
            JSONObject jsonObject = JSON.parseObject(jsonString);
            skillObject = jsonObject.getJSONObject(username).getJSONObject("skills");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String things = "";
        // 获取数学能力
        JSONArray mathArray = skillObject.getJSONArray("math");
        for(int i = 0;i<mathArray.size();i++){
            String thing = mathArray.getString(i);
            things = things +"," + thing;
        }
        JSONArray EnglishArray = skillObject.getJSONArray("English");
        for(int i = 0;i<EnglishArray.size();i++){
            String thing = EnglishArray.getString(i);
            things = things +"," + thing;
        }
        JSONArray PEArray = skillObject.getJSONArray("Project Experience");
        for(int i = 0;i<PEArray.size();i++){
            String thing = PEArray.getString(i);
            things = things +"," + thing;
        }
        JSONArray SRArray = skillObject.getJSONArray("Science Research");
        for(int i = 0;i<SRArray.size();i++){
            String thing = SRArray.getString(i);
            things = things +"," + thing;
        }
        return things;
    }

    public static String GetAchievement(String username) {
//        System.out.println(username);
        JSONArray jsonArray = null;
        double g = 0;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/data/Achieve.json")));
            jsonArray = JSON.parseArray(jsonString);// 解析JSON字符串

        } catch (IOException e) {
            e.printStackTrace();
        }
        String things = "";

        // 遍历每个对象
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            // 找到username对应的对象
            if (jsonObj.getString("username").equals(username)) {
                JSONArray mathModeling = jsonObj.getJSONArray("Math Modeling");
                // 遍历该对象的Math Modeling属性数组,如果有的对象没有mathModeling会报错，所以要特判
                if(mathModeling!=null) {
                    for (int j = 0; j < mathModeling.size(); j++) {
                        JSONObject mathModelingObj = mathModeling.getJSONObject(j);

                        // 获取Type属性
                        String type = mathModelingObj.getString("Type");
                        String score = mathModelingObj.getString("Score");
                        if(type.equals("input activityType")||score.equals("input activityScore")){
                            continue;
                        }
//                        System.out.println(things);
//                        System.out.println("i=" + i + ",j="+j);
                        things = things + "," + type + " " + score;
                    }
                }
                JSONArray EnglishCompetition = jsonObj.getJSONArray("English Competition");
                if(EnglishCompetition!=null) {
                    for (int k = 0; k < EnglishCompetition.size(); k++) {
                        JSONObject EnglishCompetitionObj = EnglishCompetition.getJSONObject(k);

                        // 获取Type属性
                        String type = EnglishCompetitionObj.getString("Type");
                        String score = EnglishCompetitionObj.getString("Score");
//                        System.out.println(things);
//                        System.out.println("i=" + i + ",k="+k);
                        things = things + "," + type + " " + score;
                    }
                }
            }
        }


        System.out.println(things);
        return things;
    }

}