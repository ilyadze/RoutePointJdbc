package ru.courceWork.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.courceWork.models.User;

import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index() {
        return jdbcTemplate.query("SELECT Person.name as name,Person.email as email, Person.password as password, Role.name as role FROM Person JOIN User_Role on Person.user_id = User_Role.user_id JOIN Role on Role.role_id = User_Role.role_id;",
                new BeanPropertyRowMapper<>(User.class));
    }

    public User show(int id) {
        return jdbcTemplate.query("SELECT Person.name as name,Person.email as email, Person.password as password, Role.name as role FROM Person JOIN User_Role on Person.user_id = User_Role.user_id JOIN Role on Role.role_id = User_Role.role_id WHERE Person.user_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }
}
