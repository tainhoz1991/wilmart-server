package com.wilmart.repository;

import com.wilmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByFullNameIgnoreCase(String fullName);

    List<User> findAllByDepartmentIgnoreCase(String department);

    List<User> findAllByGenderIgnoreCase(String gender);

    List<User> findAllByEmailIgnoreCase(String email);
}
