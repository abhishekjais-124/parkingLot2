package com.parkinglot.ticket;

import java.util.HashMap;

public class TicketManagerImpl implements TicketManager{
	
	protected HashMap<String, Ticket> tickets;
	
	public TicketManagerImpl() {
		this.tickets = new HashMap<>();
	}

	@Override
	public void addTicket(Ticket ticket) {
		this.tickets.put(ticket.getId(), ticket);
	}

	@Override
	public void removeTicket(Ticket ticket) {
		if (isValidTicket(ticket.getId())) {
			this.tickets.remove(ticket.getId());
		}
	}

	@Override
	public boolean isValidTicketId(String ticketId) {
		String[] splittedId = ticketId.split("_");
		if (splittedId.length != 3 ) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidTicket(String ticketId) {
		if (!isValidTicketId(ticketId)) {
			return false;
		}
		return this.tickets.containsKey(ticketId);
	}

	@Override
	public Ticket getTicketFromId(String ticketId) {
		if (isValidTicket(ticketId)) {
			return this.tickets.get(ticketId);
		}
		return null;
	}

	@Override
	public HashMap<String, Ticket> getTickets() {
		return tickets;
	}
	

}
