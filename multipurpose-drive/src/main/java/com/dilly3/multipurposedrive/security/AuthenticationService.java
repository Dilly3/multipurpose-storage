package com.dilly3.multipurposedrive.security;


import com.dilly3.multipurposedrive.mapper.UsersMapper;
import com.dilly3.multipurposedrive.model.IUser;
import com.dilly3.multipurposedrive.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

   private final UsersMapper userMapper ;
   private final HashService hashService;
@Autowired
    public AuthenticationService(UsersMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String username = authentication.getName();
       String password = authentication.getCredentials().toString();
        IUser user = userMapper.getUser(username);
       if (user != null){
           String encodedSalt = user.getSalt();
           String hashedPassword = hashService.getHashedValue(password,encodedSalt);
           if (user.getPassword().equals(hashedPassword)){
               return new UsernamePasswordAuthenticationToken(username,password, new ArrayList<>());
           }
       }
       return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public boolean isUserLoggedIn(){
        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);


    }
}
