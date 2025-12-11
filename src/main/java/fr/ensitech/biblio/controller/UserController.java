package fr.ensitech.biblio.controller;

import fr.ensitech.biblio.entity.User;
import fr.ensitech.biblio.service.IUserService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/users")
public class UserController implements IUserController {

    @Autowired
    private IUserService userService;

    // => http://localhost:8080/api/users/infos
    @GetMapping("/infos")
    public ResponseEntity<String> getInfos() {
        return new ResponseEntity<String>("Users / API REST Ready.", HttpStatus.OK);
    }

    @Override
    @PostMapping("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("'Create User' invoqued.");
        if (user == null
                || user.getFirstname() ==null || user.getFirstname().isBlank()
                || user.getLastname() == null || user.getLastname().isBlank()
                || user.getEmail() == null || user.getEmail().isBlank()
                || user.getPassword() == null || user.getPassword().isBlank()
                || user.getBirthdate() == null
                || user.getRole() == null || user.getRole().isBlank()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            user.setActive(false);
            userService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
