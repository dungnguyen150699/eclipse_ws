package dungnt_ptit.com.securitystandard.pojo.dto;

import dungnt_ptit.com.securitystandard.pojo.entity.MapSupperClass;
import dungnt_ptit.com.securitystandard.ulti.constant.enums.ProcessOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends MapSupperClass {

    private Date dateOrder;

    private ProcessOrder processOrder;

    private UserDTO user;

    private List<OrderDetailDTO> orderDetails;
}
