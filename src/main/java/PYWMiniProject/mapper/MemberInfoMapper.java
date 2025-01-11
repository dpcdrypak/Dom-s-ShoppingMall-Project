package PYWMiniProject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import PYWMiniProject.domain.MemberDTO;

@Mapper
public interface MemberInfoMapper {
	public MemberDTO memberSelectOne(String memberId);
	public Integer memberUpdate(MemberDTO dto);
	public Integer memberPwUpdate(@Param("_newPw")String newPw, @Param("_memberId")String memberId);
	public Integer memberDelete(String memberId);
}