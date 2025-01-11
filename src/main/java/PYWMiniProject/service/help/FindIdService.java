package PYWMiniProject.service.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import PYWMiniProject.mapper.FindMapper;

@Service
public class FindIdService {
    @Autowired
    FindMapper findMapper;

    public void execute(String userPhone, String userEmail, Model model) {
        String userId = findMapper.findId(userPhone, userEmail);
        if (userId == null || userId.isEmpty()) {
            System.out.println("아이디를 찾을 수 없습니다. 입력한 정보: " 
                               + userPhone + ", " + userEmail);
        }
        model.addAttribute("userId", userId);
    }
}
