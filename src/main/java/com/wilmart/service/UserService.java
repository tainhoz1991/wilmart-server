package com.wilmart.service;

import com.wilmart.dto.UserBaseDTO;
import com.wilmart.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserBaseDTO dto);

    List<UserDTO> getListUsers();

    UserDTO update(UserDTO dto);

    UserDTO getUser(String id);

    boolean delete(Integer id);

    List<UserDTO> findUserByFullName(String fullName);

    List<UserDTO> findUserByDepartment(String department);

    List<UserDTO> findUserByGender(String gender);

    List<UserDTO> findUserByEmail(String email);

    void evictAllCaches();

}
