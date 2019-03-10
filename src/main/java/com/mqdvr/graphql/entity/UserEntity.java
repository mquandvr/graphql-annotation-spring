package com.mqdvr.graphql.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mqdvr.graphql.common.Role;
import com.mqdvr.graphql.entity.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String mobilePhone;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Column
    private String address;

    @Column
    private Date birthday;

    @Column
    private String remarks;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean sex;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean deleted;
}
