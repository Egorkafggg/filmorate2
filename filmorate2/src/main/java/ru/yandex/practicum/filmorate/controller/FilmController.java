package ru.yandex.practicum.filmorate.controller;



import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidateException;
import ru.yandex.practicum.filmorate.model.Film;


import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/film")
public class FilmController {

    //private final static Logger log = LoggerFactory.getLogger(FilmController.class);
    private final Map<Long, Film> films = new HashMap<>();
    private static final LocalDate MIN_RELEASE = LocalDate.of(1895, 12, 28);
    private long generateId = 0;


    @PostMapping
    public Film create(@Valid @RequestBody Film film){
        film.setId(++generateId);
        films.put(film.getId(), film);
        log.info("Добавлен фильм {}",film);
        return film;
    }

    @PutMapping
    public Film upFilm(@Valid@RequestBody Film film){
        if (film.getId() == 0 || films.containsKey(film.getId())){
            log.warn("Попытка обновления не существующего фильма",film.getId());
            throw new ValidateException("Фильм с Id " + film.getId() + " не найден");
        } 
        films.put(film.getId(), film);
        log.info("Обновлен фильм {}",film.getId());
        return film;
    }

    @GetMapping
    public Collection<Film> listFilms(){ 
        log.info("Получен запрос на получение всех фильмов");
        return films.values();
    } 
} 
