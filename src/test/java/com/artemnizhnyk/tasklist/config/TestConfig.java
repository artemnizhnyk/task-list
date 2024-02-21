package com.artemnizhnyk.tasklist.config;

import com.artemnizhnyk.tasklist.repository.TaskRepository;
import com.artemnizhnyk.tasklist.repository.UserRepository;
import com.artemnizhnyk.tasklist.service.ImageService;
import com.artemnizhnyk.tasklist.service.impl.AuthServiceImpl;
import com.artemnizhnyk.tasklist.service.impl.ImageServiceImpl;
import com.artemnizhnyk.tasklist.service.impl.TaskServiceImpl;
import com.artemnizhnyk.tasklist.service.impl.UserServiceImpl;
import com.artemnizhnyk.tasklist.service.mapper.*;
import com.artemnizhnyk.tasklist.service.props.JwtProperties;
import com.artemnizhnyk.tasklist.service.props.MinioProperties;
import com.artemnizhnyk.tasklist.web.security.JwtTokenProvider;
import com.artemnizhnyk.tasklist.web.security.JwtUserDetailsService;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
//@TestConfiguration
public class TestConfig {

//    @Bean
//    @Primary
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Primary
//    @Bean
//    public JwtProperties jwtProperties() {
//        JwtProperties jwtProperties = new JwtProperties();
//        jwtProperties.setSecret(
//                "dmdqYmhqbmttYmNhamNjZWhxa25hd2puY2xhZWtic3ZlaGtzYmJ1dg=="
//        );
//        return jwtProperties;
//    }
//
//    @Primary
//    @Bean
//    public UserDetailsService userDetailsService(
//            final UserRepository userRepository,
//            final UserMapper userMapper,
//            final PasswordEncoder passwordEncoder
//    ) {
//        return new JwtUserDetailsService(
//                userService(userRepository, userMapper, passwordEncoder),
//                userMapper
//        );
//    }
//
//    @Primary
//    @Bean
//    public MinioClient minioClient() {
//        return Mockito.mock(MinioClient.class);
//    }
//
//    @Primary
//    @Bean
//    public MinioProperties minioProperties() {
//        MinioProperties properties = new MinioProperties();
//        properties.setBucket("images");
//        return properties;
//    }
//
//    @Bean
//    @Primary
//    public ImageService imageService(final MinioProperties minioProperties, final MinioClient minioClient) {
//        return new ImageServiceImpl(minioClient, minioProperties);
//    }
//
//    @Primary
//    @Bean
//    public JwtTokenProvider tokenProvider(
//            final UserRepository userRepository,
//            final JwtProperties jwtProperties,
//            final UserMapper userMapper,
//            final PasswordEncoder passwordEncoder
//    ) {
//        return new JwtTokenProvider(
//                jwtProperties,
//                userDetailsService(userRepository, userMapper, passwordEncoder),
//                userService(userRepository,userMapper, passwordEncoder)
//        );
//    }
//
//    @Bean
//    @Primary
//    public UserServiceImpl userService(
//            final UserRepository userRepository,
//            final UserMapper userMapper,
//            final PasswordEncoder passwordEncoder
//    ) {
//        return new UserServiceImpl(
//                userRepository,
//                userMapper,
//                passwordEncoder
//        );
//    }
//
//    @Bean
//    @Primary
//    public TaskServiceImpl taskService(
//            final TaskRepository taskRepository,
//            final UserRepository userRepository,
//            final UserMapper userMapper,
//            final PasswordEncoder passwordEncoder,
//            final MinioClient minioClient,
//            final MinioProperties minioProperties
//    ) {
//        return new TaskServiceImpl(
//                taskRepository,
//                userService(userRepository, userMapper, passwordEncoder),
//                imageService(minioProperties, minioClient),
//                taskMapper(),
//                userMapper(),
//                taskImageMapper()
//        );
//    }
//
//    @Bean
//    @Primary
//    public AuthServiceImpl authService(
//            final UserRepository userRepository,
//            final AuthenticationManager authenticationManager,
//            final UserMapper userMapper,
//            final PasswordEncoder passwordEncoder,
//            final JwtProperties jwtProperties
//    ) {
//        return new AuthServiceImpl(
//                authenticationManager,
//                userService(userRepository, userMapper, passwordEncoder),
//                tokenProvider(userRepository, jwtProperties, userMapper, passwordEncoder),
//                userMapper()
//        );
//    }
//
//    @Bean
//    public UserRepository userRepository() {
//        return Mockito.mock(UserRepository.class);
//    }
//
//    @Bean
//    public TaskRepository taskRepository() {
//        return Mockito.mock(TaskRepository.class);
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        return Mockito.mock(AuthenticationManager.class);
//    }
//
//    @Bean
//    public UserMapper userMapper() {
//        return new UserMapperImpl();
//    }
//
//    @Bean
//    public TaskMapper taskMapper() {
//        return new TaskMapperImpl();
//    }
//
//    @Bean
//    public TaskImageMapper taskImageMapper() {
//        return new TaskImageMapperImpl();
//    }
}