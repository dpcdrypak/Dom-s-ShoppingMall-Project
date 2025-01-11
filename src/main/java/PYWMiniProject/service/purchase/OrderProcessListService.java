package PYWMiniProject.service.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.domain.OrderListDTO;
import PYWMiniProject.mapper.MemberMapper;
import PYWMiniProject.repository.PurchaseRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class OrderProcessListService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PurchaseRepository purchaseRepository;
	public void execute(HttpSession session, Model model, String purchaseNum) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());

		Map<String , String> map = new HashMap<String, String>();
		map.put("memberNum", memberNum);
		map.put("purchaseNum", purchaseNum);

		List<OrderListDTO> list = purchaseRepository.orderList(map); 

		model.addAttribute("list", list);
	}
}