package PYWMiniProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import PYWMiniProject.command.EmployeeCommand;
import PYWMiniProject.domain.EmployeeDTO;
import PYWMiniProject.mapper.EmployeeMapper;

@Service
public class EmployeeWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpAddr(employeeCommand.getEmpAddr());
		dto.setEmpAddrDetail(employeeCommand.getEmpAddrDetail());
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		dto.setEmpHireDate(employeeCommand.getEmpHireDate());
		dto.setEmpId(employeeCommand.getEmpId().trim());
		dto.setEmpJumin(employeeCommand.getEmpJumin());
		dto.setEmpName(employeeCommand.getEmpName().trim());
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpPost(employeeCommand.getEmpPost());
		dto.setEmpPw(employeeCommand.getEmpPw());
		
		String encodePw = passwordEncoder.encode(employeeCommand.getEmpPw());
		dto.setEmpPw(encodePw);
		
		employeeMapper.employeeInsert(dto);
		
	}
}
