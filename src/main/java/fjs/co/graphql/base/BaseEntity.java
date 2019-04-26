package fjs.co.graphql.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "created_date", nullable = true)
    @JsonIgnore
    private Date createdDate;

    @Column(name = "created_by", nullable = true)
    @JsonIgnore
    private String createBy;

    @Column(name = "updated_date", nullable = true)
    @JsonIgnore
    private Date updatedDate;

    @Column(name = "updated_by", nullable = true)
    @JsonIgnore
    private String updateBy;
}
