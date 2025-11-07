package com.example.filrouge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.filrouge.entities.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}