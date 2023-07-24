package com.parkinglot.ticket;

import java.util.HashMap;
import java.util.List;

public interface TicketManager {

	 void addTicket(Ticket ticket);
	 void removeTicket(Ticket ticket);
	 boolean isValidTicketId(String ticketId);
	 boolean isValidTicket(String ticketId);
	 Ticket getTicketFromId(String ticketId);
	 HashMap<String, Ticket> getTickets();
	 
}
