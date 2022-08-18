package com.songpyeon.groupin.User.controller;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.User.entity.User;
import com.songpyeon.groupin.handler.ex.CustomValidationException;
import com.songpyeon.groupin.User.service.AuthService;
import com.songpyeon.groupin.User.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "/auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "/auth/signup";
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody SignupDto signupDto, BindingResult bindingResult) {
        log.info(signupDto.toString());

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패", errorMap);

        }
        else {
            User user = signupDto.toEntity();
            log.info(user.toString());
            User userEntity = authService.join(user);
            System.out.println(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }}

    @GetMapping("/auth/update")
    public ResponseEntity<User> updatePage(@AuthenticationPrincipal PrincipalDetails principalDetails){
        User userEntity = authService.updatePage(principalDetails);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    
    @PatchMapping("/auth/update")
    public ResponseEntity<User> userUpdate(@RequestBody User user, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User userUpdate = authService.userUpdate(user, principalDetails);
        principalDetails.setUser(userUpdate);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    @GetMapping("/auth/users")
    public ResponseEntity<List<User>> allUsers(){
        List<User> users = authService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
