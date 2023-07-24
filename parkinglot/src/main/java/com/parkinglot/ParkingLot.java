package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import com.parkinglot.floor.Floor;
import com.parkinglot.parkingSlot.BikeParkingSlot;
import com.parkinglot.parkingSlot.CarParkingSlot;
import com.parkinglot.parkingSlot.ParkingSlot;
import com.parkinglot.parkingSlot.SlotFinderStrategy;
import com.parkinglot.parkingSlot.TruckParkingSlot;
import com.parkinglot.ticket.Ticket;
import com.parkinglot.ticket.TicketManager;
import com.parkinglot.vehicle.Vehicle;

public class ParkingLot {
	
	private String id;
	private List<Floor> floors;
	private int no_of_floors;
	protected int no_of_slots_per_floor;
	private SlotFinderStrategy slotFinderStrategy;
	
	public void createParkingLot(String id, int no_of_floors, int no_of_slots_per_floor,
			SlotFinderStrategy slotFinderStrategy) {

		this.id = id;
		this.no_of_floors = no_of_floors;
		this.no_of_slots_per_floor = no_of_slots_per_floor;
		this.slotFinderStrategy = slotFinderStrategy;
		
		this.floors = new ArrayList<>();
		for (int floorCount = 1; floorCount <= no_of_floors; floorCount++) {
			Floor floor = new Floor(floorCount, this.no_of_slots_per_floor);
			List<ParkingSlot> parkingSlots = generateParkingSlots(floorCount);
			floor.setParkingSlots(parkingSlots);
			this.floors.add(floor);
		}
	}
	
	public void addFloorsToParkingLot() {
		this.no_of_floors += 1;
		Floor floor = new Floor(this.no_of_floors, this.no_of_slots_per_floor);
		List<ParkingSlot> parkingSlots = generateParkingSlots(this.no_of_floors);
		floor.setParkingSlots(parkingSlots);
		this.floors.add(floor);
	}
	
	public void addParkingLotToFloor(Floor floor, ParkingSlot parkingSlot) {
		floor.addParkingSlot(parkingSlot);
		floor.setNoOfSlots(floor.getNoOfSlots() + 1);
	}
	
	public Ticket parkVehicle(Vehicle vehicle, TicketManager ticketManager) {
		ParkingSlot parkingSlot = this.slotFinderStrategy.findSlot(floors, vehicle);
		if (parkingSlot == null) {
			System.out.println("Parking Lot Full");
			return null;
		}
		
		parkingSlot.parkVehicle(vehicle);
		Ticket ticket = new Ticket(parkingSlot, vehicle, id, ticketManager);
		ticketManager.addTicket(ticket);
		return ticket;
	}
	
	public Vehicle unParkVehicle(String ticketId, TicketManager ticketManager) {
		//		 for (Map.Entry<String, Ticket> mapElement : ticketManager.getTickets().entrySet()) {
		//	            String key = mapElement.getKey();
		//	            Ticket value = mapElement.getValue();
		//	            System.out.println(key + " : " + value.getId());
		//	        }
		if (!ticketManager.isValidTicket(ticketId)) {
			System.out.println("Invalid Ticket");
			return null;
		}
		else {
			Ticket ticket = ticketManager.getTicketFromId(ticketId);
			if (ticket == null) {
				System.out.println("Invalid Ticket");
				return null;
			}
			Vehicle vehicle = ticket.getVehicle();
			ticket.getParkingSlot().removeVehicle();
			ticketManager.removeTicket(ticket);
			return vehicle;
		}
	}
	
	public void displayFreeCount(String vehicleType) {
		int count = 0;
		for (Floor floor: floors) {
			
			for (ParkingSlot parkingSlot: floor.getParkingSlots()) 
			{
				if (!parkingSlot.isOccupied() && parkingSlot.getVehicleType().equals(vehicleType.trim())) {
					count++;
				}
			}
			System.out.println("No. of free slots for "+ vehicleType +" on Floor "+ floor.getFloor_no() +": " +count);
			count = 0;
		}
	}
	
	public void displayFreeSlots(String vehicleType) {
		for (Floor floor: floors) {
			List<String> count = new ArrayList<>();
			for (ParkingSlot parkingSlot: floor.getParkingSlots()) {
				if (!parkingSlot.isOccupied() && parkingSlot.getVehicleType().equals(vehicleType.trim())) {
					count.add(Integer.toString(parkingSlot.getSlot_no()));
				}
			}
			System.out.println("Free slots for "+ vehicleType +" on Floor "+ floor.getFloor_no() +": " +String.join(",", count));
			
		}
	}
	
	public void displayOccupiedSlots(String vehicleType) {
		for (Floor floor: floors) {
			List<String> count = new ArrayList<>();
			for (ParkingSlot parkingSlot: floor.getParkingSlots()) {
				if (parkingSlot.isOccupied() && parkingSlot.getVehicleType().equals(vehicleType.trim()))  {
					count.add(Integer.toString(parkingSlot.getSlot_no()));
				}
			}
			System.out.println("Occupied slots for "+ vehicleType +" on Floor "+ floor.getFloor_no() +": " +String.join(",", count));
		}
	}
	
	private List<ParkingSlot> generateParkingSlots(int floorCount) {
		List<ParkingSlot> parkingSlots = new ArrayList<>();
		for (int slotCount = 1; slotCount <= this.no_of_slots_per_floor; slotCount++) {
			ParkingSlot parkingSlot;
			if (slotCount == 1) {
				parkingSlot = new TruckParkingSlot(slotCount, floorCount);	
			} else if (slotCount == 2 || slotCount == 3) {
				parkingSlot = new BikeParkingSlot(slotCount, floorCount);	
			} else {
			    parkingSlot = new CarParkingSlot(slotCount, floorCount);	
			}
			parkingSlots.add(parkingSlot);
		}
		return parkingSlots;
	}
	
   }
