package com.songpyeon.groupin.User.controller;

import com.songpyeon.groupin.User.config.auth.PrincipalDetails;
import com.songpyeon.groupin.User.entity.User;
import com.songpyeon.groupin.User.handler.ex.CustomValidationException;
import com.songpyeon.groupin.User.service.AuthService;
import com.songpyeon.groupin.web.dto.auth.SignupDto;
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
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
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
            return "/auth/signin";
        }
    }

    @GetMapping("/auth/update")
    public ResponseEntity<User> updatePage(@AuthenticationPrincipal PrincipalDetails principalDetails){
        User userEntity = authService.updatePage(principalDetails);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    
    @PatchMapping("/auth/update")
    public ResponseEntity<User> userUpdate(User user){
        User userUpdate = authService.userUpdate(user);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }
}
