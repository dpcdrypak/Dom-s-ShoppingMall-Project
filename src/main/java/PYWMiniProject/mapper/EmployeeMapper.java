package PYWMiniProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import PYWMiniProject.domain.EmployeeDTO;
import PYWMiniProject.domain.StartEndPageDTO;

@Mapper
public interface EmployeeMapper {
	public void employeeInsert(EmployeeDTO dto);
	public List<EmployeeDTO> employeeSelectList(StartEndPageDTO sepDTO);
	public Integer employeeCount();
	public EmployeeDTO employeeSelectOne(String empNum);
	public void employeeUpdate(EmployeeDTO dto);
	public void employeeDelete(String empNum);
	public String getEmpNum(String empId);
}

