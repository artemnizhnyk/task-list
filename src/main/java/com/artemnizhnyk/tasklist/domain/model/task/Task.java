package com.artemnizhnyk.tasklist.domain.model.task;

import com.artemnizhnyk.tasklist.domain.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "tasks")
@Entity
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_seq")
    @SequenceGenerator(name = "tasks_id_seq", sequenceName = "tasks_id_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime expirationDate;
    @ManyToOne
    private User user;
}
