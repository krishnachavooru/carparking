package com.example.carparking;

import com.example.carparking.model.ParkLocation;
import com.example.carparking.model.ParkManager;
import com.example.carparking.service.BookParking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;


@SpringBootApplication
@EnableScheduling
public class CarparkingApplication implements CommandLineRunner {

	@Autowired
	BookParking bookParking;

	public static void main(String[] args) {
		SpringApplication.run(CarparkingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("**********Car Parking Application Started**********");
		Scanner input = new Scanner(System.in);

		int menu;
		do {
			System.out.println("WELCOME TO PARKING MANAGEMENT");
			System.out.println("1: To Check Available Slots");
			System.out.println("2: To Park Vehicle");
			System.out.println("3: To Extend Car Booking");
			System.out.println("4: Show All Perked Vehicles");
			System.out.println("0: To Exit");


			do {
				System.out.print("Enter your choice as specified Numbers: ");
				while (!input.hasNextInt()) {
					System.out.println("That's not a correct entry!");
					input.next();
				}
				menu = input.nextInt();
			} while (!(menu >= 0));

			System.out.println();

			switch (menu) {
				case 1: {
					System.out.println("Checking available slots...");
					bookParking.availableSlots().forEach(e->System.out.println(e));

					break;
				}
				case 2: {
					System.out.println("Enter slot which you want to book");
					String slot=input.next();
					ParkLocation parkLocation = new ParkLocation(slot,"allocated");

					Scanner scanner = new Scanner(System.in).useDelimiter("\n");
					System.out.println("Enter Car Number : ");
					String carNumber=scanner.next();
//                	scanner.close();

					System.out.println("Enter Mobile Number : ");
					String mobileNumber=input.next();

					Scanner sc = new Scanner(System.in);
					int noOfHours;
					do {
						System.out.println("Enter No. of hours (1 to 4): ");
						while (!sc.hasNextInt()) {
							System.out.println("That's not a correct entry!");
							sc.next();
						}
						noOfHours = sc.nextInt();
					} while (!(noOfHours > 0 && noOfHours < 5));

					ParkManager parkManager = new ParkManager(carNumber, LocalDateTime.now(), mobileNumber, noOfHours, parkLocation);
					bookParking.addparking(parkManager);
					System.out.println("*********Booked slots : " + parkLocation.getLocation());

					break;
				}
				case 3: {

					Scanner scanner = new Scanner(System.in).useDelimiter("\n");
					System.out.println("Enter Car Number : ");
					String carNumber=scanner.next();

					ParkManager parkManager1 = bookParking.getParking(carNumber);

					Scanner sc = new Scanner(System.in);
					int noOfHours;
					int totalHours;
					do {
						int pendinghrs = 4-parkManager1.getNumberOfHours();
						System.out.println("You have pending hours to book for:" +pendinghrs +" Enter Number of extending hours ");
						System.out.println();
						while (!sc.hasNextInt()) {
							System.out.println("That's not a correct entry!");
							sc.next();
						}
						noOfHours = sc.nextInt();
						totalHours = parkManager1.getNumberOfHours() + noOfHours;
					} while (!(totalHours < 5));

					parkManager1.setNumberOfHours(totalHours);
					bookParking.updateParking(parkManager1);
					System.out.println("**********Updated Number of hours**********");

					break;
				}

				case 4: {
					System.out.println("List of All Parked Cars : ");
					Map<String, ParkManager> map = bookParking.getAllParkingDetails();
					map.forEach((key, value)->{
						System.out.println(value);
					});

					break;
				}

				case 0: {
					System.out.println("\nThank you!\n");
					break;

				}
				default: {
					System.out.println("Invalid option!\n");
					break;
				}
			}
		} while (true);
	}

}
