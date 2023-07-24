package com.parkinglot;

import java.util.Scanner;

import com.parkinglot.parkingSlot.LowestFloorFirstStrategy;
import com.parkinglot.parkingSlot.SlotFinderStrategy;
import com.parkinglot.ticket.Ticket;
import com.parkinglot.ticket.TicketManager;
import com.parkinglot.ticket.TicketManagerImpl;
import com.parkinglot.vehicle.Vehicle;
import com.parkinglot.vehicle.VehicleFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkinglotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkinglotApplication.class, args);
		
		ParkingLot parkingLot = new ParkingLot();
		TicketManager ticketManager = new TicketManagerImpl();
        try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
			    String line = scanner.nextLine();
			    String[] parts = line.split(" ");
			    String command = parts[0];

			    switch (command) {
			        case "create_parking_lot": {
			            String parkingLotId = parts[1];
			            int noOfFloors = Integer.parseInt(parts[2]);
			            int noOfSlotsPerFloor = Integer.parseInt(parts[3]);
			            SlotFinderStrategy slotFinderStrategy = new LowestFloorFirstStrategy();
			            parkingLot.createParkingLot(parkingLotId, noOfFloors, noOfSlotsPerFloor, slotFinderStrategy);
			            System.out.println("Created parking lot with " + noOfFloors + " floors and " + noOfSlotsPerFloor + " slots per floor");
			            break;
			        }
			        case "park_vehicle": {
			            String vehicleType = parts[1];
			            String regNo = parts[2];
			            String color = parts[3];
			            VehicleFactory vehicleFactory = new VehicleFactory();
			            Vehicle vehicle = vehicleFactory.getVehicle(vehicleType, regNo, color);
			            Ticket ticket = parkingLot.parkVehicle(vehicle, ticketManager);
			            if (ticket != null) {
			            	System.out.println("Parked vehicle. Ticket ID: " + ticket.getId());
			            }
			            break;
			        }
			        case "unpark_vehicle": {
			            String ticketId = parts[1];
			            Vehicle vehicle = parkingLot.unParkVehicle(ticketId, ticketManager);
			            if (vehicle != null) {
			            	System.out.println("Unparked vehicle with Registration Number: " + vehicle.getRegistrationNumber() + " and Color: " + vehicle.getColor());
			            }
			            break;
			        }
			        case "display": {
			            String displayType = parts[1];
			            String vehicleType = parts[2];
			            switch (displayType) {
			                case "free_count": {
			                	parkingLot.displayFreeCount(vehicleType);
			                	break;
			                }
			                case "free_slots": {
			                	parkingLot.displayFreeSlots(vehicleType);
			                	break;
			                }
			                case "occupied_slots": {
			                	parkingLot.displayOccupiedSlots(vehicleType);
			                	break;
			                }
			                default: {
			                    System.out.println("Invalid command");
			                }
			            }
			            break;
			        }
			        case "exit": {
			            System.exit(0);
			        }
			        default: {
			            System.out.println("Invalid command");
			        }
			    }
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}


// INPUT

//create_parking_lot PR1234 2 6
//display free_count CAR
//display free_count BIKE
//display free_count TRUCK
//display free_slots CAR
//display free_slots BIKE
//display free_slots TRUCK
//display occupied_slots CAR
//display occupied_slots BIKE
//display occupied_slots TRUCK
//park_vehicle CAR KA-01-DB-1234 black
//park_vehicle CAR KA-02-CB-1334 red
//park_vehicle CAR KA-01-DB-1133 black
//park_vehicle CAR KA-05-HJ-8432 white
//park_vehicle CAR WB-45-HO-9032 white
//park_vehicle CAR KA-01-DF-8230 black
//park_vehicle CAR KA-21-HS-2347 red
//display free_count CAR
//display free_count BIKE
//display free_count TRUCK
//unpark_vehicle PR1234_2_5
//unpark_vehicle PR1234_2_5
//unpark_vehicle PR1234_2_7
//display free_count CAR
//display free_count BIKE
//display free_count TRUCK
//display free_slots CAR
//display free_slots BIKE
//display free_slots TRUCK
//display occupied_slots CAR
//display occupied_slots BIKE
//display occupied_slots TRUCK
//park_vehicle BIKE KA-01-DB-1541 black
//park_vehicle TRUCK KA-32-SJ-5389 orange
//park_vehicle TRUCK KL-54-DN-4582 green
//park_vehicle TRUCK KL-12-HF-4542 green
//display free_count CAR
//display free_count BIKE
//display free_count TRUCK
//display free_slots CAR
//display free_slots BIKE
//display free_slots TRUCK
//display occupied_slots CAR
//display occupied_slots BIKE
//display occupied_slots TRUCK
//exit
//
//OUTPUT :
//	Created parking lot with 2 floors and 6 slots per floor
//	No. of free slots for CAR on Floor 1: 3
//	No. of free slots for CAR on Floor 2: 3
//	No. of free slots for BIKE on Floor 1: 2
//	No. of free slots for BIKE on Floor 2: 2
//	No. of free slots for TRUCK on Floor 1: 1
//	No. of free slots for TRUCK on Floor 2: 1
//	Free slots for CAR on Floor 1: 4,5,6
//	Free slots for CAR on Floor 2: 4,5,6
//	Free slots for BIKE on Floor 1: 2,3
//	Free slots for BIKE on Floor 2: 2,3
//	Free slots for TRUCK on Floor 1: 1
//	Free slots for TRUCK on Floor 2: 1
//	Occupied slots for CAR on Floor 1: 
//	Occupied slots for CAR on Floor 2: 
//	Occupied slots for BIKE on Floor 1: 
//	Occupied slots for BIKE on Floor 2: 
//	Occupied slots for TRUCK on Floor 1: 
//	Occupied slots for TRUCK on Floor 2: 
//	Parked vehicle. Ticket ID: PR1234_1_4
//	Parked vehicle. Ticket ID: PR1234_1_5
//	Parked vehicle. Ticket ID: PR1234_1_6
//	Parked vehicle. Ticket ID: PR1234_2_4
//	Parked vehicle. Ticket ID: PR1234_2_5
//	Parked vehicle. Ticket ID: PR1234_2_6
//	Parking Lot Full
//	No. of free slots for CAR on Floor 1: 0
//	No. of free slots for CAR on Floor 2: 0
//	No. of free slots for BIKE on Floor 1: 2
//	No. of free slots for BIKE on Floor 2: 2
//	No. of free slots for TRUCK on Floor 1: 1
//	No. of free slots for TRUCK on Floor 2: 1
//	Unparked vehicle with Registration Number: WB-45-HO-9032 and Color: white
//	Invalid Ticket
//	Invalid Ticket
//	No. of free slots for CAR on Floor 1: 0
//	No. of free slots for CAR on Floor 2: 1
//	No. of free slots for BIKE on Floor 1: 2
//	No. of free slots for BIKE on Floor 2: 2
//	No. of free slots for TRUCK on Floor 1: 1
//	No. of free slots for TRUCK on Floor 2: 1
//	Free slots for CAR on Floor 1: 
//	Free slots for CAR on Floor 2: 5
//	Free slots for BIKE on Floor 1: 2,3
//	Free slots for BIKE on Floor 2: 2,3
//	Free slots for TRUCK on Floor 1: 1
//	Free slots for TRUCK on Floor 2: 1
//	Occupied slots for CAR on Floor 1: 4,5,6
//	Occupied slots for CAR on Floor 2: 4,6
//	Occupied slots for BIKE on Floor 1: 
//	Occupied slots for BIKE on Floor 2: 
//	Occupied slots for TRUCK on Floor 1: 
//	Occupied slots for TRUCK on Floor 2: 
//	Parked vehicle. Ticket ID: PR1234_1_2
//	Parked vehicle. Ticket ID: PR1234_1_1
//	Parked vehicle. Ticket ID: PR1234_2_1
//	Parking Lot Full
//	No. of free slots for CAR on Floor 1: 0
//	No. of free slots for CAR on Floor 2: 1
//	No. of free slots for BIKE on Floor 1: 1
//	No. of free slots for BIKE on Floor 2: 2
//	No. of free slots for TRUCK on Floor 1: 0
//	No. of free slots for TRUCK on Floor 2: 0
//	Free slots for CAR on Floor 1: 
//	Free slots for CAR on Floor 2: 5
//	Free slots for BIKE on Floor 1: 3
//	Free slots for BIKE on Floor 2: 2,3
//	Free slots for TRUCK on Floor 1: 
//	Free slots for TRUCK on Floor 2: 
//	Occupied slots for CAR on Floor 1: 4,5,6
//	Occupied slots for CAR on Floor 2: 4,6
//	Occupied slots for BIKE on Floor 1: 2
//	Occupied slots for BIKE on Floor 2: 
//	Occupied slots for TRUCK on Floor 1: 1
//	Occupied slots for TRUCK on Floor 2: 1
//
