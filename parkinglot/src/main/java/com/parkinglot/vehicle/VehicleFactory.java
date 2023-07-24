package com.parkinglot.vehicle;

public class VehicleFactory {
	
	public Vehicle getVehicle(String vehicleType, String regNo, String color) {
		Vehicle vehicle = null;
		switch(vehicleType) {
		    case "CAR":
		        vehicle = new Car(regNo, color);
		        break;
		    case "BIKE":
		        vehicle = new Bike(regNo, color);
		        break;
		    case "TRUCK":
		        vehicle = new Truck(regNo, color);
		        break;
		    default:
		        throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
		}
		return vehicle;
	}
	
}
