package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private Date orderDate = new Date();
    private Customer customer;
    private List<Ticket> tickets = new ArrayList<>();

    public double calculateTotalPrice() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.getPrice();
        }
        return total;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void assignTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }

  
}