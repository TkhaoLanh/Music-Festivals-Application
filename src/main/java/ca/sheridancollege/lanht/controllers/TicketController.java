package ca.sheridancollege.lanht.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.lanht.bean.Ticket;
import ca.sheridancollege.lanht.database.TicketRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor

public class TicketController {
	private TicketRepository ticketRepo;
	
	@GetMapping("/") //Request made for localhost:8080
	public String goHome() {
		return "rootpage.html";//Resource for the Response
	}
	
	@GetMapping("/add")
	public String goAddTicket(Model model) {
		
		model.addAttribute("ticket", new Ticket());
		return "addTicket.html";
	}
	
	@PostMapping("/add")
	public String processAddTicket(@ModelAttribute Ticket ticket, Model model) {
		ticketRepo.addTicket(ticket);
		
		return "redirect:/add";//sends status code 302
	}
	
	@GetMapping("/view")
	public String viewTicket(Model model) {
		model.addAttribute("tickets", ticketRepo.getTickets());
		return "view.html";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editTicket(@PathVariable int id, Model model) {
		Ticket t = ticketRepo.getTicketById(id);
		model.addAttribute("ticket",t);
		return "editTicket.html";
	}
	
	@PostMapping("/edit")
	public String processEditTicket(@ModelAttribute Ticket ticket, Model model) {
		ticketRepo.editTicket(ticket);
		
		return "redirect:/view";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTicket(@PathVariable int id, Model model) {
		ticketRepo.deleteTicketById(id);
		
		return "redirect:/view";
	}
	

	@GetMapping("/stats")
	public String viewStats(Model model) {
		model.addAttribute("max", ticketRepo.maxPrice());
		model.addAttribute("min", ticketRepo.minPrice());
		model.addAttribute("total", ticketRepo.totalFestivals());
		model.addAttribute("avg", ticketRepo.averagePrice());
		model.addAttribute("sum", ticketRepo.ticketAmount());
		
		return "stats.html";
	}



}
