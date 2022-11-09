package com.wilmart.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilmart.dto.UserDTO;
import com.wilmart.service.KafkaService;
import com.wilmart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaServiceImpl implements KafkaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "users")
    @SendTo
    public String reply(Object obj){
        logger.info(obj.toString());
        String test = "Server received topic users from client";

        return test;
    }

    @KafkaListener(topics = "get_list_users")
    @SendTo
    @Override
    public String getAllUser() throws JsonProcessingException {
        List<UserDTO> list = userService.getListUsers();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        return json;
    }

    @KafkaListener(topics = "create_user")
    @SendTo
    @Override
    public String create(Object obj) throws JsonProcessingException {
        UserDTO dto = objectMapper.convertValue(obj, UserDTO.class);
        UserDTO newDto = userService.create(dto);
        String jsonDto = objectMapper.writeValueAsString(newDto);
        return jsonDto;
    }

    @KafkaListener(topics = "update_user")
    @SendTo
    @Override
    public String update(Object obj) throws JsonProcessingException {
        UserDTO dto = objectMapper.convertValue(obj, UserDTO.class);
        UserDTO newDto = userService.update(dto);
        String jsonDto = objectMapper.writeValueAsString(newDto);
        return jsonDto;
    }

    @KafkaListener(topics = "delete_user")
    @SendTo
    @Override
    public Boolean delete(Object obj) {
        UserDTO dto = objectMapper.convertValue(obj, UserDTO.class);
        Boolean result = userService.delete(dto);
        return result;
    }

    @KafkaListener(topics = "get_info_user")
    @SendTo
    @Override
    public String findUserById(Object obj) throws JsonProcessingException {
        Integer userId = (Integer) obj;
        UserDTO newDto = userService.findUserById(userId);
        String jsonDto = objectMapper.writeValueAsString(newDto);
        return jsonDto;
    }

    @KafkaListener(topics = "find_user_by_fullname")
    @SendTo
    @Override
    public String findUserByFullName(Object obj) throws JsonProcessingException {
        String fullName = (String) obj;
        List<UserDTO> list = userService.findUserByFullName(fullName);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        return json;
    }

    @KafkaListener(topics = "find_user_by_email")
    @SendTo
    @Override
    public String findUserByEmail(Object obj) throws JsonProcessingException {
        String email = (String) obj;
        List<UserDTO> list = userService.findUserByEmail(email);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        return json;
    }

    @KafkaListener(topics = "find_user_by_department")
    @SendTo
    @Override
    public String findUserByDepartment(Object obj) throws JsonProcessingException {
        String department = (String) obj;
        List<UserDTO> list = userService.findUserByDepartment(department);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        return json;
    }

    @KafkaListener(topics = "find_user_by_gender")
    @SendTo
    @Override
    public String findUserByGender(Object obj) throws JsonProcessingException {
        String gender = (String) obj;
        List<UserDTO> list = userService.findUserByGender(gender);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        return json;
    }


}
