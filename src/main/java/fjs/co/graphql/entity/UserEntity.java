package fjs.co.graphql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import fjs.co.graphql.base.BaseEntity;
import fjs.co.graphql.common.Role;

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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "USER_PASS", length = 100)
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    private String lastname;

    @Column(name = "EMAIL", length = 50)
    private String email;


    @Column(name = "TOKEN")
    private String token;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;


    @ManyToOne
    private RoleEntity role;

    /*
     * @ManyToMany(fetch = FetchType.EAGER)
     *
     * @JoinTable( name = "USER_ROLE", joinColumns = {@JoinColumn(name = "USER_ID",
     * referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name =
     * "ROLE_ID", referencedColumnName = "ID")})
     */
    @Transient
    private List<RoleEntity> roles = new ArrayList<RoleEntity>();

    @Transient
    private String beautifyRoleName;

    //Temp field used when add or update user
    @Transient
    private String userPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<RoleEntity> getRoles() {
        roles.clear();
        roles.add(role);
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getBeautifyRoleName() {
        if (role == null) {
            return beautifyRoleName;
        }
        if (role.getName() == Role.ROLE_ADMIN) {
            beautifyRoleName = "Admin";
        } else {
            beautifyRoleName = "User";
        }
        return beautifyRoleName;
    }

    public void setBeautifyRoleName(String beautifyRoleName) {
        this.beautifyRoleName = beautifyRoleName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}