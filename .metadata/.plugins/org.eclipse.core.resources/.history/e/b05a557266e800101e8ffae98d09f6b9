package data;

import java.time.LocalDateTime;

public class Payment {
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