package dungnt_ptit.com.securitystandard.ulti.common.mapper.mapStruct;

import dungnt_ptit.com.securitystandard.pojo.dto.RoleDTO;
import dungnt_ptit.com.securitystandard.pojo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {UserMapStruct.class})
public interface RoleMapStruct extends MapStructBasic<Role, RoleDTO> {
    @Override
    @Mapping(source = "users",target = "users",ignore = true)
    RoleDTO toDto(Role u);

    @Override
    @Mapping(source = "users",target = "users",ignore = true)
    Role toEntity(RoleDTO u);
}
