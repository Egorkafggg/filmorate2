package ru.yandex.practicum.filmorate.model;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Instant;

@Data
public class Film {

    private long id;

    @NotBlank
    private String name;

    @Size(min = 1, max = 200)
    private String description;

    @NotNull
    @PastOrPresent(message = "Дата релиза не может быть в будущем")
    private Instant releaseDate;

    @Min(1)
    private Integer duration;
}
