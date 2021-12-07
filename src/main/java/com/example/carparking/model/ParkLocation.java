package com.example.carparking.model;

public class ParkLocation {
	private String location;
	private String status;
	public ParkLocation(String location, String status) {
		super();
		this.location = location;
		this.status = status;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ParkLocation [location=" + location + ", status=" + status + "]";
	}
	
}
