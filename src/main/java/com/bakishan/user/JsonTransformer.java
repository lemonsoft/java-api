/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakishan.user;

import com.google.gson.Gson;
import java.util.HashMap;
import spark.Response;
import spark.ResponseTransformer;

/**
 *
 * @author ss
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        if (model instanceof Response) {
            return gson.toJson(new HashMap<>());
        }
        return gson.toJson(model);
    }

}
