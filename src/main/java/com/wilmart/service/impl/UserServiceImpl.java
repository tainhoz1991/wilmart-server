package com.wilmart.service.impl;

import com.wilmart.dto.UserDTO;
import com.wilmart.entity.User;
import com.wilmart.repository.UserRepository;
import com.wilmart.service.UserService;
import com.wilmart.service.mapstruct.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO create(UserDTO dto) {
        if (Objects.isNull(dto)){
            return null;
        }
        User entity = userRepository.save(userMapper.toEntity(dto));
        return userMapper.toDto(entity);
    }

    @Cacheable(value = "users")
    @Override
    public List<UserDTO> getListUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.toDto(userList);
    }

    @CachePut(value = "User", key = "#userId")
    @Override
    public UserDTO update(UserDTO dto) {
        if (Objects.isNull(dto)){
            return null;
        }
        Optional<User> uerOpt = userRepository.findById(dto.getId());
        User user = Objects.isNull(uerOpt) ? null : uerOpt.get();
        user.setEmail(dto.getEmail());
        user.setDepartment(dto.getDepartment());
        user.setGender(dto.getGender());
        user.setFullName(dto.getFullName());
        user.setYear(dto.getYear());
        user.setMobilePhone(dto.getMobilePhone());

        User newEntity = userRepository.save(user);

        return userMapper.toDto(newEntity);
    }

    @Cacheable(value = "User", key = "#userId")
    @Override
    public UserDTO getUser(String id) {
        Optional<User> userOpt = userRepository.findById(Integer.valueOf(id));
        User user = Objects.isNull(userOpt) ? null : userOpt.get();

        return userMapper.toDto(user);
    }

    @CacheEvict(value = "User", key = "#userId")
    @Override
    public boolean delete(UserDTO dto) {
        userRepository.delete(userMapper.toEntity(dto));
        Optional<User> user = userRepository.findById(dto.getId());

        return Objects.isNull(user);
    }

    @Override
    public List<UserDTO> findUserByFullName(String fullName) {
        List<User> userList = userRepository.findAllByFullNameIgnoreCase(fullName);

        return userMapper.toDto(userList);
    }

    @Override
    public List<UserDTO> findUserByDepartment(String department) {
        List<User> userList = userRepository.findAllByDepartmentIgnoreCase(department);

        return userMapper.toDto(userList);
    }

    @Override
    public List<UserDTO> findUserByGender(String gender) {
        List<User> userList = userRepository.findAllByGenderIgnoreCase(gender);

        return userMapper.toDto(userList);
    }

    @Override
    public List<UserDTO> findUserByEmail(String email) {
        List<User> userList = userRepository.findAllByEmailIgnoreCase(email);

        return userMapper.toDto(userList);
    }

    @Cacheable(value = "User")
    @Override
    public UserDTO findUserById(Integer id) {
        Optional<User> uerOpt = userRepository.findById(id);
        User user = Objects.isNull(uerOpt) ? null : uerOpt.get();
        return userMapper.toDto(user);
    }
}
