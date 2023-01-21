package com.greatlearning.tickettracker.service;

import java.util.List;

import com.greatlearning.tickettracker.entity.Tickets;

// Service interface with service methods initialized
public interface TtService {

	// Method to fetch all ticket details in database
	List<Tickets> getAllTickets();

	// Method to save a ticket detail in database
	Tickets saveTicket(Tickets tickets);

	// Method to fetch a ticket details by id in database
	Tickets getTicketById(Long id);

	// Method to update a ticket detail in database
	Tickets updateTicket(Tickets tickets);

	// Method to delete a ticket detail in database
	void deleteTicketById(Long id);

	// Method to search for a ticket by a keyword
	List<Tickets> getByKeyword(String keyword);

}