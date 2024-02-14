package spring.mywardrobe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    public User(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void updateUser(String email, String password, String firstname, String lastname) {
        this.email = Objects.requireNonNullElse(email, this.email);
        this.password = Objects.requireNonNullElse(password, this.password);
        this.firstname = Objects.requireNonNullElse(firstname, this.firstname);
        this.lastname = Objects.requireNonNullElse(lastname, this.lastname);
    }

    public void delete() {
        isDeleted = true;
    }
}
