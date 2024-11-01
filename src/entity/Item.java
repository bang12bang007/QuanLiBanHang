package entity;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Item {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String category;// loại sản phẩm
    private Timestamp createdAt;  // thời gian nhập kho
    // Constructors, Getters, and Setters
    public Item() {}

    public Item(int id, String name, double price, int quantity, String category, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.createdAt = createdAt;
    }

	public int getId() {
            return id;
	}

	public void setId(int id) {
            this.id = id;
	}

	public String getName() {
            return name;
	}

	public void setName(String name) {
            this.name = name;
	}

	public double getPrice() {
            return price;
	}

	public void setPrice(double price) {
            this.price = price;
	}

	public int getQuantity() {
            return quantity;
	}

	public void setQuantity(int quantity) {
            this.quantity = quantity;
	}

	public String getCategory() {
            return category;
	}

	public void setCategory(String category) {
            this.category = category;
	}

	public Timestamp getCreatedAt() {
            return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
            this.createdAt = createdAt;
	}
        
	public static void formatMoney(String a){
            DecimalFormat dc = new DecimalFormat("#,##VND");
            dc.format(a);
        }
        


    // Getters and Setters
    
    // toString method
}
