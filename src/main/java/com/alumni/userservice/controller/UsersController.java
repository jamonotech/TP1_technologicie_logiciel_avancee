package com.alumni.userservice.controller;

import com.alumni.userservice.model.*;
import com.alumni.userservice.store.UserStore;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserStore store;

    public UsersController(UserStore store) {
        this.store = store;
    }

    // POST /users
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
        if (store.existsByEmail(request.getEmail()) || store.existsByKeycloakId(request.getKeycloakId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error(409, "Utilisateur déjà existant", "/users"));
        }

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setKeycloakId(request.getKeycloakId());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setType(request.getType() != null ? request.getType() : UserType.ALUMNI);
        user.setStatus(UserStatus.ACTIVE);
        user.setAddress(request.getAddress());
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        store.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // GET /users
    @GetMapping
    public ResponseEntity<UserPage> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) UserType type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        List<User> filtered = new ArrayList<>(store.all().values());

        if (name != null && !name.isBlank()) {
            String needle = name.toLowerCase();
            filtered.removeIf(u -> !(u.getFirstName().toLowerCase().contains(needle)
                    || u.getLastName().toLowerCase().contains(needle)));
        }
        if (email != null && !email.isBlank()) {
            filtered.removeIf(u -> !u.getEmail().equalsIgnoreCase(email));
        }
        if (type != null) {
            filtered.removeIf(u -> u.getType() != type);
        }

        int totalElements = filtered.size();
        int totalPages = size == 0 ? 0 : (int) Math.ceil((double) totalElements / size);
        int fromIndex = Math.min(page * size, totalElements);
        int toIndex = Math.min(fromIndex + size, totalElements);
        List<User> pageContent = filtered.subList(fromIndex, toIndex);

        UserPage result = new UserPage(
                pageContent, page, size, totalElements, totalPages,
                page + 1 < totalPages, page > 0
        );
        return ResponseEntity.ok(result);
    }

    // GET /users/me  (mock : pas de vraie session Keycloak, retourne un utilisateur fixe)
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(store.getCurrentUser());
    }

    // PATCH /users/me
    @PatchMapping("/me")
    public ResponseEntity<User> updateCurrentUser(@Valid @RequestBody UpdateUserRequest request) {
        User user = store.getCurrentUser();
        applyUpdate(user, request);
        return ResponseEntity.ok(user);
    }

    // GET /users/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable UUID userId) {
        User user = store.findById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error(404, "Ressource introuvable", "/users/" + userId));
        }
        return ResponseEntity.ok(user);
    }

    // PATCH /users/{userId}
    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable UUID userId, @Valid @RequestBody UpdateUserRequest request) {
        User user = store.findById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error(404, "Ressource introuvable", "/users/" + userId));
        }
        applyUpdate(user, request);
        return ResponseEntity.ok(user);
    }

    // DELETE /users/{userId}
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        User user = store.findById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error(404, "Ressource introuvable", "/users/" + userId));
        }
        store.delete(userId);
        return ResponseEntity.noContent().build();
    }

    private void applyUpdate(User user, UpdateUserRequest request) {
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getBiography() != null) user.setBiography(request.getBiography());
        if (request.getPhotoUrl() != null) user.setPhotoUrl(request.getPhotoUrl());
        if (request.getAddress() != null) user.setAddress(request.getAddress());
        user.setUpdatedAt(Instant.now());
    }

    private ErrorResponse error(int status, String message, String path) {
        return new ErrorResponse(Instant.now(), status, message, path);
    }
}
