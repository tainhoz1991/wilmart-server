package com.wilmart.service.impl;

import com.wilmart.dto.UserBaseDTO;
import com.wilmart.dto.UserDTO;
import com.wilmart.entity.User;
import com.wilmart.repository.UserRepository;
import com.wilmart.service.UserService;
import com.wilmart.service.mapstruct.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CacheManager cacheManager;

    @Override
    public UserDTO create(UserBaseDTO dto) {
        if (Objects.isNull(dto)){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setDepartment(dto.getDepartment());
        userDTO.setGender(dto.getGender());
        userDTO.setEmail(dto.getEmail());
        userDTO.setFullName(dto.getFullName());
        userDTO.setMobilePhone(dto.getMobilePhone());
        User entity = userRepository.save(userMapper.toEntity(userDTO));
//        evictAllCaches();
        return userMapper.toDto(entity);
    }

//    @Cacheable(cacheNames = "User")
    @Override
    public List<UserDTO> getListUsers() {
        List<User> userList = userRepository.findAll();
//        userList.stream().forEach(user -> cacheManager.getCache("User").put(user.getId(), user));
        return userMapper.toDto(userList);
    }

    @CachePut(cacheNames = "User", key = "#dto.id")
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

    @Cacheable(cacheNames = "User", key = "#id")
    @Override
    public UserDTO getUser(String id) {
        Optional<User> userOpt = userRepository.findById(Integer.valueOf(id));
        User user = Objects.isNull(userOpt) ? null : userOpt.get();

        return userMapper.toDto(user);
    }

    @CacheEvict(cacheNames = "User", key = "#id")
    @Override
    public boolean delete(Integer id) {
        Optional<User> userOpt = userRepository.findById(Integer.valueOf(id));
        User user = Objects.isNull(userOpt) ? null : userOpt.get();
        userRepository.delete(user);
        Optional<User> useCheck = userRepository.findById(Integer.valueOf(id));

        return useCheck.isEmpty();
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

    public void evictAllCaches() {
        cacheManager.getCache("User").clear();
    }
}
