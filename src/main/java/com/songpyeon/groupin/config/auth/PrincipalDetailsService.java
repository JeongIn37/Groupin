package com.songpyeon.groupin.config.auth;
import com.songpyeon.groupin.user.User;
import com.songpyeon.groupin.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        User userEntity = userRepository.findById(id);
        if(userEntity == null){
            return null;
        }
        else{
            return new PrincipalDetails(userEntity);

        }
    }

}
