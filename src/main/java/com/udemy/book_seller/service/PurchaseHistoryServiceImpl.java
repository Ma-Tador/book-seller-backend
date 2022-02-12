package com.udemy.book_seller.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.book_seller.model.PurchaseHistory;
import com.udemy.book_seller.repository.PurchaseHistoryRepository;
import com.udemy.book_seller.repository.projection.PurchaseItem;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService{

	
	@Autowired
	private PurchaseHistoryRepository purchaseHistoryRepository;

	@Override
	public PurchaseHistory save(PurchaseHistory purchaseHistory) {
		purchaseHistory.setPurchaseTime(LocalDateTime.now());
		return purchaseHistoryRepository.save(purchaseHistory);
	}
	
	
	@Override
	public List<PurchaseItem> findPurchaseItemsByUser(long userId){
		return purchaseHistoryRepository.findAllPurchasesOfUser(userId);
	}
	
	
	
	
	
}
