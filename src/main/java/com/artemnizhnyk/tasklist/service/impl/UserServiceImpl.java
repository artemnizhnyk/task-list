package com.artemnizhnyk.tasklist.service.impl;

import com.artemnizhnyk.tasklist.domain.exception.ResourceNotFoundException;
import com.artemnizhnyk.tasklist.domain.model.user.Role;
import com.artemnizhnyk.tasklist.domain.model.user.User;
import com.artemnizhnyk.tasklist.repository.UserRepository;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.service.mapper.UserMapper;
import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getByIdOrThrowException(final Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() ->
                new ResourceNotFoundException(String.format("User with id: %d, wasn't found", id)));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getByUsername(final String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException(String.format("User with name: %s, wasn't found", username)));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateTask(final UserDto userDto) {
        User updatedUser = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(updatedUser);
    }

    @Override
    public UserDto createOrThrowException(final UserDto userDto) {
        userRepository.findByUsername(userDto.getUsername())
                .ifPresent(user -> {
                    throw new IllegalStateException("User already exists");
                });
        if (!userDto.getPassword().equals(userDto.getPasswordConfirmation())) {
            throw new IllegalStateException("Password and password confirmation don't match.");
        }
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = Set.of(Role.ROLE_USER);
        user.setRoles(roles);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public boolean isTaskOwner(final Long userId, final Long taskId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() ->
                new ResourceNotFoundException(String.format("User with id: %d, wasn't found", userId)));
        return user.getTasks().stream().anyMatch(it -> it.getId().equals(taskId));
    }

    @Override
    public AnswerDto deleteById(final Long id) {
        getByIdOrThrowException(id);
        userRepository.deleteById(id);
        return AnswerDto.makeDefault(true);
    }
}
