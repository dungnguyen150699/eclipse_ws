package dungnt_ptit.com.securitystandard.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class MapSupperClass {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "created_by",updatable = false)
    @JsonIgnore
    protected Long createdBy;

    @Column(name = "created_date",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    protected Date createdDate;

    @Column(name = "updated_by")
    protected Long updatedBy;

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    protected Date updatedDate;

//    @Column(name = "is_active")
//    @JsonIgnore
//    protected Integer isActive;
}
