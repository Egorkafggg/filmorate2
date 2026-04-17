package ru.yandex.practicum.filmorate.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidateException;
import ru.yandex.practicum.filmorate.model.User;

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
        user.setId(++generateId);

        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        users.put(user.getId(), user);
        log.info("Добавлен пользователь {}", user.getLogin());
        return user;
    }

    @PutMapping
    public User upUser(@Valid @RequestBody User user) {
        if (user.getId() == 0 || !users.containsKey(user.getId())) {
            log.warn("Попытка обновления несуществующего пользователя с id", user.getId());
            throw new ValidateException("Пользователь с id " + user.getId() + " не найден");
        }

        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        users.put(user.getId(), user);
        log.info("Пользователь {} обновлен", user.getLogin());
        return user;
    }

}
