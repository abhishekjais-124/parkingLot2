package com.parkinglot.parkingSlot;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.parkinglot.floor.Floor;
import com.parkinglot.vehicle.Vehicle;

public class LowestFloorFirstStrategy implements SlotFinderStrategy {

	@Override
	public ParkingSlot findSlot(List<Floor> floors, Vehicle vehicle) {
		Collections.sort(floors, Comparator.comparingInt(Floor::getFloor_no));
		ParkingSlot parkingSlotValue = null;
		for (Floor floor: floors) {
			List<ParkingSlot> parkingSlots = floor.getParkingSlots();
			for (ParkingSlot parkingSlot : parkingSlots) {
				if (!parkingSlot.isOccupied && parkingSlot.getVehicleType().equals(vehicle.getType())) {
					if (parkingSlotValue == null) {
						parkingSlotValue = parkingSlot;
					} else if (parkingSlotValue.slot_no > parkingSlot.slot_no){
						parkingSlotValue = parkingSlot;
					}
				}
			}
			if (parkingSlotValue != null) {
				return parkingSlotValue;
			}
		}
		return null;
	}

}
