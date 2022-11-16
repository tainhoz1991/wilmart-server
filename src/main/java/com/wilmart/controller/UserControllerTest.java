package com.wilmart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilmart.dto.UserBaseDTO;
import com.wilmart.dto.UserDTO;
import com.wilmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable(value = "id") String id){
        UserDTO dto = userService.getUser(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() throws JsonProcessingException {
        List<UserDTO> userDTOList = userService.getListUsers();
//        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDTOList);
//        System.out.println(json);
        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserBaseDTO dto){
        UserDTO newDto = userService.create(dto);
        return ResponseEntity.ok(newDto);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto){
        UserDTO newDto = userService.update(dto);
        return ResponseEntity.ok(newDto);
    }

    @DeleteMapping
    public Boolean delete(@RequestBody UserDTO dto){
        return userService.delete(dto.getId());
    }

    @GetMapping("/fullnames")
    public ResponseEntity<List<UserDTO>> findByFullName(@RequestParam String fullName){
        List<UserDTO> userDTOList = userService.findUserByFullName(fullName);
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/emails")
    public ResponseEntity<List<UserDTO>> findByEmail(@RequestParam String email){
        List<UserDTO> userDTOList = userService.findUserByEmail(email);
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<UserDTO>> findByDepartment(@RequestParam String department){
        List<UserDTO> userDTOList = userService.findUserByDepartment(department);
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/genders")
    public ResponseEntity<List<UserDTO>> findByGender(@RequestParam String gender){
        List<UserDTO> userDTOList = userService.findUserByGender(gender);
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/clearCache")
    public String clearCache(){
        userService.evictAllCaches();
        return "success";
    }
}
