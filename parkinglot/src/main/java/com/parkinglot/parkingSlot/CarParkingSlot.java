package com.parkinglot.parkingSlot;

import com.parkinglot.vehicle.Car;
import com.parkinglot.vehicle.Vehicle;

public class CarParkingSlot extends ParkingSlot {

	public CarParkingSlot(int slot_no, int floor_no) {
		super(slot_no, floor_no, "CAR");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSuitableFor(Vehicle vehicle) {
		return vehicle instanceof Car;
	}

}
