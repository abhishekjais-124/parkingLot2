package com.parkinglot.parkingSlot;

import java.util.List;

import com.parkinglot.floor.Floor;
import com.parkinglot.vehicle.Vehicle;

public interface SlotFinderStrategy {
	ParkingSlot findSlot(List<Floor> floors, Vehicle vehicle);
}
