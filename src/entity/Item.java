package entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Item {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String category;// loại sản phẩm
    private Timestamp createdAt;  // thời gian nhập kho
    private Date expiredDate; // hạn sử dụng
  
    public Item() {}

    

	public Item(String id, String name, double price, int quantity, String category, Timestamp createdAt,
			Date expiredDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.createdAt = createdAt;
		this.expiredDate = expiredDate;
	}



	public String getId() {
            return id;
	}

	public void setId(String id) {
            this.id = id;
	}

	public String getName() {
            return name;
	}


	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
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
        



	public Date getExpiredDate() {
		return expiredDate;
	}



        


    // Getters and Setters
    
    // toString method
}
