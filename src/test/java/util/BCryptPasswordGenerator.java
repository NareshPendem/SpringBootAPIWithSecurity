package util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Use this method to generate BCrypt Password which is used for preconfigured passwords in resources/data.sql
 */
public class BCryptPasswordGenerator {
    public static void main(String args[]){
        PasswordEncoder e = new BCryptPasswordEncoder();
        System.out.println(e.encode("admin"));
    }
}
