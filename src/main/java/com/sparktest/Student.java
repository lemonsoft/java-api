/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sparktest;

import com.bakishan.user.User;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author ss
 */
public class Student {

    public Student() {

        setupEndpoints();
    }

    private void setupEndpoints() {

        get("/student/init", (request, response) -> {

            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "public/user.html")
            );

        });

        post("/student/add", (request, response) -> {

            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String sex = request.queryParams("sex");

            System.out.println("Test Code :::: " + name);
            System.out.println("Test Code :::: " + age);
            System.out.println("Test Code :::: " + sex);

            response.status(201); // 201 Created
            Map<String, Object> model = new HashMap<>();
            model.put("name", name);
            model.put("age", age);
            model.put("sex", sex);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "public/login.jsp"));

        });
    }
}
