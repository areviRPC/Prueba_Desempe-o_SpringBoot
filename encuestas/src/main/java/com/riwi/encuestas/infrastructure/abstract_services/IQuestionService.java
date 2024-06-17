package com.riwi.encuestas.infrastructure.abstract_services;

import com.riwi.encuestas.api.dto.request.QuestionRequest;
import com.riwi.encuestas.api.dto.response.QuestionResponse;

// 16
public interface IQuestionService extends CrudService <QuestionRequest, QuestionResponse, Integer>{
    
}
