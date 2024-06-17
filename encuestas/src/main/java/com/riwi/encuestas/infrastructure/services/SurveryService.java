package com.riwi.encuestas.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.encuestas.api.dto.request.SurveyRequest;
import com.riwi.encuestas.api.dto.response.SurveyResponse;
import com.riwi.encuestas.domain.entities.Survey;
import com.riwi.encuestas.domain.repositories.SurveyReposiroty;
import com.riwi.encuestas.infrastructure.abstract_services.ISurveyService;
import com.riwi.encuestas.util.enums.SortType;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveryService implements ISurveyService {
    
    @Autowired
    private final SurveyReposiroty surveyRepository;

    
    // entitytoresponse
    private Survey requestToEntity(SurveyRequest request) {
        Survey survey = new Survey();
        BeanUtils.copyProperties(request, survey);
        return survey;
    }
    
    // get all 
    @Override
    public Page<SurveyResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.surveyRepository.findAll(pagination).map(this::entityToResponse);
    }

    // metodo find 
        private Survey find(Integer id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El id de la encuesta no ha sido encontrado: " + id));
    }

    // get by id
    @Override
    public SurveyResponse getById(Integer id) {
        Survey survey = find(id);
        return entityToResponse(survey);
    }

    // crear
    @Override
    public SurveyResponse create(SurveyRequest request) {

        Survey survey = this.requestToEntity(request);
        return this.entityToResponse(this.surveyRepository.save(survey));
    }

    // Actualizar
    @Override
    public SurveyResponse update(SurveyRequest request, Integer id) {
        Survey survey = this.find(id);

        if (request.getTitle()!=null)survey.setTitle(request.getTitle());
        if (request.getDescription()!=null)survey.setDescription(request.getDescription());
        if (request.getCreationDate()!=null)survey.setCreationDate(request.getCreationDate());
        survey.setActive(request.isActive()); 
        return this.entityToResponse(this.surveyRepository.save(survey));
    }

    // borrar
    @Override
    public void delete(Integer id) {
        this.surveyRepository.delete(this.find(id));
    }

        private SurveyResponse entityToResponse (Survey survey){
        SurveyResponse surveyResponse = new SurveyResponse();
        BeanUtils.copyProperties(survey, surveyResponse);      
        return surveyResponse;
    }
    
}
