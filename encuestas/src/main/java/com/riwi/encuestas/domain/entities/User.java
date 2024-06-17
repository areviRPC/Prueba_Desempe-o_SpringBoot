package com.riwi.encuestas.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// 9
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length = 11)    
    private int id;
    
    @Column(length = 100 , nullable = false)
    private String userName;

    @Column(length = 100 , nullable = false)
    private String password;

    @Column(length = 100 , nullable = false)
    private String email;

    @Column(length = 100 , nullable = false)
    private Boolean active;

    @Column(length = 100 , nullable = false)
    private String suverys;
}
