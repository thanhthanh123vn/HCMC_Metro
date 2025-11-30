package data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

// Lớp Đầu máy
class Locomotive {
    private String id;
    private boolean status;
    private double speed;
    private int year;
}

// Lớp Toa tàu
class Carriage {
    private String id;
    private int numberOfSeats;
    public Carriage(String id, int numberOfSeats) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
    }
    public int getNumberOfSeats() {
        return this.numberOfSeats;
    }
}



// Lớp Tàu (Train) - Quan hệ Composition với Locomotive & Carriage
class Train {
    private String name;
    private boolean status;
    private Locomotive locomotive;
    private LinkedList<Carriage> carriageList = new LinkedList<>();

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSeatQuantity() {
        // Logic tính tổng số ghế
        return carriageList.stream().mapToInt(c -> c.getNumberOfSeats()).sum(); 
    }
}

// Lớp Chi tiết lịch trình
class ScheduleDetail {
    private String date;
    private double timeStart;
    private double timeEnd;
    private Train train;
    private Route route;
}

// Lớp Lịch chạy hàng ngày
class ScheduleDaily {
    private String date;
    private ArrayList<ScheduleDetail> listOfSchedule = new ArrayList<>();
}

// Lớp Chuyến đi (Trip) - Lớp trung tâm kết nối Tàu và Lịch
class Trip {
    private String id;
    private ScheduleDaily scheduleDaily;
    private Train train;
    private Route route;
    private LocalDate departureDate;
    private LocalDateTime actualDepartureTime;
    private LocalDateTime actualArrivalTime;
    private String status;

    public double calculateFare(Station from, Station to) {
        return 0.0; // Logic tính giá dựa trên khoảng cách
    }

    public void setPeakMode(boolean peak) {
        // Logic set giá cao điểm
    }
}