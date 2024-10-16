package com.example.RecipeProjectBackend.Service;

import com.example.RecipeProjectBackend.Entity.AppUser;
import com.example.RecipeProjectBackend.Repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService  implements UserDetailsService {

    private final AppUserRepository appUserRepository ;
    private final static String USER_NOT_FOUND = "User with email %s not found";
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
