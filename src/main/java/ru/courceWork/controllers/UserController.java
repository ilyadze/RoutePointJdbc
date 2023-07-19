package ru.courceWork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.courceWork.dao.UserDAO;
import ru.courceWork.models.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public List<User> getUsers() {
        return userDAO.index();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id")int id) {
        return userDAO.show(id);
    }
}
