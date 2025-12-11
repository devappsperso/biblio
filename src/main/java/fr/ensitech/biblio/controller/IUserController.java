package fr.ensitech.biblio.controller;

import fr.ensitech.biblio.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserController {

    ResponseEntity<User> createUser(User user);
}
