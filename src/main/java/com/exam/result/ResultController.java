/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.result;

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
public class ResultController {
    
    public ResultController() {
        setupEndpoints();

    }
    
    private void setupEndpoints() {

        get("/result/init", (request, response) -> {

            System.out.println("Inside Init :::");
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "public/registration.html")
            );

        });
        post("/result/add", (request, response) -> {

            System.out.println("Inside Init :::");
            Map<String, Object> model = new HashMap<>();

            return new VelocityTemplateEngine().render(new ModelAndView(model, "public/registration.html"));

        });
    }
}
