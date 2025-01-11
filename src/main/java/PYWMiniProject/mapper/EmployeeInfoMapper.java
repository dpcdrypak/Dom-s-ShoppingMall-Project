package PYWMiniProject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import PYWMiniProject.domain.EmployeeDTO;

@Mapper
public interface EmployeeInfoMapper {
	public EmployeeDTO employeeSelectOne(String empId);
	public Integer employeeUpdate(EmployeeDTO dto);
	public Integer employeePwUpdate(@Param("_newPw") String newPw
			,@Param("_empId")String empId);
}
