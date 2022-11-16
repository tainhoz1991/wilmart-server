package com.wilmart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDTO implements Serializable {

    private String fullName;

    private String department;

    private String gender;

    private String year;

    private String email;

    private String mobilePhone;

    @Override
    public String toString() {
        return "UserBaseDTO{" +
                "fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", year='" + year + '\'' +
                ", email='" + email + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                '}';
    }
}
