package com.example.carparking.service;

import com.example.carparking.model.ParkLocation;
import com.example.carparking.model.ParkManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class BookParking {

    List<ParkLocation> list = new ArrayList<ParkLocation>();

    {
        ParkLocation parkLocation = new ParkLocation("S-1", "unallocated");
        ParkLocation parkLocation1 = new ParkLocation("S-2", "unallocated");
        ParkLocation parkLocation2 = new ParkLocation("S-3", "unallocated");
        ParkLocation parkLocation3 = new ParkLocation("S-4", "unallocated");
        ParkLocation parkLocation4 = new ParkLocation("S-5", "unallocated");
        ParkLocation parkLocation5 = new ParkLocation("S-6", "unallocated");
        ParkLocation parkLocation6 = new ParkLocation("S-7", "unallocated");
        ParkLocation parkLocation7 = new ParkLocation("S-8", "unallocated");
        ParkLocation parkLocation8 = new ParkLocation("S-9", "unallocated");
        ParkLocation parkLocation9 = new ParkLocation("S-10", "unallocated");

        list.add(parkLocation);
        list.add(parkLocation1);
        list.add(parkLocation2);
        list.add(parkLocation3);
        list.add(parkLocation4);
        list.add(parkLocation5);
        list.add(parkLocation6);
        list.add(parkLocation7);
        list.add(parkLocation8);
        list.add(parkLocation9);
    }

    Map<String, ParkManager> map = new ConcurrentHashMap<String, ParkManager>();


    public void addparking(ParkManager parkManager) {

        System.out.println("******Added Parking*********");
        System.out.println(parkManager);

        list.stream().forEach(e -> {
            if (e.getLocation().equalsIgnoreCase(parkManager.getLocation().getLocation())) {
                e.setStatus("allocated");
            }
        });

        map.put(parkManager.getCarNumber(), parkManager);

    }

    public void updateParking(ParkManager parkManager) {
        System.out.println("******Updated Parking*********");
        System.out.println(parkManager);

        map.put(parkManager.getCarNumber(), parkManager);

    }

    public void deallocateParking(ParkManager parkManager) {
        System.out.println("******Deleting Parking*********");
        System.out.println(parkManager);

        map.remove(parkManager);

        System.out.println("******Removed Parking*********");

    }

    public Map<String, ParkManager> getAllParkingDetails() {

        System.out.println("******Getting all Parking details*********");

        return map;
    }

    public ParkManager getParking(String carNumber) {

        return map.get(carNumber);

    }

    public List<ParkLocation> availableSlots() {

        return list.stream().filter(element -> element.getStatus().equalsIgnoreCase("unallocated")).collect(Collectors.toList());

    }

    public int size() {
        return map.size();
    }

}
