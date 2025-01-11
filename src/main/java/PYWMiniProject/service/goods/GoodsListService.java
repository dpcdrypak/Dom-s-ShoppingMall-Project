package PYWMiniProject.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import PYWMiniProject.domain.GoodsDTO;
import PYWMiniProject.domain.StartEndPageDTO;
import PYWMiniProject.mapper.GoodsMapper;
import PYWMiniProject.service.StartEndPageService;

@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	StartEndPageService startEndPageService;
	public void execute(String searchWord, int page, Model model) {
		//한 페이지에 보일 list
		int limit = 3;
		StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
		List<GoodsDTO> list = goodsMapper.allSelect(sepDTO);
		int count = goodsMapper.goodsCount(searchWord);
		startEndPageService.execute(page, limit, count, searchWord, list, model);
		
	}
}
