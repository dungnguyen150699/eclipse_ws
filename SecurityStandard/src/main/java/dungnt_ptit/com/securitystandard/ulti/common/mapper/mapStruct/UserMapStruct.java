package dungnt_ptit.com.securitystandard.ulti.common.mapper.mapStruct;

import dungnt_ptit.com.securitystandard.pojo.dto.UserDTO;
import dungnt_ptit.com.securitystandard.pojo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {RoleMapStruct.class})
public interface UserMapStruct extends MapStructBasic<User, UserDTO> {

    @Override
//    @Mapping(source = "roles",target = "roles",ignore = true)
    @Mapping(source = "orders",target = "orders",ignore = true)
    UserDTO toDto(User u);

    @Override
    @Mapping(source = "roles",target = "roles",ignore = true)
    @Mapping(source = "orders",target = "orders",ignore = true)
    User toEntity(UserDTO u);
}
