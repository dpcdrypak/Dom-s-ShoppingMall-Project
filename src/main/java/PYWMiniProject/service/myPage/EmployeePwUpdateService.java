package PYWMiniProject.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.mapper.EmployeeInfoMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EmployeePwUpdateService {
	@Autowired
	EmployeeInfoMapper employeeInfoMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(String oldPw, String newPw, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(oldPw, auth.getUserPw())) {
			String pw = passwordEncoder.encode(newPw);
			employeeInfoMapper.employeePwUpdate(pw, auth.getUserId());
			auth.setUserPw(pw);
		}
	}
}