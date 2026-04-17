package ru.yandex.practicum.filmorate.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validatiors.UserValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    Map<Long, User> users = new HashMap<>();
    private long generateId = 0;

    @GetMapping
    public Collection<User> listUser() {
        return users.values();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        UserValidator.validate(user, users, HttpMethod.POST);
        user.setId(++generateId);
        users.put(user.getId(), user);
        log.info("Добавлен пользователь {}", user.getLogin());
        return user;
    }

    @PutMapping
    public User upUser(@Valid @RequestBody User user) {
        UserValidator.validate(user, users, HttpMethod.PUT);
        users.put(user.getId(), user);
        log.info("Пользователь {} обновлен", user.getLogin());
        return user;
    }

}
