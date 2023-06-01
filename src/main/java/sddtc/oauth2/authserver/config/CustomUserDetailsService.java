package sddtc.oauth2.authserver.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by sddtc on 2017/4/6.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList list = new ArrayList();
        list.add(new SimpleGrantedAuthority("ROLE_hello"));
        User details = new User("sddtc", "N/A", list);
        return details;
    }
}
