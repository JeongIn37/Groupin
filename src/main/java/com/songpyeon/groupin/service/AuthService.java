package com.songpyeon.groupin.service;

import com.songpyeon.groupin.config.auth.PrincipalDetails;
import com.songpyeon.groupin.entity.User;
import com.songpyeon.groupin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User userUpdate(User user) {
        User persistance = userRepository.findById(user.getId());
        System.out.println(persistance);
        persistance.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        persistance.setNickname(user.getNickname());
        persistance.setEmail(user.getEmail());
        persistance.setRegion(user.getRegion());
        System.out.println(user.getNickname());
        System.out.println(persistance);
        return persistance;
    }

}
