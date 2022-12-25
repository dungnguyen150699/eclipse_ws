package vn.viettel.app.common.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CommonMapper {

    public static <T, E> E map(T source, Class<E> typeDestination) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(source, typeDestination);
    }

    public static <S, T> Page<T> toPage(Page<S> source, Class<T> targetClass, Pageable pageable) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return new PageImpl<>(
                source.stream().map(item -> modelMapper.map(item, targetClass)).collect(Collectors.toList()), pageable,
                source.getTotalElements());
    }

    public static <S, T> List<T> toList(List<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return source.stream().map(item -> modelMapper.map(item, targetClass)).collect(Collectors.toList());
    }

    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        BeanUtils.copyProperties(source, target, emptyNames.toArray(result));
    }
  
}
