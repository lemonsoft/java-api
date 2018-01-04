/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.dao;

import com.exam.registration.RegistrationDAO;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author ss
 */
public class StudentController {

    public StudentController() {
        setupEndpoints();

    }

    private void setupEndpoints() {

        get("/student/init", (request, response) -> {

            System.out.println("Inside Init :::");
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "public/registration.html")
            );

        });
        post("/student/add", (request, response) -> {

            System.out.println("Inside Init :::");
            Map<String, Object> model = new HashMap<>();

            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String occupation = request.queryParams("occupation");
            String batch = request.queryParams("batch");

            RegistrationDAO regdao = new RegistrationDAO();
            regdao.setName(name);
            regdao.setAge(age);
            regdao.setOccupation(occupation);
            regdao.setBatch(batch);
            

            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "public/registration.html")
            );

        });
    }
}
