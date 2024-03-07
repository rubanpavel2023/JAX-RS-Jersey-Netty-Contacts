package org.example.app.domain.user;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
// https://projectlombok.org/features/Data
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Override
    public String toString() {
        return "{" +
                "\"id\" : " + id +
                ", \"firstName\" : \"" + firstName + "\"" +
                ", \"lastName\" : \"" + lastName + "\"" +
                ", \"email\" : \"" + email + "\"" +
                "}";
    }
}
