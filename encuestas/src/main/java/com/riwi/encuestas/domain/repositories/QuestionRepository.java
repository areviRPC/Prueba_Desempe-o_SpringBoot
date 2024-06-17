package com.riwi.encuestas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.encuestas.domain.entities.Question;

//16
@Repository
public interface QuestionRepository extends JpaRepository <Question,Integer>{
    
}
