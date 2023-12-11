package org.example.content.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GradeAnalyzer {

    private static final String GRADE_FILE_PATH = "./src/main/resources/data/Grade.json";

    public static double getRank(String username) throws IOException {
        // 读取JSON文件到字符串
        String jsonString = new String(Files.readAllBytes(Paths.get(GRADE_FILE_PATH)));

        // 使用Fastjson库将JSON字符串解析为JSONObject对象
        JSONObject gradeData = JSONObject.parseObject(jsonString);

        // 获取给定用户名的分数列表
        JSONObject userData = gradeData.getJSONObject(username);
        if (userData == null) {
            throw new IllegalArgumentException("User " + username + " not found in grade data.");
        }
        JSONArray scores = userData.getJSONArray("scores");
        if (scores == null) {
            throw new IllegalArgumentException("Invalid score data for user " + username);
        }

        // 计算均分
        double totalScore = 0.0;
        int scoreCount = 0;
        for (Object scoreObj : scores) {
            if (!(scoreObj instanceof JSONObject)) {
                continue;
            }
            JSONObject score = (JSONObject) scoreObj;
            Object scoreVal = score.get("score");
            if (!(scoreVal instanceof Number)) {
                continue;
            }
            totalScore += ((Number) scoreVal).doubleValue();
            scoreCount++;
        }
        if (scoreCount == 0) {
            throw new IllegalArgumentException("No valid scores found for user " + username);
        }
        double averageScore = totalScore / scoreCount;

        // 计算排名百分比
        List<Double> averages = new ArrayList<>();
        for (String key: gradeData.keySet()) {
            JSONObject user = gradeData.getJSONObject(key);
            JSONArray userScores = user.getJSONArray("scores");
            if (userScores == null) {
                continue;
            }
            totalScore = 0.0;
            scoreCount = 0;
            for (Object scoreObj : userScores) {
                if (!(scoreObj instanceof JSONObject)) {
                    continue;
                }
                JSONObject score = (JSONObject) scoreObj;
                Object scoreVal = score.get("score");
                if (!(scoreVal instanceof Number)) {
                    continue;
                }
                totalScore += ((Number) scoreVal).doubleValue();
                scoreCount++;
            }
            if (scoreCount == 0) {
                averages.add(0.0);
            } else {
                averages.add(totalScore / scoreCount);
            }
        }
        int rank = (int) averages.stream()
                .sorted(Comparator.reverseOrder())
                .collect(java.util.stream.Collectors.toList())
                .indexOf(averageScore) + 1;
        return 100.0 - (rank - 1) * 100.0 / averages.size();
    }

}
