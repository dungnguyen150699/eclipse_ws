package com.ttc.bookmeetingroom.repository;

import com.ttc.bookmeetingroom.model.Booking;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
	   @Query(value = "select * from booking b where "
	            + "(:id is null or b.id = :id) "
	            + "and (:check_in is null or b.check_in >= :check_in) "
	            + "and (:check_out is null or b.check_out <= :check_out) "
	            + "and (:description is null or b.description like %:description%) "
	            + "and (:participants is null or b.participants = :participants) "
	            + "and (:title is null or b.title like %:title%)"
	            + "and (:employee_id is null or b.employee_id = :employee_id)"
	            + "and (:room_id is null or b.room_id = :room_id)", nativeQuery = true)
	    
	    Page<Booking> getAllByCondition(@Param("id") Integer id
	    		,@Param("check_in") Timestamp check_in, @Param("check_out") Timestamp check_out
	    		,@Param("description") String description, @Param("participants") Integer participants
	    		,@Param("title") String title,@Param("employee_id") Integer employee_id
	    		,@Param("room_id") Integer room_id,Pageable pageable);
	    
	    Page<Booking> findAll(Pageable pageable);
	    
	    @Modifying
		void deleteById(Integer id);

		Booking findById(Integer id);
}
