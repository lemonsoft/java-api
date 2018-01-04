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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {

    public static Map<String, User> users = new HashMap<>();
    private static final AtomicInteger count = new AtomicInteger(0);

    public User findById(String id) {
        return users.get(id);
    }

    public User add(String name, String email) {
        int currentId = count.incrementAndGet();
        User user = new User(currentId, name, email);
        users.put(String.valueOf(currentId), user);
        return user;
    }

    public User update(String id, String name, String email) {

        User user = users.get(id);
        if (name != null) {
            user.setName(name);
        }

        if (email != null) {
            user.setEmail(email);
        }
        users.put(id, user);

        return user;

    }

    public void delete(String id) {
        users.remove(id);
    }

    public List findAll() {
        return new ArrayList<>(users.values());
    }

    public UserService() {
    }
}
