package com.example.elembase.Services;

import com.example.elembase.Entitity.User;
import com.example.elembase.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<String> getAllUsersNames(){
        List<User> users = userRepo.findAll();
        List<String> usersNames = new ArrayList<>();
        for (User user : users) {
            usersNames.add(user.getName());
        }
        return usersNames;
    }

    public void createNewUser(User user){
        System.out.println("hello");
        userRepo.save(user);
    }

}
