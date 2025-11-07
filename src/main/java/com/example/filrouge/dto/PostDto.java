package com.example.filrouge.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.example.filrouge.entities.Post}
 */
@Data
public class PostDto implements Serializable {
    Integer id;
    String content;
    Integer likesCount;
    Integer userId;
}