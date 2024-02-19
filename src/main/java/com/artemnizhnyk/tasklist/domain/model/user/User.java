package com.artemnizhnyk.tasklist.domain.model.user;

import com.artemnizhnyk.tasklist.domain.model.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
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
