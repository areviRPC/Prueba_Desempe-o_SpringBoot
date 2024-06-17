package com.riwi.encuestas.infrastructure.abstract_services;

import org.springframework.stereotype.Service;

import com.riwi.encuestas.api.dto.request.UserRequest;
import com.riwi.encuestas.api.dto.response.UserResponse;

@Service
public interface IUserService extends CrudService <UserRequest, UserResponse, Integer> {
    
}
