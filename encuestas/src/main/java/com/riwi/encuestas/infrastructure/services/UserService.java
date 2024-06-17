package com.riwi.encuestas.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.encuestas.api.dto.request.UserRequest;
import com.riwi.encuestas.api.dto.response.SurveyResponseInUser;
import com.riwi.encuestas.api.dto.response.UserResponse;
import com.riwi.encuestas.domain.entities.Survey;
import com.riwi.encuestas.domain.entities.User;
import com.riwi.encuestas.domain.repositories.UserRepository;
import com.riwi.encuestas.infrastructure.abstract_services.IUserService;
import com.riwi.encuestas.util.enums.SortType;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserService implements IUserService{
    
    @Autowired
    private final UserRepository userRepository;

    // request to entity
    private User requestToEntity(UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }   
    
    // get all
    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.userRepository.findAll(pagination).map(this::entityToResponse);
    }

    // find 
        private User find(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    // Get by id
    @Override
    public UserResponse getById(Integer id){
            User user = find(id);
            return entityToResponse(user);
    }

    // create
    @Override
    public UserResponse create(UserRequest request) {
        User user = this.requestToEntity(request);
        return this.entityToResponse(this.userRepository.save(user));
    }

    // Update
    @Override
    public UserResponse update(UserRequest request, Integer id) {
        User user = this.find(id);
        if (request.getName()!=null) user.setUserName(request.getName());
        if (request.getEmail()!=null) user.setEmail(request.getEmail());
        if (request.getPassword()!=null)user.setPassword(request.getPassword());
        if (request.getPassword()!=null)user.setPassword(request.getPassword());
        user.setActive(request.isActive());
            
        return this.entityToResponse(this.userRepository.save(user));
    }

    // delete
    @Override
    public void delete(Integer id) {
        // Este metodo no es requerido para la entidad usuario
    }

    // Entity to response
    private UserResponse entityToResponse (User user){
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        userResponse.setSurveys(SurveyResponseInUser(user.getSurveys()));       
        return userResponse;
    }

    // lista de encuestas
    private List<SurveyResponseInUser> SurveyResponseInUser(List<Survey> surveys) {
        return surveys.stream()
                .map(enrollment -> {
                    SurveyResponseInUser surveyResponseInUser = new SurveyResponseInUser();
                    return surveyResponseInUser;
                })
                .collect(Collectors.toList());
    }
    
}
