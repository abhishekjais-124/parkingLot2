package com.parkinglot.parkingSlot;

import com.parkinglot.vehicle.Vehicle;

abstract public class ParkingSlot {
	
	protected int slot_no;
	protected boolean isOccupied;
	protected Vehicle vehicle;
	protected int floor_no;
	protected String vehicleType;
	
	public ParkingSlot(int slot_no, int floor_no, String vehicleType) {
		this.slot_no = slot_no;
		this.floor_no = floor_no;
		this.isOccupied = false;
		this.vehicleType = vehicleType;
	}

	public abstract boolean isSuitableFor(Vehicle vehicle);
	
	public synchronized boolean parkVehicle(Vehicle vehicle) {
		if (isSuitableFor(vehicle) && !isOccupied) {
			this.vehicle = vehicle;
			this.isOccupied = true;
			return true;
		}
		return false;
	}
	
	public synchronized boolean removeVehicle() {
		if (isOccupied) {
			this.vehicle = null;
			this.isOccupied = false;
			return true;
		}
		return false;
	}

	public int getSlot_no() {
		return slot_no;
	}

	public void setSlot_no(int slot_no) {
		this.slot_no = slot_no;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public int getFloor_no() {
		return floor_no;
	}

	public void setFloor_no(int floor_no) {
		this.floor_no = floor_no;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public String toString() {
		return "ParkingSlot [slot_no=" + slot_no + ", isOccupied=" + isOccupied + ", vehicle=" + vehicle + ", floor_no="
				+ floor_no + ", vehicleType=" + vehicleType + "]";
	}
	
	

}
