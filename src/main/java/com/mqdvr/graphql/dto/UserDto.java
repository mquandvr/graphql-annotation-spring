package com.mqdvr.graphql.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private String address;

    private Date birthday;

    private String remarks;

    private Boolean sex;

    private String role;

    private Boolean active;

    private Boolean deleted;

    private Long storeId;

    private String mobilePhone;

    private String createdBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;

    private String userType;
}
