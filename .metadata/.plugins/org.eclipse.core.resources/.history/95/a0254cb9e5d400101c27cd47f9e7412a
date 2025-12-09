package data; // Hoặc package logic

import java.util.Hashtable;

public class FareCalculator {
    private Hashtable<Integer, Double> priceTable = new Hashtable<>();

    public FareCalculator() {
        // Khởi tạo bảng giá (Zone theo km)
        priceTable.put(1, 12000.0); // 0-5 km
        priceTable.put(2, 14000.0); // 5-10 km
        priceTable.put(3, 16000.0); // 10-15 km
        priceTable.put(4, 18000.0); // 15-20 km
        priceTable.put(5, 20000.0); // > 20 km
    }

    public double calculateFare(double distanceKm) {
        int zone = getZoneFromDistance(distanceKm);
        return priceTable.getOrDefault(zone, 10000.0);
    }

    private int getZoneFromDistance(double km) {
        if (km <= 0) return 0; // Đi trùng ga
        if (km <= 5) return 1;
        if (km <= 10) return 2;
        if (km <= 15) return 3;
        if (km <= 20) return 4;
        return 5;
    }
}