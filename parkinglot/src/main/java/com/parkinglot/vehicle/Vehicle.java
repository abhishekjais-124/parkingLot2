package com.parkinglot.vehicle;

abstract public class Vehicle {

	protected String registrationNumber;
	protected String color;
	protected String type;
	
	
	public Vehicle(String registrationNumber, String color, String type) {
		this.registrationNumber = registrationNumber;
		this.color = color;
		this.type = type;
	}


	public String getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
