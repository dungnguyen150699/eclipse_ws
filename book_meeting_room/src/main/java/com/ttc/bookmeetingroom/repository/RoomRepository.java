package com.ttc.bookmeetingroom.repository;

import com.ttc.bookmeetingroom.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoomRepository extends JpaRepository<Room, String> {

    @Query(value = "select * from room r where "
            + "(:id is null or r.id like %:id%) "
            + "and (:name is null or r.name like %:name%) "
            + "and (:location_id is null or r.location_id = :location_id) "
            + "and (:capacity_from is null or r.capacity <= :capacity_from) "
            + "and (:capacity_to is null or r.capacity <= :capacity_to) "
            + "and (:description is null or r.description like %:description%)", nativeQuery = true)
    Page<Room> getAllByCondition(@Param("id") Integer id, @Param("name") String name, @Param("location_id") Integer locationId, @Param("capacity_from")
            Integer capacityFrom, @Param("capacity_to") Integer capacityTo, @Param("description") String description, Pageable pageable);

    Room findById(Integer id);

    @Modifying
    void deleteById(Integer id);
}
