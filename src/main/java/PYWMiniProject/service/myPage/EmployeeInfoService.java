package PYWMiniProject.service.myPage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.domain.EmployeeDTO;
import PYWMiniProject.mapper.EmployeeInfoMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeInfoService {
    @Autowired
    EmployeeInfoMapper employeeInfoMapper;

    public Map<String, Object> execute(HttpSession session, Model model) {
        Map<String, Object> map = new HashMap<>();

        // 세션에서 사용자 인증 정보 가져오기
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        if (auth == null) {
            System.out.println("[DEBUG] EmployeeInfoService - Session 'auth' is null");
            return map;
        }

        System.out.println("[DEBUG] EmployeeInfoService - Retrieved 'auth' from session: " + auth);

        // 데이터베이스에서 직원 정보 조회
        String userId = auth.getUserId();
        EmployeeDTO dto = employeeInfoMapper.employeeSelectOne(userId);

        if (dto == null) {
            System.out.println("[DEBUG] EmployeeInfoService - No employee found for userId: " + userId);
        } else {
            System.out.println("[DEBUG] EmployeeInfoService - Retrieved employee: " + dto);

            // Model에 데이터 추가
            model.addAttribute("employeeCommand", dto);

            // Map에도 데이터 추가
            map.put("employeeCommand", dto);
        }

        return map;
    }
}
