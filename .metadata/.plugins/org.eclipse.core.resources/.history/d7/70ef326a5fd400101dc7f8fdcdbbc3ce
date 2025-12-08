package data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

// Lớp Khách hàng
class Customer {
    private String id;
    private String name;
    private String email;
    private String phone;
    private List<Order> orders;

    public void updateContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public void setOrder(Order order) {
        // Logic thêm order
    }
}

// Lớp Vé (Ticket)
class Ticket {
    private String id;
    private ScheduleDetail scheduleDetail;
    private Customer customer;
    private String seatNumber;
    private double price;

    public String getInfo() {
        return "Ticket info...";
    }

    public void assignCustomer(Customer c) {
        this.customer = c;
    }

    public double calculatePrice() {
        return this.price;
    }

    public boolean isValid() {
        return true;
    }
}

// Lớp Đặt chỗ (Booking) - Quản lý quy trình trước khi thanh toán
class Booking {
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

// Lớp Đơn hàng (Order)
class Order {
    private String id;
    private Date orderDate;
    private Customer customer;
    private List<Ticket> tickets = new ArrayList<>();

    public double calculateTotalPrice() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.calculatePrice();
        }
        return total;
    }

    public void assignTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}

// Lớp Thanh toán (Payment)
class Payment {
    private String id;
    private Order order;
    private double amount;
    private String paymentMethod;
    private LocalDateTime paymentTime;
    private String transactionCode;
    private String status;
    private String note;

    public boolean process() {
        // Logic gọi API thanh toán (Momo, VNPay...)
        this.status = "SUCCESS";
        return true;
    }

    public void refund() {
        // Logic hoàn tiền
    }
}