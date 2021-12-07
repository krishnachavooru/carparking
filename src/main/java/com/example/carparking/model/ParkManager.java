package com.example.carparking.model;

import java.time.LocalDateTime;


public class ParkManager {
	private String carNumber;
	private LocalDateTime currentDateTime;
	private String mobileNumber;
	private int numberOfHours;
	private ParkLocation location;
	
	public ParkManager(String carNumber, LocalDateTime currentDateTime, String mobileNumber, int numberOfHours,
			ParkLocation location) {
		super();
		this.carNumber = carNumber;
		this.currentDateTime = currentDateTime;
		this.mobileNumber = mobileNumber;
		this.numberOfHours = numberOfHours;
		this.location = location;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public LocalDateTime getCurrentDateTime() {
		return currentDateTime;
	}
	public void setCurrentDateTime(LocalDateTime currentDateTime) {
		this.currentDateTime = currentDateTime;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getNumberOfHours() {
		return numberOfHours;
	}
	public void setNumberOfHours(int numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	public ParkLocation getLocation() {
		return location;
	}
	public void setLocation(ParkLocation location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "ParkManager [carNumber=" + carNumber + ", currentDateTime=" + currentDateTime + ", mobileNumber="
				+ mobileNumber + ", numberOfHours=" + numberOfHours + ", location=" + location + "]";
	}
	
}
