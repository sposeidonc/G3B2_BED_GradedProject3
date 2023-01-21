package com.greatlearning.tickettracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greatlearning.tickettracker.entity.Tickets;

//Repository class extending JpaRepository to enable CRUD operations

@Repository
public interface TicketTrackerRepository extends JpaRepository<Tickets, Long> {

	@Query(value = "select * from ticket t where t.ticket_title like %:keyword% or t.ticket_short_description like %:keyword%", nativeQuery = true)
	List<Tickets> findByKeyword(@Param("keyword") String keyword);
}
