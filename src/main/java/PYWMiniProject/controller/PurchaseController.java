package PYWMiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import PYWMiniProject.command.PurchaseCommand;
import PYWMiniProject.repository.PurchaseRepository;
import PYWMiniProject.service.purchase.GoodsBuyService;
import PYWMiniProject.service.purchase.GoodsOrderService;
import PYWMiniProject.service.purchase.IniPayReqService;
import PYWMiniProject.service.purchase.OrderProcessListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	OrderProcessListService orderProcessListService;
	@Autowired
	GoodsBuyService goodsBuyService;
	@Autowired
	GoodsOrderService goodsOrderService;
	@Autowired
	IniPayReqService iniPayReqService;

	@RequestMapping("goodsBuy")
	public String goodsBuy(String nums[] , HttpSession session,Model model) {
		goodsBuyService.execute(nums, session, model);
		return "thymeleaf/purchase/goodsOrder";
	}
	@PostMapping("goodsOrder")
	public String goodsOrder(PurchaseCommand purchaseCommand, HttpSession session,
			Model model) {
		String purchaseNum = goodsOrderService.execute(purchaseCommand, session, model);
		return "redirect:paymentOk?purchaseNum="+purchaseNum;
	}
	@GetMapping("paymentOk")
	public String paymentOk(String purchaseNum,Model model) {
		try {
			iniPayReqService.execute(purchaseNum, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "thymeleaf/purchase/payment";
	}

	@GetMapping("orderList")
	public String orderList(HttpSession session, Model model) {
		orderProcessListService.execute(session, model, null);
		return "thymeleaf/purchase/orderList";
	}
	@GetMapping("purchaseList")
	public String purchaseList(HttpSession session, Model model) {
		orderProcessListService.execute(session, model, null);
		return "thymeleaf/purchase/purchaseList";
	}
	@Autowired
	PurchaseRepository purchaseRepository;
	@GetMapping("paymentDel")
	public String paymentDel(String purchaseNum) {
		purchaseRepository.paymentDel(purchaseNum);
		return "redirect:orderList";
	}
	@GetMapping("purchaseOk")
	public String purchaseOk(String purchaseNum) {
		purchaseRepository.paymentStatusUpdate(purchaseNum);
		return "redirect:orderList";
	}
	
	
}
