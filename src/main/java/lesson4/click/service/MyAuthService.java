package lesson4.click.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class MyAuthService implements UserDetailsService {
   @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> userList=new ArrayList<>(Arrays.asList(
                new User("Jahongir",passwordEncoder.encode("111"),new ArrayList<>()),
                new User("Sukhrob",passwordEncoder.encode("222"),new ArrayList<>()),
                new User("Hayot",passwordEncoder.encode("222"),new ArrayList<>())
        ));
        for (User user : userList) {
            if (user.getUsername().equals(s)){
                return user;
            }
        }
        throw new UsernameNotFoundException("Not founded");
    }
}
