package dungnt_ptit.com.securitystandard.pojo.dto;

import dungnt_ptit.com.securitystandard.pojo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private long id;

    private String name;

    private Set<UserDTO> users;
}
