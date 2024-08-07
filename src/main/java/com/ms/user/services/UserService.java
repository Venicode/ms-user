package com.ms.user.services;

import com.ms.user.domains.User;
import com.ms.user.dtos.UserDto;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Getter
@Setter
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProducer userProducer;

    public ResponseEntity<User> registerNewUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.name());
        user.setLastName(userDto.lastName());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        user.setConfirmed("Pendency");
        User userSaved = this.userRepository.save(user);
        userProducer.publishMessageEmail(user, "Confirm your registration",
                "Hello " + user.getName() + " , confirm your registration by clicking the link: http://localhost:8081/user/confirm/"+userSaved.getId().toString());

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    public ResponseEntity<Object> confirmUser(UUID idUser) {
        User user = this.userRepository.getReferenceById(idUser);
        user.setConfirmed("Yes");
        this.userRepository.save(user);
        userProducer.publishMessageEmail(user, "Registration confirmed successfully!",
                "Hello " + user.getName() + " , welcome to the platform!");
        return new ResponseEntity<>("Registration confirmed successfully!", HttpStatus.CREATED);
    }

}
