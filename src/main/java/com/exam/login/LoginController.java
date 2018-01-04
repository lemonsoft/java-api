/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.login;

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
public class LoginController {

    public LoginController() {
        setupEndpoints();

    }

    private void setupEndpoints() {

        get("/login/init", (request, response) -> {

            System.out.println("Inside Init :::");
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "public/login.html")
            );

        });
        post("/login/auth", (request, response) -> {

            String forward = "";
            Map<String, Object> model = new HashMap<>();
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            boolean userflag = authUserLogin(username, password);
            if (userflag) {

                request.session().attribute("username", username); 
                
                response.redirect("/login/dashboard");

            } else {
                
                
                forward = new VelocityTemplateEngine().render(new ModelAndView(model, "public/login.html"));
            }

            return forward;

        });
        get("/login/dashboard", (request, response) -> {

            String username=request.session().attribute("username"); 
            Map<String, Object> model = new HashMap<>();
            model.put("username", username);
            return new VelocityTemplateEngine().render(new ModelAndView(model, "public/dashboard.html"));

        });
    }

    public boolean authUserLogin(String username, String password) {

        boolean flag = true;

        String hiveQuery = "";

        return flag;
    }
}
