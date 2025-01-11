package PYWMiniProject.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import PYWMiniProject.domain.EmployeeDTO;
import PYWMiniProject.domain.StartEndPageDTO;
import PYWMiniProject.mapper.EmployeeMapper;
import PYWMiniProject.service.StartEndPageService;

@Service
public class EmployeeListService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	StartEndPageService startEndPageService;
	public void execute(Integer page, String searchWord, Model model) {
		Integer limit = 3;
		
		StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
		List<EmployeeDTO> list = employeeMapper.employeeSelectList(sepDTO);
		
		Integer count = employeeMapper.employeeCount();
		
		startEndPageService.execute(page, limit, count, searchWord, list, model);
		


	}
}
