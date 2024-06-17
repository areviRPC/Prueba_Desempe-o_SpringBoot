package com.riwi.encuestas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.encuestas.domain.entities.OptionQuestion;
//17
@Repository
public interface OptionQuestionRepository extends JpaRepository <OptionQuestion,Integer>{
    
}
