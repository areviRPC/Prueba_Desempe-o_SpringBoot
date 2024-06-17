package com.riwi.encuestas.infrastructure.abstract_services;

import com.riwi.encuestas.api.dto.request.OptionQuestionRequest;
import com.riwi.encuestas.domain.entities.OptionsQuestionResponse;

// 17
public interface IOptionQuestionService extends CrudService<OptionQuestionRequest, OptionsQuestionResponse, Integer>{
    
}
