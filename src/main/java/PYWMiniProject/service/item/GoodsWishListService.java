package PYWMiniProject.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.domain.GoodsDTO;
import PYWMiniProject.mapper.ItemMapper;
import PYWMiniProject.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsWishListService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	ItemMapper itemMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(authInfo.getUserId());
		List<GoodsDTO> list = itemMapper.wishSelectList(memberNum);
		model.addAttribute("list", list);
	}
}
