package model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.Instant;

@Data
public class User {


    private long id;

    @NotBlank
    @Email(message = "email должен быть корректным")
    private String email;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "Логин не может содержать пробелы")
    private String login;


    private String name;

    @PastOrPresent(message = "Дата рождения не может быть в будущем")
    private Instant birthday;
}
