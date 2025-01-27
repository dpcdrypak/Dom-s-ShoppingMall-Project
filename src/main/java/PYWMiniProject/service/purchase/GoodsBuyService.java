package PYWMiniProject.service.purchase;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.domain.GoodsCartDTO;
import PYWMiniProject.mapper.CartMapper;
import PYWMiniProject.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
@Service
public class GoodsBuyService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	CartMapper cartMapper;
	public void execute(String [] nums, HttpSession session , Model model) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		List<GoodsCartDTO> list = cartMapper.cartSelectList(memberNum, nums);
		model.addAttribute("list", list);

		Integer sumPrice = 0; // 결제 금액
		Integer deliveryCost = 50; // 배송비합계금액
		String goodsNums = ""; // 상품번호들 저장
		for(GoodsCartDTO dto : list) {
			sumPrice += dto.getGoodsDTO().getGoodsPrice() * dto.getCartDTO().getCartQty();
			goodsNums += dto.getGoodsDTO().getGoodsNum()+"-";
		}
		if(sumPrice >= 30000)  deliveryCost = 0;
		model.addAttribute("sumPrice", sumPrice);
		model.addAttribute("deliveryCost", deliveryCost);
		model.addAttribute("goodsNums", goodsNums);
	}
}