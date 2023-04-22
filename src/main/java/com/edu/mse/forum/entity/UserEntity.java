package com.edu.mse.forum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity extends BaseEntity {

    @Column
    // TODO: make unique
    private String email;

    @Column
    private String role;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private Date dateOfBirth;

    @Column
    @OneToMany(mappedBy = "userEntity")
    private List<ReplyEntity> replies;

    @Column
    @OneToMany(mappedBy = "userEntity")
    private List<TopicEntity> topics;
}
