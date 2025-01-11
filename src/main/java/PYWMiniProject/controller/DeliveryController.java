package PYWMiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PYWMiniProject.repository.DeliveryRepository;
import PYWMiniProject.service.delivery.DeliveryInsertService;
import PYWMiniProject.service.purchase.OrderProcessListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("delivery")
public class DeliveryController {
	@Autowired
	OrderProcessListService orderProcessListService;
	@Autowired
	DeliveryInsertService deliveryInsertService;
	
	@GetMapping("deliveryRegist")
	public String deliveryRegist(String purchaseNum
			,Model model, HttpSession session) {
		orderProcessListService.execute(session, model, purchaseNum);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/delivery/deliveryRegist"; 
	}
	@PostMapping("deliveryRegist")
	public String deliveryRegist(String purchaseNum
			,@RequestParam(value="deliveryNum" , required = false , defaultValue = "")String deliveryNum) {
		deliveryInsertService.execute(purchaseNum, deliveryNum);
		return "redirect:deliveryRegist?purchaseNum="+purchaseNum;
	}
	@Autowired
	DeliveryRepository deliveryRepository;
	@GetMapping("deliveryStatus")
	public String deliveryStatus(String purchaseNum) {
		deliveryRepository.deliveryStatusUpdate(purchaseNum);
		return "redirect:/purchase/purchaseList";
	}
	@PostMapping("deliveryDelete")
	public String deliveryDelete(String purchaseNum) {
		deliveryRepository.deliveryDelete(purchaseNum);
		return "redirect:deliveryRegist?purchaseNum="+purchaseNum;
	}}
	