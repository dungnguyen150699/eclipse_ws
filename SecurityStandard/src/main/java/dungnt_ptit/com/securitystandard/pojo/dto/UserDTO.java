package dungnt_ptit.com.securitystandard.pojo.dto;

import dungnt_ptit.com.securitystandard.pojo.entity.MapSupperClass;
import dungnt_ptit.com.securitystandard.pojo.entity.Order;
import dungnt_ptit.com.securitystandard.pojo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends MapSupperClass {

    private String username;

    private String password;

    private String phone;

    private Set<RoleDTO> roles;

    private List<OrderDTO> orders;
}
