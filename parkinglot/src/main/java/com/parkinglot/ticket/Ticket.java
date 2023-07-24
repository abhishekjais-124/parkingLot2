package com.parkinglot.ticket;

import java.util.UUID;

import com.parkinglot.parkingSlot.ParkingSlot;
import com.parkinglot.vehicle.Vehicle;

public class Ticket {
	
	private String id;
	private ParkingSlot parkingSlot;
	private Vehicle vehicle;
	private String parkingLotId;
	private TicketManager ticketmanager;
	
	public Ticket(ParkingSlot parkingSlot, Vehicle vehicle,  String parkingLotId, TicketManager ticketManager) {
		this.parkingSlot = parkingSlot;
		this.vehicle = vehicle;
		this.parkingLotId = parkingLotId;
		this.id = generateTicketId();
		this.ticketmanager = ticketManager;
	}

	public boolean checkValidTicket() {
		if (!this.parkingSlot.isOccupied()) {
			return false;
		}
		return this.ticketmanager.isValidTicket(id);
	}
	
	private String generateTicketId() {
		return this.parkingLotId + "_" + this.parkingSlot.getFloor_no() + "_" + this.parkingSlot.getSlot_no();
	}

	public ParkingSlot getParkingSlot() {
		return parkingSlot;
	}

	public void setParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlot = parkingSlot;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(String parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
