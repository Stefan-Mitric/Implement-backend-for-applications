# *Backend für Applikationen realisieren / Implement-backend-for-applications*

# Inhaltsverzeichnis
- [Einleitung](#einleitung)
- [Analyse](#analyse)
- [Anforderungen](#anforderungen)
- [Arbeitsjournal](#arbeitsjournal)
- [Passworter](#Passworter)
- [Endpoints](#endpoints)
- [OpenAPI](#openapi)
- [Fazit](#fazit)
- [Quellenverzeichnis](#quellenverzeichnis)

## Einleitung

Sie müssen eine API zur Verwaltung von Produkten eines Online-Shops erstellen. Diese muss die nötigen Endpoints zur Verfügung stellen, um damit Produkte und Kategorien in einer Datenbank zu erstellen, lesen, ändern und löschen. Die Endpoints müssen dem REST-Standard entsprechen.

Das Frontend für das Web wird im Rahmen eines späteren Projekts entwickelt. Daher muss die API unbedingt sauber strukturiert und gut dokumentiert sein.

Die Datenbank müssen Sie selbst mit den nötigen Tabellen und Eigenschaften einrichten. Die Datenstruktur wird hierzu vorgegeben (siehe unten). Alle Eigenschaften beider Typen (mit Ausnahme der IDs) müssen durch die API korrekt verwaltet werden können. Anfrage und Antwort müssen in korrektem JSON strukturiert werden.

Alle Endpoints (ausser dies wird anderweitig entkräftet) der API müssen mittels JSON Web Token geschützt werden. Dafür muss es allerdings einen einzigen Endpoint zur Authentifizierung geben, der ohne Token erreichbar ist. Dieser Endpoint nimmt einen Benutzernamen und ein Passwort entgegen und gibt ein valides JWT zurück, das dann zur Authentifizierung von allen anderen Endpoints verwendet wird.


## Analyse
### Anforderungen
Hier werden die funktionalen und nichtfunktionalen Anforderungen an die API aufgelistet.

Funktional

1. Ein nicht authentifizierter Benutzer kann sich authentifizieren

2. Ein nicht authentifizierter Benutzer kann ein Konto erstellen

3. Ein authentifizierter Benutzer mit Administrationsrechte kann einen Benutzer zum Administrator befördern

4. Mittels einem Database Seeder werden initiale Daten in die Datenbank geladen

5. Ein authentifizierter Benutzer mit Administrationsrechte kann Produkte erstellen

6. Ein authentifizierter und nicht authentifizierter Benutzer kann Produkte einsehen

7. Ein authentifizierter Benutzer mit Administrationsrechte kann Produkte bearbeiten

8. Ein authentifizierter Benutzer mit Administrationsrechte kann Produkte löschen

9. Ein authentifizierter Benutzer und nicht authentifizierter Benutzer kann alle Produkte auflisten

10. Ein authentifizierter Benutzer mit Administrationsrechte kann Produktkategorien erstellen

11. Ein authentifizierter und nicht authentifizierter Benutzer kann Produktkategorien einsehen

12. Ein authentifizierter Benutzer mit Administrationsrechte kann Produktkategorien bearbeiten

13. Ein authentifizierter Benutzer mit Administrationsrechte kann Produktkategorien löschen

14. Ein authentifizierter Benutzer und nicht authentifizierter Benutzer kann alle Produktkategorien auflisten

15. Ein authentifizierter Benutzer und nicht authentifizierter Benutzer kann alle Produkte einer Produktekategorie auflisten

Nichtfunktional

1. Die Applikation erfüllt die REST-Richtlinien (Insbesondere sind die Endpoints einheitlich und sinnvoll zu benennen)

2. Die Datenbankanbindung erfolgt mittels Spring JDBC

3. Die Authentifizierung für die geschützten Endpoints wird mittels JWT umgesetzt

4. Es muss stets ein Benutzer mit Administratorenrechte im System geben

5. Passwörter werden mit einem starken Verschlüsslungsalgorithmus geschützt

Dokumentation

1. Die allgemeinen formalen Anforderungen (Schriftgrösse, Formalität, Gliederung, etc...) müssen beachtet werden

2. Es muss ein Arbeitsjournal mit der Auflistung der Tätigkeiten und der Arbeitszeit sowie mit einer Reflexion für jeden Arbeitstag geführt werden

3. Alle Endpoints der API müssen in der Dokumentation detailliert aufgelistet werden

4. Alle Endpoints der API müssen zudem mit einer OpenAPI-Dokumentation vollständig beschrieben sein

5. Es muss ein einheitliches Namensschema für die API-Parameter sowie für Datenbanktabellen und -spalten schriftlich definiert werden

## Arbeitsjournal

### Tag 1
#### Aktivität:
- Java Primer und Java Spring Boot angeschaut,installiert und in Betrieb genommen.
- RestController erstellet.
- Dependency hinzugefügt.
- MariaDB Anbindung erstellt.
  
#### Reflextion:
- Alles verlief Reibunglose ohne fehler.

---

### Tag 2
#### Aktivität:
- Implementieren von Tabellen und Relationen in Java Spring Boot.
- Repository-Interface erstellt für = User,Product und Category
- erbungen erstellt das von JpaRepository CrudRepository oder PagingAndSortingRepository verwaltet wird.

#### Reflextion:
- Alles verlief Reibunglose ohne fehler.

---

### Tag 3
#### Aktivität:
- Service_interface erstellt.
- Service-Implementierung implementiert.
- Dependency Injection (@Autowired) erstellt.

#### Reflextion:
- Alles verlief Reibunglose ohne fehler.

---

### Tag 4
#### Aktivität:
- Neue Dependency eingefügt.
- Angefangen die Endpoints zu Dokumentieren.

DTO Dokumentation:
```java
@Data
@Schema(description = "DTO für die Detailansicht einer ToDo-Liste") 
public class ToDoListDetailDto {

    @NotNull 
    @Schema(description = "Einzigartige Identifikationsnummer der ToDo-Liste", example = "1") 
    private Long id;

    @NotNull 
    @Schema(description = "Titel der ToDo-Liste", example = "Einkaufsliste") 
    private String title;

    @NotNull 
    @Schema(description = "Liste der ToDos in dieser ToDo-Liste") 
    private List<ToDoShowDto> toDos;

    public Long getId() {
        return id; 
    }

    public void setId(Long id) {
        this.id = id; 
    }

    public String getTitle() {
        return title; 
    }

    public void setTitle(String title) {
        this.title = title; 
    }

    public List<ToDoShowDto> getToDos() {
        return toDos; 
    }

    public void setToDos(List<ToDoShowDto> toDos) {
        this.toDos = toDos; 
    } 
}

```

---
### Tag 5
#### Aktivität:
- Java Spring Security hizugefügt.
- JJWT und Javax.xml.bind. Dependencies hinzugefügt.
```java
<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.1</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version> <!-- Use the latest version -->
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

| Komponente             | Beschreibung                                                                                                                                                                                                                                                                                                                                                                          |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `AuthController`       | Stellt einen Endpoint zur Verfügung (z.B. `/auth/login`), der ein `LoginRequestDto` akzeptiert.                                                                                                                                                                                                                                                                                      |
| `LoginRequestDto`      | Ein Data Transfer Object (DTO), das aus zwei Properties besteht: E-Mail/Passwort oder Username/Passwort.                                                                                                                                                                                                                                                                             |
| `MyUserPrincipal`      | Erbt von `UserDetails` für die Java Spring Security Integration und repräsentiert das Benutzerobjekt, das dem SecurityContext übergeben wird. Enthält die Annotation `@Data` und implementiert die `UserDetails` Schnittstelle.                                                                                                                                                      |
| `JwtRequestFilter`     | Erbt von `OncePerRequestFilter`. Verantwortlich für das Auslesen des JWT aus dem Authorization-Header, Überprüfung der Token-Validität, Benutzerladen mittels `UserService` und Zusammenstellung des `UserPrincipal` Objekts.                                                                                                                                                       |
| `SecurityConfiguration`| Konfiguriert die Sicherheitseinstellungen mit der Annotation `@Configuration`. Enthält den `JwtRequestFilter` als `@Autowired` Bean. In der `filterChain` Methode werden Anpassungen wie das Implementieren des `JwtRequestFilter`, das Deaktivieren von CSRF und httpBasic sowie das Freigeben/Sperren von Endpoints vorgenommen.                                                        |
| `UserService`          | Bietet Methoden, um Benutzer anhand von E-Mail-Adresse oder anderen Claims zu laden. Wichtige Methoden sind `getUserWithCredentials`, das ein `LoginRequestDto` gegen einen Benutzer eintauscht, und `findUserByEmail`, das einen Benutzer aufgrund der E-Mail-Adresse lädt.                                                                                                          |
| `TokenWrapper`         | Ein DTO, das den JWT beinhaltet und bei `/auth/login` zurückgegeben wird. Besteht aus einem einzigen String `token`.                                                                                                                                                                                                                                                                  |
| `OpenApiConfig`        | Konfiguration für OpenAPI/Swagger, die es ermöglicht, den Bearer Token in der Swagger-UI zu verwenden. Definiert ein `OpenAPI` Bean mit Sicherheitsschemen für Bearer Authentication.                                                                                                                                                                                                 |
| `TokenService`         | Wird verwendet, um JWTs zu generieren, indem Benutzerinformationen als Token-Inhalte hinzugefügt werden. Bietet die Methode `generateToken`, die den Token als String zurückgibt und über `setClaim` zusätzliche Inhalte (payload) zum Token hinzufügen kann.                                                                                                                         |
                                                                                                                                            
#### Reflextion:
- Alles verlief Reibunglose ohne fehler.
---

### Tag 6
#### Aktivität:
- Alles Verloren weil ich das ganze projekt gelöscht habe und es nicht auf GitHub hatte.
- Von vorne angefangen.
- 
  #### Reflextion:
- Immer auf GitHub hochladen damit ich einen Backup habbe.
  
---
### Tag 7
#### Aktivität:
- Angefangen mit der Dokumentation.
  
## Passworter

Wie es funktioniert

Verschlüsselung: Wenn ein Benutzer ein Passwort erstellt oder ändert, wird dieses Passwort durch einen Algorithmus geschickt, der es in einen neuen, unleserlichen Text verwandelt. Dieser Vorgang heißt "Hashing".

Speichern: Dieser unleserliche Text, also der Hash, wird dann in der Datenbank gespeichert, nicht das eigentliche Passwort.

Validierung: Wenn sich der Benutzer anmelden will, wird das eingegebene Passwort erneut gehasht und mit dem in der Datenbank gespeicherten Hash verglichen. Stimmen beide überein, ist die Anmeldung erfolgreich.

Warum ist das so?

Sicherheit: Wenn jemand unbefugt Zugang zur Datenbank bekommt, sieht er nur die unleserlichen Hashes und nicht die eigentlichen Passwörter.

Einweg-Hashing: Ein guter Hashing-Algorithmus ist so gestaltet, dass man den ursprünglichen Text nicht aus dem Hash ableiten kann. Das macht es sicherer.

Verifikation trotz Unleserlichkeit

Auch wenn man den Hash kennt, kann man daraus nicht das Originalpasswort ableiten. Aber man kann ein eingegebenes Passwort durch den gleichen Hashing-Prozess schicken und prüfen, ob der resultierende Hash dem in der Datenbank gespeicherten Hash entspricht. Wenn ja, muss das eingegebene Passwort korrekt sein.

In Spring

In Spring kann man den BCryptPasswordEncoder verwenden, um Passwörter zu hashen und zu validieren.
```java
// Hashing
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode("plainPassword");
// Validierung
        boolean isPasswordMatch = encoder.matches("plainPassword",
hashedPassword);
```

## Endpoints

## OpenAPI

## Fazit

## Quellenverzeichnis
