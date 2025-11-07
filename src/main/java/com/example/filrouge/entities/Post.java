package com.example.filrouge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "content", nullable = false, length = 500)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "likes_count", nullable = false)
    private Integer likesCount = 0;
}