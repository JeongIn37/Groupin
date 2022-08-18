package com.songpyeon.groupin.User.service;

import com.songpyeon.groupin.board.domain.Board;
import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.User.entity.User;
import com.songpyeon.groupin.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User join(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        User userEntity = userRepository.save(user);
        return userEntity;
    }

    public User updatePage(PrincipalDetails principalDetails){
        User user = principalDetails.getUser();
        return user;
    }

    @Transactional
    public User userUpdate(User user, PrincipalDetails principalDetails) {
        User persistance = userRepository.findById(principalDetails.getUser().getId());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        persistance.setPassword(encPassword);
        persistance.setNickname(user.getNickname());
        persistance.setEmail(user.getEmail());
        persistance.setRegion(user.getRegion());
        System.out.println("persistance: " + persistance);
        return persistance;
    }

    public List<User> allUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

}
