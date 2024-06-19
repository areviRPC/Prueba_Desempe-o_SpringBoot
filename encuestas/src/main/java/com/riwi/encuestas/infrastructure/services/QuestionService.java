package com.riwi.encuestas.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;

import com.riwi.encuestas.api.dto.request.OptionQuestionRequest;
import com.riwi.encuestas.api.dto.response.SurveyResponse;
import com.riwi.encuestas.domain.entities.Survey;
import com.riwi.encuestas.domain.repositories.OptionQuestionRepository;
import com.riwi.encuestas.domain.repositories.SurveyReposiroty;
import com.riwi.encuestas.util.exceptions.IdNotFoundException;
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
    @Autowired
    private final OptionQuestionRepository optionQuestionRepository;
    @Autowired
    private final SurveyReposiroty surveyReposiroty;
    

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
        Survey survey= this.surveyReposiroty.findById(request.getSurvey()).orElseThrow(()->new IdNotFoundException("survey"));

        QuestionResponse response = this.entityToResponse(this.questionsRepository.save(question));
        List<OptionQuestion> listSave =  request.getOptiones().stream().map(op->this.createOptions(op,response.getIdQuestion())).collect(Collectors.toList());
        response.setOptionQuestions(this.optionsQuestionsResponseInQuestions(listSave));

        return response;
    }

    private  OptionQuestion createOptions(OptionQuestionRequest request, int id_question){
        Question question= this.find(id_question);
        OptionQuestion optionQuestion = new OptionQuestion();
        BeanUtils.copyProperties(request,optionQuestion);
        optionQuestion.setQuestion(question);
        return   this.optionQuestionRepository.save(optionQuestion);

    }


    // update
    @Override
    public QuestionResponse update(QuestionRequest request, Integer id) {
        Question question = this.find(id);
        if (request.getText()!=null)question.setText(request.getText());
        if (request.getType()!=null)question.setType(request.getType());

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
        System.out.println(question.getSurvey());
        BeanUtils.copyProperties(question, questionsResponse);
        SurveyResponse surveyResponse = new SurveyResponse();
        surveyResponse.setTitle(question.getSurvey().getTitle());
        surveyResponse.setDescription(question.getSurvey().getDescription());
        surveyResponse.setCreationDate(question.getSurvey().getCreationDate());
        surveyResponse.setActive(question.getSurvey().getUser().isActive());
        questionsResponse.setSurvey(surveyResponse);
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
