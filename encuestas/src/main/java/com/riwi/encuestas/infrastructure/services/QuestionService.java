package com.riwi.encuestas.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.encuestas.api.dto.request.QuestionRequest;
import com.riwi.encuestas.api.dto.response.OptionsQuestionsResponseInQuestions;
import com.riwi.encuestas.api.dto.response.QuestionResponse;
import com.riwi.encuestas.domain.entities.OptionQuestion;
import com.riwi.encuestas.domain.entities.Question;
import com.riwi.encuestas.domain.repositories.QuestionRepository;
import com.riwi.encuestas.infrastructure.abstract_services.IQuestionService;
import com.riwi.encuestas.util.enums.SortType;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService{
    
    @Autowired
    private final  QuestionRepository questionsRepository;
    

    // get all 
    @Override
    public Page<QuestionResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.questionsRepository.findAll(pagination).map(this::entityToResponse);
    }

    // metodo find
    private Question find(Integer id) {
        return questionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));
    }

    // metdoo get by id
    @Override
    public QuestionResponse getById(Integer id) {
        Question question = find (id);
        return entityToResponse(question);
    }

    // create
    @Override
    public QuestionResponse create(QuestionRequest request) {
        Question question = this.requestToEntity(request);
        return this.entityToResponse(this.questionsRepository.save(question));
    }

    // update
    @Override
    public QuestionResponse update(QuestionRequest request, Integer id) {
        Question question = this.find(id);
        if (request.getText()!=null)question.setText(request.getText());
        if (request.getType()!=null)question.setType(request.getText());

        return this.entityToResponse(this.questionsRepository.save(question));
    }


    // delete
    @Override
    public void delete(Integer id) {
        this.questionsRepository.delete(this.find(id));
    }


    // DTOs
    private Question requestToEntity(QuestionRequest request) {
        Question question = new Question();
        BeanUtils.copyProperties(request, question);
        return question;
    }
    private QuestionResponse entityToResponse (Question question){
        QuestionResponse questionsResponse = new QuestionResponse();
        BeanUtils.copyProperties(question, questionsResponse);
        questionsResponse.setOptionQuestions(optionsQuestionsResponseInQuestions(question.getOptionQuestions()));      
        return questionsResponse;
    }

    // lista de opciones de la pregunta
    private List<OptionsQuestionsResponseInQuestions> optionsQuestionsResponseInQuestions(List<OptionQuestion> optionQuestions) {
        return optionQuestions.stream()
                .map(question -> {
                    OptionsQuestionsResponseInQuestions optionsQuestionsResponseInQuestions = new OptionsQuestionsResponseInQuestions();
                    optionsQuestionsResponseInQuestions.setIdOptionQuestion(question.getIdOptionQuestion());
                    optionsQuestionsResponseInQuestions.setIdQuestion(question.getQuestion().getIdQuestion());
                    optionsQuestionsResponseInQuestions.setText(question.getText());
                    optionsQuestionsResponseInQuestions.setActive(question.isActive());

                    
                    return optionsQuestionsResponseInQuestions;
                })
                .collect(Collectors.toList());
    }
    
}
