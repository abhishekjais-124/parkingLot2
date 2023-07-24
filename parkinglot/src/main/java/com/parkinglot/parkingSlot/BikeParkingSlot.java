package com.parkinglot.parkingSlot;

import com.parkinglot.vehicle.Bike;
import com.parkinglot.vehicle.Vehicle;

public class BikeParkingSlot extends ParkingSlot {

	public BikeParkingSlot(int slot_no, int floor_no) {
		super(slot_no, floor_no, "BIKE");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSuitableFor(Vehicle vehicle) {
		return vehicle instanceof Bike;
	}

}
