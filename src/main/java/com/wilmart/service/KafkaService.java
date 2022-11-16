package com.wilmart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wilmart.dto.UserDTO;

import java.util.List;

public interface KafkaService {

    String getAllUser() throws JsonProcessingException;

    String create(Object obj) throws JsonProcessingException;

    String update(Object obj) throws JsonProcessingException;

    String delete(Object obj) throws JsonProcessingException;

    String findUserById(Object obj) throws JsonProcessingException;

    String findUserByFullName(Object obj) throws JsonProcessingException;

    String findUserByEmail(Object obj) throws JsonProcessingException;

    String findUserByDepartment(Object obj) throws JsonProcessingException;

    String findUserByGender(Object obj) throws JsonProcessingException;

}
