package PYWMiniProject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import PYWMiniProject.domain.GoodsDTO;

@Mapper
public interface ItemMapper {
	public int wishItem(Map<String , String> map);
	public List<GoodsDTO> wishSelectList(String memberNum);
	public Integer wishCountSelectOne(Map<String, String> map);
	public Integer wishDelete(Map<String, Object> condition);
}
