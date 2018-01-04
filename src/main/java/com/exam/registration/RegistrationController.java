/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.registration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author ss
 */
public class RegistrationController {
    
    public RegistrationController() {
        setupEndpoints();
        
    }
    
    private void setupEndpoints() {
        
        get("/register/init", (request, response) -> {
            
            System.out.println("Inside Init :::");
            Map<String, Object> model = new HashMap<>();
            
            return new VelocityTemplateEngine().render(new ModelAndView(model, "public/registration.html"));
            
        });
        post("/register/add", (request, response) -> {
            
            String forward = "";
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
            String status = addUser(regdao, request);
            if (status.equals("saved")) {
                forward = new VelocityTemplateEngine().render(new ModelAndView(model, "public/registration.html"));
            } else {
                forward = new VelocityTemplateEngine().render(new ModelAndView(model, "public/registration.html"));
            }
            
            return forward;
            
        });
        
        get("/register/initsearch", (request, response) -> {
            
            Map<String, Object> model = new HashMap<>();
            
            return new VelocityTemplateEngine().render(new ModelAndView(model, "public/search.html"));
            
        });
        post("/register/search", (request, response) -> {
            
            ArrayList model = new ArrayList();
            
            String stname = request.queryParams("stname");
            Map<String, ArrayList> userdao = request.session().attribute("user");
            if (userdao != null) {
                ArrayList users = userdao.get("users");
                Iterator itr = users.iterator();
                while (itr.hasNext()) {
                    RegistrationDAO regdao = (RegistrationDAO) itr.next();
                    if (regdao.getName().equals(stname)) {
                        model.add(regdao);
                    }
                }
                
            }
          return new VelocityTemplateEngine().render(new ModelAndView(model, "public/registration.html"));
            
        });
        
        get("/register/initUpdate", (request, response) -> {
            
            System.out.println("Inside Init :::");
            String recid = request.queryParams("recid");
            Map<String, ArrayList> userdao = request.session().attribute("user");
            if (userdao != null) {
                ArrayList users = userdao.get("users");
                
            }
           return new VelocityTemplateEngine().render(new ModelAndView("", "public/registration.html"));
            
        });
    }
    
    public String addUser(RegistrationDAO regdao, Request request) {
        
        String status = "saved";
        int record =1;
        
        Map<Integer, RegistrationDAO> userdao = request.session().attribute("user");
        if (userdao != null) {
            RegistrationDAO users = userdao.get("users");
            record++;
            userdao.put(record, users);
            
        } else {
            RegistrationDAO users = new RegistrationDAO();
            //users.add(regdao);
            Map<String, ArrayList> usersdao = new HashMap();
            //usersdao.put("users", users);
            request.session().attribute("user", usersdao);
        }
        
        String hiveQuery = "";
        
        return status;
    }
}
