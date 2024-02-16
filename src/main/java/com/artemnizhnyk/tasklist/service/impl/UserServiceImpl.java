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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Cacheable(value = "UserService::getByIdOrThrowException", key = "#id")
    @Transactional(readOnly = true)
    @Override
    public UserDto getByIdOrThrowException(final Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() ->
                new ResourceNotFoundException(String.format("User with id: %d, wasn't found", id)));
        return userMapper.toDto(user);
    }

    @Cacheable(value = "UserService::getByUsernameOrThrowException", key = "#username")
    @Transactional(readOnly = true)
    @Override
    public UserDto getByUsernameOrThrowException(final String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException(String.format("User with name: %s, wasn't found", username)));
        return userMapper.toDto(user);
    }

    @Caching(put = {
            @CachePut(value = "UserService::getByIdOrThrowException", key = "#userDto.id"),
            @CachePut(value = "UserService::getByUsernameOrThrowException", key = "#userDto.username")
    })
    @Transactional
    @Override
    public UserDto updateUser(final UserDto userDto) {
        Role userRole = getByIdOrThrowException(userDto.getId()).getRole();
        userDto.setRole(userRole);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User updatedUser = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(updatedUser);
    }

    @Caching(cacheable = {
            @Cacheable(value = "UserService::getByIdOrThrowException", key = "#userDto.id"),
            @Cacheable(value = "UserService::getByUsernameOrThrowException", key = "#userDto.username")
    })
    @Transactional
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
        user.setRole(Role.ROLE_USER);
        return userMapper.toDto(userRepository.save(user));
    }

    @Cacheable(value = "UserService::isTaskOwner", key = "#userId" + "." + "#taskId")
    @Override
    public boolean isTaskOwner(final Long userId, final Long taskId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() ->
                new ResourceNotFoundException(String.format("User with id: %d, wasn't found", userId)));
        return user.getTasks().stream().anyMatch(it -> it.getId().equals(taskId));
    }

    @CacheEvict(value = "UserService::getByIdOrThrowException", key = "#id")
    @Transactional
    @Override
    public AnswerDto deleteById(final Long id) {
        getByIdOrThrowException(id);
        userRepository.deleteById(id);
        return AnswerDto.makeDefault(true);
    }
}
