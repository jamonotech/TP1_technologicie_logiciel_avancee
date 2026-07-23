#!/usr/bin/env bash
# À exécuter depuis la racine du projet (là où se trouve pom.xml)
set -euo pipefail

BASE="src/main/java/com/alumni/userservice"
TEST_BASE="src/test/java/com/alumni/userservice"

echo "==> Suppression de l'ancien package com.example.demo"
rm -rf src/main/java/com/example
rm -rf src/test/java/com/example
rm -f src/main/resources/application.yaml

echo "==> Création de l'arborescence"
mkdir -p "$BASE/model" "$BASE/controller" "$BASE/store" "$BASE/config"
mkdir -p "$TEST_BASE"

# ---------------------------------------------------------------------------
# pom.xml
# ---------------------------------------------------------------------------
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/>
    </parent>

    <groupId>com.alumni</groupId>
    <artifactId>user-service-mock</artifactId>
    <version>1.0.0</version>
    <name>user-service-mock</name>
    <description>Mock server for the Alumni Platform User Service API (generated from user-service.yaml)</description>

    <properties>
        <java.version>21</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <finalName>user-service-mock</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
EOF

# ---------------------------------------------------------------------------
# application.properties
# ---------------------------------------------------------------------------
cat > src/main/resources/application.properties << 'EOF'
spring.application.name=user-service-mock
# Render fournit le port via la variable d'environnement PORT
server.port=${PORT:8080}
EOF

# ---------------------------------------------------------------------------
# UserServiceApplication.java
# ---------------------------------------------------------------------------
cat > "$BASE/UserServiceApplication.java" << 'EOF'
package com.alumni.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
EOF

# ---------------------------------------------------------------------------
# model/UserType.java
# ---------------------------------------------------------------------------
cat > "$BASE/model/UserType.java" << 'EOF'
package com.alumni.userservice.model;

public enum UserType {
    ADMIN, TEACHER, STUDENT, ALUMNI
}
EOF

# ---------------------------------------------------------------------------
# model/UserStatus.java
# ---------------------------------------------------------------------------
cat > "$BASE/model/UserStatus.java" << 'EOF'
package com.alumni.userservice.model;

public enum UserStatus {
    ACTIVE, INACTIVE, SUSPENDED
}
EOF

# ---------------------------------------------------------------------------
# model/Gender.java
# ---------------------------------------------------------------------------
cat > "$BASE/model/Gender.java" << 'EOF'
package com.alumni.userservice.model;

public enum Gender {
    MALE, FEMALE, OTHER
}
EOF

# ---------------------------------------------------------------------------
# model/Address.java
# ---------------------------------------------------------------------------
cat > "$BASE/model/Address.java" << 'EOF'
package com.alumni.userservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Address {

    @NotBlank
    @Size(min = 2, max = 100)
    private String country;

    @NotBlank
    @Size(min = 1, max = 100)
    private String city;

    @Size(max = 255)
    private String street;

    @Size(max = 20)
    private String postalCode;

    public Address() {
    }

    public Address(String country, String city, String street, String postalCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
EOF

# ---------------------------------------------------------------------------
# model/User.java
# ---------------------------------------------------------------------------
cat > "$BASE/model/User.java" << 'EOF'
package com.alumni.userservice.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class User {

    private UUID id;
    private String keycloakId;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String photoUrl;
    private String biography;
    private LocalDate birthDate;
    private Gender gender;
    private UserType type;
    private UserStatus status;
    private Address address;
    private Instant createdAt;
    private Instant updatedAt;

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
EOF

# ---------------------------------------------------------------------------
# model/CreateUserRequest.java
# ---------------------------------------------------------------------------
cat > "$BASE/model/CreateUserRequest.java" << 'EOF'
package com.alumni.userservice.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

    @NotBlank
    @Size(min = 1, max = 255)
    private String keycloakId;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    private String lastName;

    @Pattern(regexp = "^\\+?[1-9]\\d{7,14}$")
    private String phone;

    private UserType type;

    @Valid
    private Address address;

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
EOF

# ---------------------------------------------------------------------------
# model/UpdateUserRequest.java
# ---------------------------------------------------------------------------
cat > "$BASE/model/UpdateUserRequest.java" << 'EOF'
package com.alumni.userservice.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

    @Size(min = 1, max = 100)
    private String firstName;

    @Size(min = 1, max = 100)
    private String lastName;

    @Pattern(regexp = "^\\+?[1-9]\\d{7,14}$")
    private String phone;

    @Size(max = 2000)
    private String biography;

    private String photoUrl;

    @Valid
    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
EOF

# ---------------------------------------------------------------------------
# model/UserPage.java
# ---------------------------------------------------------------------------
cat > "$BASE/model/UserPage.java" << 'EOF'
package com.alumni.userservice.model;

import java.util.List;

public class UserPage {

    private List<User> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    public UserPage() {
    }

    public UserPage(List<User> content, int page, int size, long totalElements,
                     int totalPages, boolean hasNext, boolean hasPrevious) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public List<User> getContent() {
        return content;
    }

    public void setContent(List<User> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
EOF

# ---------------------------------------------------------------------------
# model/ErrorResponse.java
# ---------------------------------------------------------------------------
cat > "$BASE/model/ErrorResponse.java" << 'EOF'
package com.alumni.userservice.model;

import java.time.Instant;

public class ErrorResponse {

    private Instant timestamp;
    private int status;
    private String message;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(Instant timestamp, int status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
EOF

# ---------------------------------------------------------------------------
# store/UserStore.java
# ---------------------------------------------------------------------------
cat > "$BASE/store/UserStore.java" << 'EOF'
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
EOF

# ---------------------------------------------------------------------------
# controller/UsersController.java
# ---------------------------------------------------------------------------
cat > "$BASE/controller/UsersController.java" << 'EOF'
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
EOF

# ---------------------------------------------------------------------------
# config/GlobalExceptionHandler.java
# ---------------------------------------------------------------------------
cat > "$BASE/config/GlobalExceptionHandler.java" << 'EOF'
package com.alumni.userservice.config;

import com.alumni.userservice.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
                                                            HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .orElse("Requête invalide");

        ErrorResponse body = new ErrorResponse(Instant.now(), 400, message, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
EOF

# ---------------------------------------------------------------------------
# test class
# ---------------------------------------------------------------------------
cat > "$TEST_BASE/UserServiceApplicationTests.java" << 'EOF'
package com.alumni.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
EOF

# ---------------------------------------------------------------------------
# Dockerfile
# ---------------------------------------------------------------------------
cat > Dockerfile << 'EOF'
# --- Étape de build ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -q clean package -DskipTests

# --- Étape d'exécution ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/user-service-mock.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# ---------------------------------------------------------------------------
# .gitignore
# ---------------------------------------------------------------------------
cat > .gitignore << 'EOF'
target/
*.class
.idea/
*.iml
.DS_Store
EOF

echo "==> Terminé. Nouvelle arborescence :"
find src -type f | sort
echo
echo "Prochaine étape : mvn clean package  (ou ./mvnw clean package)"