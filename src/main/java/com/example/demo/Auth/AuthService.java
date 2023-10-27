package com.example.demo.Auth;

import com.example.demo.Person.Person;
import com.example.demo.Person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void register(RegistrationDto registrationDto) {
        Person person = new Person();
        person.setFirstName(registrationDto.getFirstName());
        person.setLastName(registrationDto.getLastName());
        person.setUserName(registrationDto.getUserName());
        person.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        personRepository.save(person);
    }

    public String getJwt(LoginDto loginDto) {
        Person person = personRepository.findByUserName(loginDto.getUserName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!bCryptPasswordEncoder.matches(loginDto.getPassword(), person.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return jwtService.createJwt(person.getUserName());
    }
}
