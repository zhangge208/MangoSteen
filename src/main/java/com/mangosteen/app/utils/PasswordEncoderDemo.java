package com.mangosteen.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderDemo {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String encodedPassword = passwordEncoder.encode("mangosteen");
        System.out.println(encodedPassword);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("mangosteen");
        System.out.println(encoder.matches("mangosteen", "$2a$WrxWeUdGrxL4c5zQSlDIWOvzjg16$zwD7d9rpOV1aEmMInEpORmPdF.u"));
        System.out.println(result);

    }
}
