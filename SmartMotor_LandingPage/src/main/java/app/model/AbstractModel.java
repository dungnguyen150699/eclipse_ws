package vn.viettel.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
public class AbstractModel implements Serializable {

//    @Column(name = "created_user", nullable = false, length = 50, updatable = false)
    @Column(name = "created_user")
    @JsonIgnore
    protected Integer createdUser;

    @Column(name = "created_datetime", updatable = false)
    @JsonIgnore
    protected LocalDateTime createdDatetime = LocalDateTime.now();

    @Column(name = "updated_user")
    @JsonIgnore
    protected Integer updatedUser;

    @Column(name = "updated_datetime")
    @JsonIgnore
    protected LocalDateTime updatedDateTime = LocalDateTime.now();

}
