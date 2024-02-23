package com.example.focus_group.controller;

import com.example.focus_group.dto.UserDTO;
import com.example.focus_group.models.user;
import com.example.focus_group.services.JWTUtil;
import com.example.focus_group.services.UserService;
import com.example.focus_group.util.UserNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AutoController {
    private final UserService userService;
    private final JWTUtil jwtUtil;

    private final ModelMapper modelMapper;

    @Autowired
    public AutoController(UserService userService, JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostMapping()
    public Map<String, String> performRegistration(@RequestBody @Validated UserDTO userDTO, BindingResult bindingResult) {
        user user = convertToUser(userDTO);
        if ( bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMsg.toString());
        }
        userService.save(user);
        String token = jwtUtil.generateToken(user.getLogin(), user.getPassword(), user.getRole());
        return Map.of("token", token);
    }

    public user convertToUser(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, user.class);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.findAll().stream().map(this::convertToUserDTO).collect(Collectors.toList()); // jackson конвертирует в json
    }

    private UserDTO convertToUserDTO(user user) {
        return this.modelMapper.map(user, UserDTO.class);
    }

}
