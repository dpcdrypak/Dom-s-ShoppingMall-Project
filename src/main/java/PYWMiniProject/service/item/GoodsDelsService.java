package PYWMiniProject.service.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.mapper.ItemMapper;
import PYWMiniProject.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsDelsService {
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    ItemMapper itemMapper;

    public String deleteWishlistItems(String[] goodsNums, HttpSession session) {
        // 사용자 정보 확인
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
        if (authInfo == null) {
            return "401"; // Unauthorized
        }

        // 회원 번호 가져오기
        String memberNum = memberMapper.memberNumSelect(authInfo.getUserId());

        // 삭제 조건 설정
        Map<String, Object> condition = new HashMap<>();
        condition.put("memberNum", memberNum);
        condition.put("goodsNums", goodsNums);

        // 로그로 확인
        System.out.println("삭제 조건: " + condition);

        // 삭제 실행
        int result = itemMapper.wishDelete(condition);
        return result > 0 ? "200" : "000"; // 삭제 성공 또는 실패
    }
}
