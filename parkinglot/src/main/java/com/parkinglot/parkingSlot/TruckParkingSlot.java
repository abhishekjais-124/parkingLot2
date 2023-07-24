package com.parkinglot.parkingSlot;

import com.parkinglot.vehicle.Truck;
import com.parkinglot.vehicle.Vehicle;

public class TruckParkingSlot extends ParkingSlot {

	public TruckParkingSlot(int slot_no, int floor_no) {
		super(slot_no, floor_no, "TRUCK");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSuitableFor(Vehicle vehicle) {
		return vehicle instanceof Truck;
	}

}
