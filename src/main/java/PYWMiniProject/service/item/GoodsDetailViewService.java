package PYWMiniProject.service.item;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.domain.GoodsStockDTO;
import PYWMiniProject.mapper.GoodsStockMapper;
import PYWMiniProject.mapper.ItemMapper;
import PYWMiniProject.mapper.MemberMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsDetailViewService {
	@Autowired
	GoodsStockMapper goodsStockMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	ItemMapper itemMapper;
	public void execute(String goodsNum, Model model, HttpServletResponse response
			, HttpSession session) {
		GoodsStockDTO dto = goodsStockMapper.goodsStockSelectOne(goodsNum);
		goodsStockMapper.goodsVisitCountUpdate(goodsNum);
		model.addAttribute("dto", dto);
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(auth != null) {
			String memberNum =  memberMapper.memberNumSelect(auth.getUserId());
			Map<String, String> map = new HashMap<String, String>();
			map.put("goodsNum", goodsNum);
			map.put("memberNum", memberNum);
			Integer i = itemMapper.wishCountSelectOne(map);
			model.addAttribute("wish", i);
		}
		ObjectMapper mapper = new ObjectMapper();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			response.getWriter().print(mapper.writeValueAsString(dto));
		}catch(Exception e) {}
	}
}
