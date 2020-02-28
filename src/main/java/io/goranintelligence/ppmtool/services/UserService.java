package io.goranintelligence.ppmtool.services;

import io.goranintelligence.ppmtool.domain.User;
import io.goranintelligence.ppmtool.exceptions.UsernameAlreadyExistsException;
import io.goranintelligence.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

            try {
                newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
                //Username has to be uniqe (exception)
                newUser.setUsername(newUser.getUsername());

                //Password i confirmPass se poklapaju
                //Ne pokazujemo confPass
                newUser.setConfirmPassword("");
                return userRepository.save(newUser);
            }catch (Exception e){
                throw new UsernameAlreadyExistsException("Korisnik '"+newUser.getUsername()+"' postoji");
            }

    }

}
