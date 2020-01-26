package com.springboot.poc.demo.springboot.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static Integer userCount = 4;

    static {
        users.add(new User(1, "Ash", new Date()));
        users.add(new User(2, "Sam", new Date()));
        users.add(new User(3, "Paul", new Date()));
        users.add(new User(4, "Jhon", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User add(User user) {

        if(user.getId() == null)
            user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User find(int id) {
        for(User u:users){
            if(u.getId() == id)
                return u;
        }
        return null;
    }

    public User delete(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User u = iterator.next();
            if (u.getId() == id){
                iterator.remove();
                return u;
            }
        }
        return null;
    }

}
