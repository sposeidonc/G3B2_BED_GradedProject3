package com.greatlearning.tickettracker.contoller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.greatlearning.tickettracker.entity.Tickets;
import com.greatlearning.tickettracker.service.TtService;

//Controller class to set paths and send the requests to various views
@Controller
public class TtController {

	private final TtService ttService;

	public TtController(TtService ttService) {
		this.ttService = ttService;
	}

	// The home page or index page showing the list of tickets
	@GetMapping("/tickets")
	public String listTickets(Model model) {
		model.addAttribute("tickets", this.ttService.getAllTickets());
		return "tickets";
	}

	// The get method to view a particular ticket
	@GetMapping("/tickets/view/{id}")
	public String viewTicketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ttService.getTicketById(id));
		return "view_ticket";
	}

	// The get method to fetch ticket details by id which in turn returns the
	// editing view
	@GetMapping("/tickets/edit/{id}")
	public String editTicketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ttService.getTicketById(id));
		return "edit_ticket";
	}

	// The get method to return the view for creating a ticket
	@GetMapping("/tickets/new")
	public String createTicketForm(Model model) {
		// Creating ticket object to hold ticket form data
		Tickets tickets = new Tickets();
		model.addAttribute("ticket", tickets);
		return "create_ticket";
	}

	// The post method to save a ticket to the database and redirect to the home
	// page
	@PostMapping("/tickets")
	public String saveticket(@ModelAttribute("ticket") Tickets tickets) {
		ttService.saveTicket(tickets);
		return "redirect:/tickets";
	}

	// The get method to delete a ticket from the database and redirect to the
	// home page
	@GetMapping("/tickets/{id}")
	public String deleteTicket(@PathVariable Long id) {
		ttService.deleteTicketById(id);
		return "redirect:/tickets";
	}

	// The post method update an existing ticket to the database and redirect to
	// the home page
	@PostMapping("/tickets/{id}")
	public String updateTicket(@PathVariable Long id, @ModelAttribute("ticket") Tickets tickets, Model model) {

		Tickets existingTicket = ttService.getTicketById(id);
		existingTicket.setId(id);
		existingTicket.setTicketTitle(tickets.getTicketTitle());
		existingTicket.setTicketShortDescription(tickets.getTicketShortDescription());
		existingTicket.setTicketContent(tickets.getTicketContent());

		// Save updated ticket object
		ttService.updateTicket(existingTicket);
		return "redirect:/tickets";
	}

	// For the functionality of the search bar to search for tickets
	@GetMapping(path = { "/", "/search" })
	public String searchForm(Tickets tickets, Model model, String keyword) {
		if (keyword != null) {
			List<Tickets> list = ttService.getByKeyword(keyword);
			model.addAttribute("tickets", list);
		} else {
			List<Tickets> list = ttService.getAllTickets();
			model.addAttribute("list", list);
		}
		return "tickets";
	}

}
