package fr.ensitech.biblio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class PasswordSecurity {

    /**
     * Ces paramètres sont sécurisés pour un serveur en 2025.
     * On peux augmenter la mémoire et les itérations selon la puissance machine.
     * @return l'algorithme encodeur de mot de passe
     */
    @Bean
    public static PasswordEncoder argon2idEncoder() {
        return new Argon2PasswordEncoder(
                16,   // saltLength
                32,   // hashLength
                1,    // parallelism (threads)
                1 << 14, // memory (16384 KB = 16MB)
                3     // iterations
        );
    }

    @Bean
    public static PasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder(12); // facteur de coût recommandé
    }
}
