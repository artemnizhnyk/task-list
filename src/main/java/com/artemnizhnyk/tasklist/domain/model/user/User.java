package com.artemnizhnyk.tasklist.domain.model.user;

import com.artemnizhnyk.tasklist.domain.model.task.Task;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String username;
    private String password;
    @Transient
    private String passwordConfirmation;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();
}
