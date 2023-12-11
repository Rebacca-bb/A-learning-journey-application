package org.example.content.utils;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ShiYuang
 * @date 2023/4/1 17:38
 */

public class DataserviceTest {


//        @Before
//        public void init() {
//            // 初始化一个包含多个学生的 StudentService 对象
//            dataService = new DataService();
//            dataService.addStudent(new Student("Tom", "123456"));
//            dataService.addStudent(new Student("Jerry", "abcdef"));
//            dataService.addStudent(new Student("Alice", "qwerty"));
//        }

        @Test
        public void testCheckAdminPassword() {
            DataService dataService = new DataService();
//            // 测试账号不存在的情况
//            assertEquals(false, dataService.checkAdminPassword("Bob", "123456"));
//
            // 测试账号存在但密码不正确的情况
            assertEquals(false, dataService.checkStudentPassword("2020213137", "111111"));
            assertEquals(false, dataService.checkStudentPassword("2020214568", "123123"));

            // 测试账号存在且密码正确的情况
            assertEquals(true, dataService.checkStudentPassword("2020213137", "1234567"));
            assertEquals(true, dataService.checkStudentPassword("2020214568", "1234568"));
        }

        @Test
        public void testGetAchievement() {
            // create a temporary file for testing

            DataService myJSON = new DataService();
            String things1 = myJSON.GetAchievement("2020213319");
            String testThings1 = ",2022 English contest 100";
            assertEquals(testThings1, things1);
        }
        @Test
        public void testGetGrades(){
            DataService myJSON = new DataService();
            double grades1 = myJSON.GetGrades("2020213319");
            double grades2 = myJSON.GetGrades("2020211919");

            assertEquals(85, grades1,0);
            assertEquals(81, grades2,1);
        }
    }



