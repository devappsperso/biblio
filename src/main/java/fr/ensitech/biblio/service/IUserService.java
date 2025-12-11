package fr.ensitech.biblio.service;

import fr.ensitech.biblio.entity.User;

import java.util.Date;
import java.util.List;

public interface IUserService {

    void createUser(User user) throws Exception;
    User getUserById(long id) throws Exception;
    List<User> getUsersByBirthdate(Date dateInf, Date dateSup) throws Exception;
}
