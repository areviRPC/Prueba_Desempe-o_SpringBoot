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

//17
@Entity(name = "optionQuestion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_question_id", length = 11)
    private int idOptionQuestion;

    @Column(name = "text", nullable = false)
    private String text;
    
    @Column(name = "active", nullable = false)
    private boolean active;
}
