package com.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.dao.UserDao;
import com.code.model.Subscription;
import com.code.model.User;
import com.code.services.AccountDetails;
import com.code.services.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("")
	public ResponseEntity<?> addSubscription(@AuthenticationPrincipal AccountDetails account, @RequestBody Subscription request) {
		User user = userDao.getUserByUsername(account.getUsername());
		return subscriptionService.addSubscription(user.getUserId(), request);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getSubscriptions(@AuthenticationPrincipal AccountDetails account) {
		User user = userDao.getUserByUsername(account.getUsername());
		return subscriptionService.getSubscriptions(user.getUserId());
	}
	
	@DeleteMapping("/subscriptionId={subscriptionId}")
	public ResponseEntity<?> deleteSubscription(@AuthenticationPrincipal AccountDetails account, @PathVariable String subscriptionId) {
		User user = userDao.getUserByUsername(account.getUsername());
		return subscriptionService.deleteSubscription(user.getUserId(), Integer.parseInt(subscriptionId));
	}
	
	@PutMapping("/subscriptionId={subscriptionId}")
	public ResponseEntity<?> editSubscription(@AuthenticationPrincipal AccountDetails account, @RequestBody Subscription subscription, @PathVariable String subscriptionId) {
		User user = userDao.getUserByUsername(account.getUsername());
		return subscriptionService.editSubscription(user.getUserId(), subscription, Integer.parseInt(subscriptionId));
	}

//	@Autowired
//	private Subscription subscription;
//	
//	@Autowired
//	private SubscriptionDao subscriptionDao;
//	
//	@RequestMapping("/subscriptions/{userId}")
//	public String subscriptionPage(Model model, @PathVariable int userId) {
//		model.addAttribute("userId", userId);
//		return "subscription";
//	} 
//	
//	@PostMapping("/add-subscription/{userId}")
//	private String addSubscription(Model model,
//			@RequestParam("subscriptionName") String subscriptionName,
//			@RequestParam("amount") float amount,
//			@RequestParam("renewalDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate renewalDate,
//			@PathVariable int userId) {
//
//		subscription = new Subscription();
//		subscription.setSubscriptionName(subscriptionName);
//		subscription.setAmount(amount);
//		subscription.setRenewalDate(renewalDate);
//
//
//		
//		if(subscription==null) {
//			model.addAttribute("error", "Empty fields.");
//			return "subscription";
//		} else {
//			subscriptionDao.save(userId, subscription);
//		}
//		
//		return "index";
//	}
}
