package com.riwi.encuestas.infrastructure.abstract_services;

import org.springframework.stereotype.Service;

import com.riwi.encuestas.api.dto.request.SurveyRequest;
import com.riwi.encuestas.api.dto.response.SurveyResponse;

// 15
@Service
public interface ISurveyService extends CrudService<SurveyRequest, SurveyResponse, Integer>{
    
}
