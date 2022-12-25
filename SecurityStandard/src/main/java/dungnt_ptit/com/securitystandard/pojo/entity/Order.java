package dungnt_ptit.com.securitystandard.pojo.entity;

import dungnt_ptit.com.securitystandard.ulti.constant.enums.ProcessOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
public class Order extends MapSupperClass {
    @Column(name = "date_order")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOrder;

    @Transient
    private ProcessOrder processOrder;

    @Column(name = "process")
    private Integer processValue;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(targetEntity = OrderDetail.class, mappedBy = "order",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE) // cascade phía trên chnsh là onUpdate
    private List<OrderDetail> orderDetails;

    @PrePersist // Hãy tận dụng jpa CallBack để set Enum => Save + Update
    @PreUpdate
    public void setProcessValue(){
        if(processOrder != null) {
            processValue = processOrder.getCode();
        };
    }

    @PostLoad
    public void setProcessEnum(){
        processOrder = ProcessOrder.of(processValue);
    }
}
