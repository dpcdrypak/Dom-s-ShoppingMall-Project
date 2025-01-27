package PYWMiniProject.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberCommand {
	String memberNum;
	@NotEmpty(message = "아이디를 입력해주세요.") 
	String memberId;
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
			message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String memberPw;
	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	String memberPwCon;
	@NotEmpty(message = "이름을 입력해주세요")
	String memberName;
	@NotBlank(message = "주소를 입력하여 주세요.")
	String memberAddr;
	String memberAddrDetail;
	String memberPost;
	@NotBlank(message = "연락ㄴ처를 입력하여 주세요.")
	@Size(min = 11, max = 13)
	String memberPhone1;
	@Size(min = 11, max = 13)
	String memberPhone2;
	String gender;
	@NotNull(message = "생년월일을 입력해주세요.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberBirth;
	@NotBlank(message = "이메일을 입력하여 주세요.")
	String memberEmail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberRegist;
	public boolean isMemberPwEqualMemberPwCon() {
		return memberPw.equals(memberPwCon);
	}
}
