package com.wilmart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "department")
    private String department;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String year;

    @Column(name = "email")
    private String email;

    @Column(name = "mobilephone")
    private String mobilePhone;
}
