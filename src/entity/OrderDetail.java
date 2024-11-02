package entity;

import java.sql.Timestamp;

public class OrderDetail {
    private String id;
    private Order order; // Foreign key relationship
    private Item item; // Foreign key relationship
    private int quantity;
    private double price;
    private double total; // Computed field (quantity * price)
    private Timestamp createdAt;

    // Constructors, Getters, and Setters
    public OrderDetail() {}

    public OrderDetail(String id, Order order, Item item, int quantity, double price, Timestamp createdAt) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
        this.createdAt = createdAt;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


    // toString method
}

