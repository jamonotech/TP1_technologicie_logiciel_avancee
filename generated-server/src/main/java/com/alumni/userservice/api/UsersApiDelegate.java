package com.alumni.userservice.api;

import com.alumni.userservice.model.CreateUserRequest;
import com.alumni.userservice.model.ErrorResponse;
import org.springframework.lang.Nullable;
import java.util.UUID;
import com.alumni.userservice.model.UpdateUserRequest;
import com.alumni.userservice.model.User;
import com.alumni.userservice.model.UserPage;
import com.alumni.userservice.model.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link UsersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-23T09:01:43.370133340Z[Africa/Dakar]", comments = "Generator version: 7.24.0")
public interface UsersApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /users : Créer un profil utilisateur
     * Création d&#39;un profil métier après création de l&#39;utilisateur dans Keycloak. 
     *
     * @param createUserRequest  (required)
     * @return Profil créé (status code 201)
     *         or Requête invalide (status code 400)
     *         or Utilisateur déjà existant (status code 409)
     *         or Authentification requise ou jeton invalide (status code 401)
     *         or AccÃ¨s refusÃ© (status code 403)
     * @see UsersApi#createUser
     */
    default ResponseEntity<User> createUser(CreateUserRequest createUserRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"123e4567-e89b-12d3-a456-426614174000\", \"keycloakId\" : \"keycloak-abc-123\", \"email\" : \"user@example.com\", \"firstName\" : \"Jean\", \"lastName\" : \"Dupont\", \"phone\" : \"+33123456789\", \"photoUrl\" : \"https://example.com/photos/jean.jpg\", \"biography\" : \"Ancien élève passionné par l'informatique.\", \"birthDate\" : \"1980-05-15\", \"gender\" : \"FEMALE\", \"type\" : \"STUDENT\", \"status\" : \"ACTIVE\", \"address\" : { \"country\" : \"France\", \"city\" : \"Paris\", \"street\" : \"10 rue de la Paix\", \"postalCode\" : \"75002\" }, \"createdAt\" : \"2023-01-01T12:00:00Z\", \"updatedAt\" : \"2023-06-01T15:30:00Z\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/{userId} : Supprimer un utilisateur
     *
     * @param userId  (required)
     * @return Utilisateur supprimé (status code 204)
     *         or Requête invalide (status code 400)
     *         or Ressource introuvable (status code 404)
     *         or Authentification requise ou jeton invalide (status code 401)
     *         or AccÃ¨s refusÃ© (status code 403)
     * @see UsersApi#deleteUser
     */
    default ResponseEntity<Void> deleteUser(UUID userId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/me : Récupérer le profil de l&#39;utilisateur connecté
     *
     * @return Profil courant (status code 200)
     *         or Authentification requise ou jeton invalide (status code 401)
     *         or AccÃ¨s refusÃ© (status code 403)
     * @see UsersApi#getCurrentUser
     */
    default ResponseEntity<User> getCurrentUser() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"123e4567-e89b-12d3-a456-426614174000\", \"keycloakId\" : \"keycloak-abc-123\", \"email\" : \"user@example.com\", \"firstName\" : \"Jean\", \"lastName\" : \"Dupont\", \"phone\" : \"+33123456789\", \"photoUrl\" : \"https://example.com/photos/jean.jpg\", \"biography\" : \"Ancien élève passionné par l'informatique.\", \"birthDate\" : \"1980-05-15\", \"gender\" : \"FEMALE\", \"type\" : \"STUDENT\", \"status\" : \"ACTIVE\", \"address\" : { \"country\" : \"France\", \"city\" : \"Paris\", \"street\" : \"10 rue de la Paix\", \"postalCode\" : \"75002\" }, \"createdAt\" : \"2023-01-01T12:00:00Z\", \"updatedAt\" : \"2023-06-01T15:30:00Z\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/{userId} : Obtenir un utilisateur par ID
     *
     * @param userId  (required)
     * @return Utilisateur trouvé (status code 200)
     *         or Requête invalide (status code 400)
     *         or Ressource introuvable (status code 404)
     *         or Authentification requise ou jeton invalide (status code 401)
     *         or AccÃ¨s refusÃ© (status code 403)
     * @see UsersApi#getUserById
     */
    default ResponseEntity<User> getUserById(UUID userId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"123e4567-e89b-12d3-a456-426614174000\", \"keycloakId\" : \"keycloak-abc-123\", \"email\" : \"user@example.com\", \"firstName\" : \"Jean\", \"lastName\" : \"Dupont\", \"phone\" : \"+33123456789\", \"photoUrl\" : \"https://example.com/photos/jean.jpg\", \"biography\" : \"Ancien élève passionné par l'informatique.\", \"birthDate\" : \"1980-05-15\", \"gender\" : \"FEMALE\", \"type\" : \"STUDENT\", \"status\" : \"ACTIVE\", \"address\" : { \"country\" : \"France\", \"city\" : \"Paris\", \"street\" : \"10 rue de la Paix\", \"postalCode\" : \"75002\" }, \"createdAt\" : \"2023-01-01T12:00:00Z\", \"updatedAt\" : \"2023-06-01T15:30:00Z\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users : Rechercher les utilisateurs
     *
     * @param name  (optional)
     * @param email  (optional)
     * @param type  (optional)
     * @param page  (optional, default to 0)
     * @param size  (optional, default to 20)
     * @return Liste paginée des utilisateurs (status code 200)
     *         or Requête invalide (status code 400)
     *         or Authentification requise ou jeton invalide (status code 401)
     *         or AccÃ¨s refusÃ© (status code 403)
     * @see UsersApi#searchUsers
     */
    default ResponseEntity<UserPage> searchUsers(String name,
        String email,
        UserType type,
        Integer page,
        Integer size) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"content\" : [ { \"id\" : \"123e4567-e89b-12d3-a456-426614174000\", \"keycloakId\" : \"keycloak-abc-123\", \"email\" : \"user1@example.com\", \"firstName\" : \"Alice\", \"lastName\" : \"Martin\", \"phone\" : \"+33123456780\", \"photoUrl\" : \"https://example.com/photos/alice.jpg\", \"biography\" : \"Professeur de mathématiques.\", \"birthDate\" : \"1975-03-20\", \"gender\" : \"FEMALE\", \"type\" : \"TEACHER\", \"status\" : \"ACTIVE\", \"address\" : { \"country\" : \"France\", \"city\" : \"Lyon\", \"street\" : \"12 avenue des Champs\", \"postalCode\" : \"69000\" }, \"createdAt\" : \"2022-01-01T10:00:00Z\", \"updatedAt\" : \"2023-01-01T10:00:00Z\" }, { \"id\" : \"223e4567-e89b-12d3-a456-426614174001\", \"keycloakId\" : \"keycloak-def-456\", \"email\" : \"user2@example.com\", \"firstName\" : \"Bob\", \"lastName\" : \"Durand\", \"phone\" : \"+33123456781\", \"photoUrl\" : \"https://example.com/photos/bob.jpg\", \"biography\" : \"Étudiant en informatique.\", \"birthDate\" : \"1998-07-15\", \"gender\" : \"MALE\", \"type\" : \"STUDENT\", \"status\" : \"ACTIVE\", \"address\" : { \"country\" : \"France\", \"city\" : \"Marseille\", \"street\" : \"5 rue de la République\", \"postalCode\" : \"13000\" }, \"createdAt\" : \"2022-06-01T10:00:00Z\", \"updatedAt\" : \"2023-06-01T10:00:00Z\" } ], \"page\" : 0, \"size\" : 20, \"totalElements\" : 2, \"totalPages\" : 1, \"hasNext\" : false, \"hasPrevious\" : false }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /users/me : Modifier son propre profil
     *
     * @param updateUserRequest  (required)
     * @return Profil mis à jour (status code 200)
     *         or Requête invalide (status code 400)
     *         or Authentification requise ou jeton invalide (status code 401)
     *         or AccÃ¨s refusÃ© (status code 403)
     * @see UsersApi#updateCurrentUser
     */
    default ResponseEntity<User> updateCurrentUser(UpdateUserRequest updateUserRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"123e4567-e89b-12d3-a456-426614174000\", \"keycloakId\" : \"keycloak-abc-123\", \"email\" : \"user@example.com\", \"firstName\" : \"Jean\", \"lastName\" : \"Dupont\", \"phone\" : \"+33123456789\", \"photoUrl\" : \"https://example.com/photos/jean.jpg\", \"biography\" : \"Ancien élève passionné par l'informatique.\", \"birthDate\" : \"1980-05-15\", \"gender\" : \"FEMALE\", \"type\" : \"STUDENT\", \"status\" : \"ACTIVE\", \"address\" : { \"country\" : \"France\", \"city\" : \"Paris\", \"street\" : \"10 rue de la Paix\", \"postalCode\" : \"75002\" }, \"createdAt\" : \"2023-01-01T12:00:00Z\", \"updatedAt\" : \"2023-06-01T15:30:00Z\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /users/{userId} : Modifier un utilisateur
     *
     * @param userId  (required)
     * @param updateUserRequest  (required)
     * @return Utilisateur modifié (status code 200)
     *         or Requête invalide (status code 400)
     *         or Ressource introuvable (status code 404)
     *         or Authentification requise ou jeton invalide (status code 401)
     *         or AccÃ¨s refusÃ© (status code 403)
     *         or Conflit avec une ressource existante (status code 409)
     * @see UsersApi#updateUser
     */
    default ResponseEntity<User> updateUser(UUID userId,
        UpdateUserRequest updateUserRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"123e4567-e89b-12d3-a456-426614174000\", \"keycloakId\" : \"keycloak-abc-123\", \"email\" : \"user@example.com\", \"firstName\" : \"Jean\", \"lastName\" : \"Dupont\", \"phone\" : \"+33123456789\", \"photoUrl\" : \"https://example.com/photos/jean.jpg\", \"biography\" : \"Ancien élève passionné par l'informatique.\", \"birthDate\" : \"1980-05-15\", \"gender\" : \"FEMALE\", \"type\" : \"STUDENT\", \"status\" : \"ACTIVE\", \"address\" : { \"country\" : \"France\", \"city\" : \"Paris\", \"street\" : \"10 rue de la Paix\", \"postalCode\" : \"75002\" }, \"createdAt\" : \"2023-01-01T12:00:00Z\", \"updatedAt\" : \"2023-06-01T15:30:00Z\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestamp\" : \"2023-06-01T12:00:00Z\", \"status\" : 400, \"message\" : \"Requête invalide\", \"path\" : \"/users\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
