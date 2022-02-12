package com.udemy.book_seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.book_seller.model.PurchaseHistory;
import com.udemy.book_seller.security.basic.CustomUserDetails;
import com.udemy.book_seller.service.PurchaseHistoryService;

@RestController
@RequestMapping("api/purchase-history")
public class PurcaseHistoryCOntroller {

	@Autowired
	private PurchaseHistoryService purchaseHistoryService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PurchaseHistory purchaseHistory){
		return new ResponseEntity<>(purchaseHistoryService.save(purchaseHistory), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllPurchasesOfAuthUser(@AuthenticationPrincipal CustomUserDetails currentUser){
		return new ResponseEntity<>(purchaseHistoryService.findPurchaseItemsByUser(currentUser.getId()), HttpStatus.OK);
	}
	
}
