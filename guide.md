# Guide TP1 — User Service Mock (Alumni Platform)

Ce guide résume les étapes réalisées pour répondre aux 3 exigences du TP :
1. Générer un mock serveur backend à partir de `user-service.yaml` (Swagger Codegen / OpenAPI Generator)
2. Déployer ce mock quelque part (Render)
3. Mettre à jour le fichier de spécifications avec l'URL du serveur déployé

---

## 1. Génération du mock server

### 1.1 Outil utilisé

[`openapi-generator-cli`](https://openapi-generator.tech/) (successeur maintenu de Swagger Codegen),
générateur cible : **`spring`** (Spring Boot, Java).

### 1.2 Commande de génération

Exécutée à la racine du projet, à côté de `user-service.yaml` :

```bash
openapi-generator-cli generate \
  -i user-service.yaml \
  -g spring \
  -o generated-server \
  --additional-properties=useSpringBoot3=true,interfaceOnly=false,delegatePattern=true,basePackage=com.alumni.userservice,apiPackage=com.alumni.userservice.api,modelPackage=com.alumni.userservice.model,groupId=com.alumni,artifactId=user-service-generated
```

**Explication des options :**

| Option | Rôle |
|---|---|
| `-i user-service.yaml` | fichier de spécification OpenAPI source |
| `-g spring` | générateur cible : Spring (Java) |
| `-o generated-server` | dossier de sortie du projet généré |
| `useSpringBoot3=true` | génère du code compatible Spring Boot 3 (namespace `jakarta.*`) |
| `interfaceOnly=false` | génère des controllers concrets (pas seulement des interfaces) |
| `delegatePattern=true` | sépare le controller HTTP (généré, à ne pas toucher) de la logique métier, à écrire dans une classe `*ApiDelegateImpl` |
| `basePackage` / `apiPackage` / `modelPackage` | organisation des packages Java générés |

### 1.3 Résultat de la génération

L'outil a produit un projet Maven complet dans `generated-server/` :

```
generated-server/
├── pom.xml
├── src/main/java/com/alumni/userservice/
│   ├── OpenApiGeneratorApplication.java     # point d'entrée Spring Boot
│   ├── RFC3339DateFormat.java
│   ├── api/
│   │   ├── UsersApi.java                    # interface annotée @RequestMapping
│   │   ├── UsersApiController.java          # controller HTTP généré
│   │   ├── UsersApiDelegate.java            # interface à implémenter (logique métier)
│   │   └── ApiUtil.java
│   └── model/
│       ├── User.java, Address.java, CreateUserRequest.java, ...
├── src/main/java/org/openapitools/configuration/
│   ├── SpringDocConfiguration.java          # config Swagger UI (springdoc)
│   ├── HomeController.java
│   └── EnumConverterConfiguration.java
├── src/main/resources/
│   ├── application.properties
│   └── openapi.yaml                         # copie de la spec, servie par l'app
└── src/test/java/.../OpenApiGeneratorApplicationTests.java
```

Chaque opération de la spec (`createUser`, `searchUsers`, `getUserById`, `updateUser`,
`deleteUser`, `getCurrentUser`, `updateCurrentUser`) a été retrouvée et traduite en
méthode Java dans `UsersApiDelegate`.

> Par défaut, un projet généré avec `delegatePattern=true` répond **501 Not Implemented**
> sur chaque endpoint tant qu'on n'a pas fourni sa propre implémentation
> (`@Component` implémentant `UsersApiDelegate`). C'est le comportement normal d'un stub.

### 1.4 Remarque réseau rencontrée

Au lancement, l'outil a affiché :
```
Unable to query repository, because of: "getaddrinfo ENOTFOUND central.sonatype.com".
Return default versions instead.
```
Ce n'est **pas une erreur bloquante** : c'est juste une vérification de mise à jour de
version qui échoue faute de réseau à ce moment précis ; l'outil est retombé sur la
version locale déjà installée (`7.24.0`) et a généré le projet normalement.

---

## 2. Test en local

### 2.1 Lancer le serveur généré

```bash
cd generated-server
mvn clean spring-boot:run
```

Le serveur écoute par défaut sur `http://localhost:8080`.

### 2.2 Tester les endpoints (curl)

```bash
# Créer un utilisateur
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"keycloakId":"kc-1","email":"a@b.com","firstName":"A","lastName":"B"}'

# Rechercher / lister (paginé)
curl "http://localhost:8080/users?page=0&size=20"

# Récupérer un utilisateur par ID
curl http://localhost:8080/users/<uuid>

# Profil de l'utilisateur "connecté" (mock, pas de vraie session Keycloak)
curl http://localhost:8080/users/me

# Modifier un utilisateur
curl -X PATCH http://localhost:8080/users/<uuid> \
  -H "Content-Type: application/json" -d '{"firstName":"Nouveau"}'

# Supprimer un utilisateur
curl -X DELETE http://localhost:8080/users/<uuid>
```

### 2.3 Documentation en ligne (Swagger UI généré automatiquement)

Le générateur `spring` embarque `springdoc-openapi` par défaut
(voir `SpringDocConfiguration.java`). Une fois le serveur lancé :

- **Swagger UI** : `http://localhost:8080/swagger-ui/index.html`
- **Spec JSON** : `http://localhost:8080/v3/api-docs`
- **Spec YAML** : `http://localhost:8080/v3/api-docs.yaml`

Documentation officielle de l'outil de génération :
- Générateur `spring` : https://openapi-generator.tech/docs/generators/spring/
- Vue d'ensemble de l'outil : https://openapi-generator.tech/docs/usage

---

## 3. Déploiement sur Render

### 3.1 Préparation

- Le projet a été packagé avec un `Dockerfile` (build multi-stage Maven → JRE),
  compatible avec le mode **Docker** de Render.
- `application.properties` lit le port depuis la variable d'environnement fournie
  par Render : `server.port=${PORT:8080}`.

### 3.2 Étapes suivies

1. Poussé le projet (dossier généré ou stub écrit à la main, selon la version retenue) dans un dépôt Git.
2. Sur [render.com](https://render.com) : **New +** → **Web Service**.
3. Connexion du dépôt GitHub.
4. Environnement : **Docker** (Render détecte automatiquement le `Dockerfile`).
5. Déploiement lancé.

### 3.3 URL obtenue

```
https://tp1-technologicie-logiciel-avancee.onrender.com
```

### 3.4 Vérification du déploiement

À exécuter (le service Render peut prendre ~30-50s à se réveiller sur le plan
gratuit après une période d'inactivité — *cold start*) :

```bash
curl -i https://tp1-technologicie-logiciel-avancee.onrender.com/users
curl -i https://tp1-technologicie-logiciel-avancee.onrender.com/users/me
curl -i https://tp1-technologicie-logiciel-avancee.onrender.com/swagger-ui/index.html
```

> Vérification à faire toi-même : je n'ai pas pu tester cette URL depuis mon
> environnement (accès réseau limité à une liste de domaines autorisés,
> qui n'inclut pas `onrender.com`). Confirme que les trois commandes
> ci-dessus renvoient bien un code `200` (ou `501` si le delegate n'est pas
> encore implémenté — dans ce cas c'est le controller généré qui répond,
> ce qui prouve que le déploiement fonctionne).

---

## 4. Mise à jour du fichier de spécifications

Le fichier `user-service.yaml` a été complété avec une entrée `servers`
supplémentaire pointant vers le déploiement Render :

```yaml
servers:
  - description: Local development
    url: 'http://localhost:8080'
  - description: Production
    url: 'https://api.example.com/user-service'
  - description: SwaggerHub API Auto Mocking
    url: 'https://virtserver.swaggerhub.com/uasz-77e/user-service/1.0.0'
  - description: Mock server (Render)
    url: 'https://tp1-technologicie-logiciel-avancee.onrender.com'
```

---

## 5. Récapitulatif des livrables

| Livrable | Description |
|---|---|
| `user-service.yaml` | spec OpenAPI mise à jour avec l'URL Render |
| `generated-server/` | projet généré par `openapi-generator-cli -g spring` |
| `user-service-mock/` (variante alternative) | stub Spring Boot écrit à la main, même contrat d'API, en cas de besoin de comparaison |
| `Dockerfile` | build/run du service pour déploiement Docker sur Render |
| URL Render | https://tp1-technologicie-logiciel-avancee.onrender.com |
