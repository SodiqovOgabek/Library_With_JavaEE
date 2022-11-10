package com.company.library_javaEE.service.auth;

import com.company.library_javaEE.configs.ApplicationContextHolder;
import com.company.library_javaEE.dao.AbstractDAO;
import com.company.library_javaEE.dao.UserDAO;
import com.company.library_javaEE.domains.User;
import com.company.library_javaEE.dto.auth.UserCreateDTO;
import com.company.library_javaEE.dto.auth.UserDTO;
import com.company.library_javaEE.dto.auth.UserLoginDTO;
import com.company.library_javaEE.exceptions.AuthenticationException;
import com.company.library_javaEE.exceptions.InvalidInputException;
import com.company.library_javaEE.exceptions.NotFoundException;
import com.company.library_javaEE.utils.BaseUtils;

import java.util.Optional;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/10/2022 12:51 AM (Thursday)
 * Library_JavaEE/IntelliJ IDEA
 */
public class UserService  extends AbstractDAO<UserDAO> {


    private static UserService userService;
    private final BaseUtils baseUtils = ApplicationContextHolder.getBean(BaseUtils.class);


    private UserService() {
        super(ApplicationContextHolder.getBean(UserDAO.class));
    }

    public UserDTO create(UserCreateDTO userCreateDTO) throws InvalidInputException {
        Optional<User> byEmail = dao.findByEmail(userCreateDTO.getEmail());
        if (byEmail.isPresent()) {
            throw new InvalidInputException("User by email %s already exists".formatted(byEmail.get()));
        }
        User user = User.builder()
                .email(userCreateDTO.getEmail())
                .name(userCreateDTO.getName())
                .password(baseUtils.encode(userCreateDTO.getPassword()))
                .surname(userCreateDTO.getSurname())
                .build();
        User savedUser = dao.save(user);
        return UserDTO.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .surname(savedUser.getSurname())
                .name(savedUser.getName())
                .build();
    }


    public User get(long id) {
        return null;
    }





    public UserDTO getByEmail(String emailAddress) throws NotFoundException {
        Optional<User> byEmail = dao.findByEmail(emailAddress);
        User user = byEmail.orElseThrow(() -> new NotFoundException("user not found by email"));
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .status(user.getStatus())
                .build();
    }

    public UserDTO login(UserLoginDTO userLoginDTO) throws InvalidInputException, AuthenticationException {
        String email = userLoginDTO.getEmail();
        Optional<User> byEmail = dao.findByEmail(email);
        User user = byEmail.orElseThrow(() -> new InvalidInputException("user not found by email %s".formatted(email)));
        if (!baseUtils.matchPassword(userLoginDTO.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Bad credentials");
        }
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
}



