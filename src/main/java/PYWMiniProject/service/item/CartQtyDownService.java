package PYWMiniProject.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.mapper.CartMapper;
import PYWMiniProject.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CartQtyDownService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	CartMapper cartMapper;
	public void execute(String goodsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		cartMapper.cartQtyDown(goodsNum, memberNum);
	}
}