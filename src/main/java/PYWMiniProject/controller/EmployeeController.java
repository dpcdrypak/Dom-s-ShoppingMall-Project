package PYWMiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PYWMiniProject.command.EmployeeCommand;
import PYWMiniProject.service.AutoNumService;
import PYWMiniProject.service.employee.EmployeeDeleteService;
import PYWMiniProject.service.employee.EmployeeDetailService;
import PYWMiniProject.service.employee.EmployeeListService;
import PYWMiniProject.service.employee.EmployeeUpdateService;
import PYWMiniProject.service.employee.EmployeeWriteService;
import PYWMiniProject.service.employee.EmployeesDeleteService;




@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeWriteService employeeWriteService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeDetailService employeeDetailService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	@Autowired
	EmployeesDeleteService employeesDeleteService;
	
	@RequestMapping("employeeList")
	public String employeeList(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "searchWord", required = false) String searchWord
			,Model model) {
		employeeListService.execute(page, searchWord, model);
		return "thymeleaf/employee/employeeList";
	}
	@RequestMapping("employeeWrite")
	public String employeeWrite(Model model) {
		String autoNum = autoNumService.execute("emp_", "emp_num", 5, "employees");
		System.out.println(autoNum);
		EmployeeCommand employeeCommand = new EmployeeCommand();
		employeeCommand.setEmpNum(autoNum);
	    model.addAttribute("employeeCommand", employeeCommand);
		return "thymeleaf/employee/employeeForm";
	}
	@RequestMapping("employeeRegist")
	public String employeeRegist(@Validated EmployeeCommand employeeCommand
						, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/employee/employeeForm";
		}
		if(!employeeCommand.isEmpPwEqualEmpPwCon()) {
			//model.addAttribute("errPw", "비밀번호가 일치하지 않습니다.");
			result.rejectValue("empPwCon", "employeeCommand.empPwCon", "비밀번호가 일치하지 않습니다.");
			return "thymeleaf/employee/employeeForm";
		}
		employeeWriteService.execute(employeeCommand);
		return "redirect:employeeList";
	}
	@RequestMapping(value = "employeesDelete")
	public String employeesDelete(@RequestParam("nums") String empNums[]) {
		employeesDeleteService.execute(empNums);
		return "redirect:employeeList";
	}
	
	@GetMapping("employeeDetail/{empNum}")
	public String employeeInfo(@PathVariable("empNum") String empNum, Model model) {
		employeeDetailService.execute(empNum, model );
		return "thymeleaf/employee/employeeInfo";
	}
	@GetMapping("employeeUpdate")
	public String employeeUpdate(String empNum, Model model) {
		employeeDetailService.execute(empNum, model );
		return "thymeleaf/employee/employeeModify";
	}
	@PostMapping("employeeUpdate")
	public String employeeUpdate(EmployeeCommand employeeCommand) {
		employeeUpdateService.execute(employeeCommand);
		return "redirect:employeeDetail/"+employeeCommand.getEmpNum();
	}
	@RequestMapping("employeeDelete/{empNum}")
	public String employeeDelete(@PathVariable("empNum") String empNum) {
		employeeDeleteService.execute(empNum);
		return "redirect:../employeeList";

	}
}