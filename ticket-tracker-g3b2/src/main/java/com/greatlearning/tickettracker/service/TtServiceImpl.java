package com.greatlearning.tickettracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.tickettracker.entity.Tickets;
import com.greatlearning.tickettracker.repository.TicketTrackerRepository;

@Service
public class TtServiceImpl implements TtService {
	private final TicketTrackerRepository ttRepository;

	public TtServiceImpl(TicketTrackerRepository ttRepository) {
		this.ttRepository = ttRepository;
	}

	// Method to fetch all ticket details in database
	@Override
	public List<Tickets> getAllTickets() {
		List<Tickets> tickets = this.ttRepository.findAll();
		return tickets;
	}

	// Method to save a ticket detail in database
	@Override
	public Tickets saveTicket(Tickets tickets) {
		return this.ttRepository.save(tickets);
	}

	// Method to fetch a ticket details by id in database
	@Override
	public Tickets getTicketById(Long id) {

		return this.ttRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Ticket id passed"));
	}

	// Method to update a ticket detail in database
	@Override
	public Tickets updateTicket(Tickets tickets) {
		return this.ttRepository.save(tickets);
	}

	// Method to delete a ticket detail in database
	@Override
	public void deleteTicketById(Long id) {
		this.ttRepository.deleteById(id);

	}

	// Method to search for a ticket by a keyword
	@Override
	public List<Tickets> getByKeyword(String keyword) {
		return ttRepository.findByKeyword(keyword);
	}

}
