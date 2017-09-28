package com.example.altarix.security;

import com.example.altarix.user.IUserRepository;
import com.example.altarix.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final IUserRepository userRepository;

    @Autowired
    public CustomAuthenticationProvider(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(login, password);
    }

    /**
     * Return null means can't authenticate.
     */
    private UsernamePasswordAuthenticationToken authenticate(String username, String password) throws AuthenticationException {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
                Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
