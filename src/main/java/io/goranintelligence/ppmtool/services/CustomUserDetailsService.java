package io.goranintelligence.ppmtool.services;

import io.goranintelligence.ppmtool.domain.User;
import io.goranintelligence.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null) new UsernameNotFoundException("Korisnik ne postoji");
        return user;
    }

    @Transactional
    public User loadUserByID(Long id){
        User user = userRepository.getById(id);
        if(user==null) new UsernameNotFoundException("Korisnik ne postoji");
        return user;
    }

}
