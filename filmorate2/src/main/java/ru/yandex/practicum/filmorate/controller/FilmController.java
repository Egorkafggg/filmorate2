package ru.yandex.practicum.filmorate.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validatiors.FilmValidator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/film")
public class FilmController {
    private static final LocalDate MIN_RELEASE = LocalDate.of(1895, 12, 28);
    private final Map<Long, Film> films = new HashMap<>();
    private long generateId = 0;


    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        FilmValidator.validate(film, films, HttpMethod.POST);
        film.setId(++generateId);
        films.put(film.getId(), film);
        log.info("Добавлен фильм {}", film);
        return film;
    }

    @PutMapping
    public Film upFilm(@Valid @RequestBody Film film) {
        FilmValidator.validate(film, films, HttpMethod.PUT);
        films.put(film.getId(), film);
        log.info("Обновлен фильм {}", film.getId());
        return film;
    }

    @GetMapping
    public Collection<Film> listFilms() {
        log.info("Получен запрос на получение всех фильмов");
        return films.values();
    }
}