package com.riwi.encuestas.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//16
@Entity(name = "question")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", length = 11)
    private int idQuestion;
    @Column(name = "text", nullable = false)
    private String text;
    @Column(name = "type", nullable = false,length=50)
    private String type;
    @Column(name = "active", nullable = false)
    private boolean active;
}
