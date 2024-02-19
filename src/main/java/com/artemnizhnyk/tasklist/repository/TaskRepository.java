package com.artemnizhnyk.tasklist.repository;

import com.artemnizhnyk.tasklist.domain.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserId(final Long userId);
}
