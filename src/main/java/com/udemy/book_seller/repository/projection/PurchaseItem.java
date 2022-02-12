package com.udemy.book_seller.repository.projection;

import java.time.LocalDateTime;

public interface PurchaseItem {

	
	String getTitle();
	String getPrice();
	LocalDateTime getPurchaseTime();
	
}
