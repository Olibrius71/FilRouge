package com.example.filrouge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String lastName;

    @Column(name = "email", nullable = true)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String email;

    @Column(name = "password", nullable = true)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Post> posts = new LinkedHashSet<>();

}