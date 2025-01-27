package PYWMiniProject.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Alias("memberDTO")
@Data
public class MemberDTO {
	String memberNum;
	String memberId;
	String memberPw;
	String memberName;
	String memberPhone1;
	String memberPhone2;
	String memberAddr;
	String memberAddrDetail;
	String memberPost;
	String gender;
	String memberEmail;
	Date memberBirth;
	Date memberRegist;
	String memberEmailConf;
}
