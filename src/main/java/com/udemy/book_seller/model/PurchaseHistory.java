package com.udemy.book_seller.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase_history")
public class PurchaseHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "user_id", nullable = false)
	private long userId;
	
	@Column(name="book_id", nullable = false)
	private long bookId;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "purchase_time", nullable = false)
	private LocalDateTime purchaseTime;

	
	
	public long getId() {
		return id;
	}

	public long getUserId() {
		return userId;
	}

	public long getBookId() {
		return bookId;
	}

	public double getPrice() {
		return price;
	}

	public LocalDateTime getPurchaseTime() {
		return purchaseTime;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPurchaseTime(LocalDateTime purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	
	
}
