package com.parkinglot.floor;

import java.util.ArrayList;
import java.util.List;

import com.parkinglot.parkingSlot.ParkingSlot;

public class Floor {
	
	protected int floor_no;
	protected int noOfSlots;
	protected List<ParkingSlot> parkingSlots;
	
	public Floor(int floor_no, int noOfSlots) {
		this.floor_no = floor_no;
		this.noOfSlots = noOfSlots;
		this.parkingSlots = new ArrayList<>();
	}
	
	public int getNoOfSlots() {
		return noOfSlots;
	}

	public void setNoOfSlots(int noOfSlots) {
		this.noOfSlots = noOfSlots;
	}

	public void addParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlots.add(parkingSlot);
	}
	
	public int getFloor_no() {
		return floor_no;
	}

	public void setFloor_no(int floor_no) {
		this.floor_no = floor_no;
	}

	public List<ParkingSlot> getParkingSlots() {
		return parkingSlots;
	}

	public void setParkingSlots(List<ParkingSlot> parkingSlots) {
		this.parkingSlots = parkingSlots;
	}
	
	
	
}
