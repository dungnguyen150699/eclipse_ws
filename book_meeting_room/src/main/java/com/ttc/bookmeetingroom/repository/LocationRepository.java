package com.ttc.bookmeetingroom.repository;

import com.ttc.bookmeetingroom.model.Location;
import com.ttc.bookmeetingroom.model.Room;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, String> {

        @Query(value = "select * from location l where "
                + "(:id is null  or l.id like  %:id%) "
                + "and (:name is null or l.name like %:name%)", nativeQuery = true )
        Page<Location> getAllByCondition(@Param("id") Integer id, @Param("name") String name, Pageable pageable);

        Location findById(Integer id);

        @Modifying
        void deleteById(Integer id);




}
