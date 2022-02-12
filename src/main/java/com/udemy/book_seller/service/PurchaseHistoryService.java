package com.udemy.book_seller.service;

import java.util.List;

import com.udemy.book_seller.model.PurchaseHistory;
import com.udemy.book_seller.repository.projection.PurchaseItem;

public interface PurchaseHistoryService {

	PurchaseHistory save(PurchaseHistory purchaseHistory);

	List<PurchaseItem> findPurchaseItemsByUser(long userId);

}
