/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakishan.user;

/**
 *
 * @author ss
 */
import com.exam.login.LoginController;
import com.exam.registration.RegistrationController;
import com.exam.result.ResultController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import spark.ModelAndView;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import com.sparktest.Student;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {

    private static UserService userService = new UserService();
    private static ObjectMapper om = new ObjectMapper();

    public static void main(String[] args) {

        // Start embedded server at this port
        port(8090);
        //staticFileLocation("/public");
        // Main Page, welcome
        
        Student st=new Student();
        LoginController login=new LoginController();
        RegistrationController register=new RegistrationController();
        ResultController results=new ResultController();
        
        get("/", (request, response) -> "Welcome");

        // POST - Add an user
        post("/user/add", (request, response) -> {

            String name = request.queryParams("name");
            String email = request.queryParams("email");
            User user = userService.add(name, email);
            response.status(201); // 201 Created
            return om.writeValueAsString(user);

        });

        // GET - Give me user with this id
        get("/user/:id", (request, response) -> {
            User user = userService.findById(request.params(":id"));
            if (user != null) {
                return om.writeValueAsString(user);
            } else {
                response.status(404); // 404 Not found
                return om.writeValueAsString("user not found");
            }
        });

        // Get - Give me all users
        get("/user", (request, response) -> {
            List result = userService.findAll();
            if (result.isEmpty()) {
                return om.writeValueAsString("user not found");
            } else {
                return om.writeValueAsString(userService.findAll());
            }
        });

        // PUT - Update user
        put("/user/:id", (request, response) -> {
            String id = request.params(":id");
            User user = userService.findById(id);
            if (user != null) {
                String name = request.queryParams("name");
                String email = request.queryParams("email");
                userService.update(id, name, email);
                return om.writeValueAsString("user with id " + id + " is updated!");
            } else {
                response.status(404);
                return om.writeValueAsString("user not found");
            }
        });

        // DELETE - delete user
        delete("/user/:id", (request, response) -> {
            String id = request.params(":id");
            User user = userService.findById(id);
            if (user != null) {
                userService.delete(id);
                return om.writeValueAsString("user with id " + id + " is deleted!");
            } else {
                response.status(404);
                return om.writeValueAsString("user not found");
            }
        });

        get("/home", (req, res) -> {
            
            System.out.println("Inside Home :  ");
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "public/index.html")
            );
        });

    }

}
