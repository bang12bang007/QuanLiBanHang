package entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class Order {
	private String id;
	private Customer customer; // Foreign key relationship
	private Date orderDate;
	private double totalAmount;
	private String status;

	private List<OrderDetail> orderDetails;

	// Constructors, Getters, and Setters
	public Order() {
	}

	public Order(String id, Customer customer, Date orderDate, double totalAmount, String status) {
		this.id = id;
		this.customer = customer;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	// Getters and Setters

	// toString method
}
