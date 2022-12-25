package dungnt_ptit.com.securitystandard.ulti.common.mapper.mapStruct;

import java.util.List;

//@Mapper(componentModel = "spring", uses = {})
interface MapStructBasic<E,D> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtos(List<E> entitys);

    List<E> toEntitys(List<D> dtos);
}
