package PYWMiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import PYWMiniProject.service.purchase.INIstdpayPcReturn;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("payment")
public class PaymentController {
	@Autowired
	INIstdpayPcReturn iniPayReturnService;
	@RequestMapping("INIstdpay_pc_return")
	public String payReturn(HttpServletRequest request) {
		iniPayReturnService.execute(request);
		return "thymeleaf/purchase/buyfinished";
	}
	@RequestMapping("close")
	public String close() {
		return "thymeleaf/purchase/close";
	}
}
