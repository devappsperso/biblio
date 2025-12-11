package fr.ensitech.biblio.service;

import fr.ensitech.biblio.entity.User;
import fr.ensitech.biblio.repository.IUserRepository;
import fr.ensitech.biblio.security.PasswordSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void createUser(User user) throws Exception {

        String password = user.getPassword();
        System.out.println("Password : " + password);

        // Hashage avec Argon2id : considéré aujourd’hui comme l’un des meilleurs choix :
        // résistant aux attaques GPU, mémoire-dépendant, sécure contre side-channel.
        // il nécessite la dépendance BouncyCastle pour le support natif.
        String hash1 = PasswordSecurity.argon2idEncoder().encode(password);
        System.out.println("Hash 1 : " + hash1);
        boolean matches1 = PasswordSecurity.argon2idEncoder().matches(password, hash1);
        if (matches1) {
            System.out.println("  => Argon2id : Les deux mots de passe sont identiques");
        }

        // Hashage avec BCrypt : très robuste, plus léger qu’Argon2id, largement éprouvé
        String hash2 = PasswordSecurity.bcryptEncoder().encode(password);
        System.out.println("Hash 2 : " + hash2);
        boolean matches2 = PasswordSecurity.bcryptEncoder().matches(password, hash2);
        if (matches2) {
            System.out.println("  => BCrypt : Les deux mots de passe sont identiques");
        }

        //userRepository.save(user);
    }

    @Override
    public User getUserById(long id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<User> getUsersByBirthdate(Date dateInf, Date dateSup) throws Exception {

        return userRepository.findByBirthdateBetween(dateInf, dateSup);
    }
}
