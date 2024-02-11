package com.artemnizhnyk.tasklist.repository;

import com.artemnizhnyk.tasklist.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(final String username);

//    boolean existsByIdAndTasksContains(final Long id, final List<Task> tasks);
}
