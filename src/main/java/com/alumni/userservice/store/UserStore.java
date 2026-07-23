package com.alumni.userservice.store;

import com.alumni.userservice.model.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Store en mémoire simulant la base de données pour ce mock server.
 * Les données sont réinitialisées à chaque redémarrage de l'application.
 */
@Component
public class UserStore {

    private final Map<UUID, User> users = new ConcurrentHashMap<>();
    private UUID currentUserId;

    public UserStore() {
        seed();
    }

    private void seed() {
        User alice = new User();
        alice.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        alice.setKeycloakId("keycloak-abc-123");
        alice.setEmail("alice.martin@example.com");
        alice.setFirstName("Alice");
        alice.setLastName("Martin");
        alice.setPhone("+33123456780");
        alice.setPhotoUrl("https://example.com/photos/alice.jpg");
        alice.setBiography("Professeure de mathématiques.");
        alice.setBirthDate(LocalDate.of(1975, 3, 20));
        alice.setGender(Gender.FEMALE);
        alice.setType(UserType.TEACHER);
        alice.setStatus(UserStatus.ACTIVE);
        alice.setAddress(new Address("France", "Lyon", "12 avenue des Champs", "69000"));
        alice.setCreatedAt(Instant.parse("2022-01-01T10:00:00Z"));
        alice.setUpdatedAt(Instant.parse("2023-01-01T10:00:00Z"));
        users.put(alice.getId(), alice);

        User bob = new User();
        bob.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174001"));
        bob.setKeycloakId("keycloak-def-456");
        bob.setEmail("bob.durand@example.com");
        bob.setFirstName("Bob");
        bob.setLastName("Durand");
        bob.setPhone("+33123456781");
        bob.setPhotoUrl("https://example.com/photos/bob.jpg");
        bob.setBiography("Étudiant en informatique.");
        bob.setBirthDate(LocalDate.of(1998, 7, 15));
        bob.setGender(Gender.MALE);
        bob.setType(UserType.STUDENT);
        bob.setStatus(UserStatus.ACTIVE);
        bob.setAddress(new Address("France", "Marseille", "5 rue de la République", "13000"));
        bob.setCreatedAt(Instant.parse("2022-06-01T10:00:00Z"));
        bob.setUpdatedAt(Instant.parse("2023-06-01T10:00:00Z"));
        users.put(bob.getId(), bob);

        // Utilisateur "connecté" retourné par /users/me (mock : pas de vraie session Keycloak)
        this.currentUserId = alice.getId();
    }

    public Map<UUID, User> all() {
        return new LinkedHashMap<>(users);
    }

    public User findById(UUID id) {
        return users.get(id);
    }

    public boolean existsByEmail(String email) {
        return users.values().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public boolean existsByKeycloakId(String keycloakId) {
        return users.values().stream().anyMatch(u -> u.getKeycloakId().equals(keycloakId));
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public void delete(UUID id) {
        users.remove(id);
    }

    public User getCurrentUser() {
        return users.get(currentUserId);
    }
}
