package org.hannaweldehana.claimsmanagment.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "administrator")  // Correct usage of the @Table annotation
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // Lombok will generate getters, setters, and toString() for all fields.
}
