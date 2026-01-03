package data;

import java.time.LocalDateTime;

public class Booking {
    private String id;
    private Customer customer;
    private Station fromStation;
    private Station toStation;
    private Trip trip;
    private LocalDateTime bookingTime;
    private String status;
    private LocalDateTime expirationTime;
    private double estimatePrice;

    public void addSeat(String seatId) {
        // Logic giữ chỗ
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationTime);
    }

    // Quan trọng: Phương thức chuyển đổi từ Booking sang Order
    public Order confirm() {
        Order newOrder = new Order();
        // Logic copy dữ liệu từ Booking sang Order...
        return newOrder;
    }
}