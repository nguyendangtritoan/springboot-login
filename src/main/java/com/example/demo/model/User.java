package com.example.demo.model;

import com.example.demo.validation.UpdateValidationGroup;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 4, max = 50, message = "First name size must be between 4 - 50",groups = {Default.class, UpdateValidationGroup.class})
    private String firstName;

    @Column(nullable = false)
    @Size(min = 4, max = 50, message = "Last name size must be between 4 - 50",groups = {Default.class, UpdateValidationGroup.class})
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(groups = {Default.class, UpdateValidationGroup.class})
    @NotEmpty(message = "Email should not be empty", groups = {Default.class, UpdateValidationGroup.class})
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Password can not be empty")
    private String password;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birthday cannot be in the future",groups = {Default.class, UpdateValidationGroup.class})
    @NotNull(message = "Birthday must not be empty", groups = {Default.class, UpdateValidationGroup.class})
    private LocalDate birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    // getters and setters
}
