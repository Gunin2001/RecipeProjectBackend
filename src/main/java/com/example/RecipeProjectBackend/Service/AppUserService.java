package com.example.RecipeProjectBackend.Service;

import com.example.RecipeProjectBackend.Entity.AppUser;
import com.example.RecipeProjectBackend.Entity.ConfirmationToken;
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
    private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND = "User with email %s not found";
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }


    public String signUp(AppUser appUser){

        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if(userExists){
            throw new IllegalStateException("the email already exists");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
