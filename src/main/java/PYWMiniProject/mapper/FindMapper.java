package PYWMiniProject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.domain.UserChangePasswordDTO;

@Mapper
public interface FindMapper {
	public String findId(@Param("_userPhone")String userPhone
					   , @Param("_userEmail")String userEmail);
	public AuthInfoDTO userEmail(@Param("_userId") String userId,
			 @Param("_userPhone") String userPhone);
	public int pwUpdate(UserChangePasswordDTO dto);
}
