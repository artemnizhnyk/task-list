package com.artemnizhnyk.tasklist.domain.model.task;

import com.artemnizhnyk.tasklist.domain.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "tasks")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime expirationDate;
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @ManyToOne
    private User user;
}
