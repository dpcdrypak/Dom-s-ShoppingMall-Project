package PYWMiniProject.service.item;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.mapper.ItemMapper;
import PYWMiniProject.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsWishService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	ItemMapper itemMapper;
	public void execute(String goodsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		
		Map<String , String> map = new HashMap<String , String>();
		map.put("goodsNum", goodsNum);
		map.put("memberNum", memberNum);
		
		itemMapper.wishItem(map);
	}
}
