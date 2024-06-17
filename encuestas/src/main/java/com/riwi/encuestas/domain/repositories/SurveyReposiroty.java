package com.riwi.encuestas.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.encuestas.domain.entities.Survey;

//15
@Repository
public interface SurveyReposiroty extends JpaRepository <Survey,Integer>{
    
}
