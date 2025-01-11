package PYWMiniProject.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import PYWMiniProject.command.MemberCommand;
import PYWMiniProject.domain.MemberDTO;
import PYWMiniProject.mapper.MemberMapper;

@Service
public class MemberWriteService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		dto.setGender(memberCommand.getGender());
		dto.setMemberNum(memberCommand.getMemberNum());
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberAddrDetail(memberCommand.getMemberAddrDetail());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberId(memberCommand.getMemberId().trim());
		dto.setMemberName(memberCommand.getMemberName().trim());
		dto.setMemberPhone1(memberCommand.getMemberPhone1());
		
		if(memberCommand.getMemberPhone2() != null) {
			dto.setMemberPhone2(memberCommand.getMemberPhone2().trim());
		}		
		//dto.setMemberPw(passwordEncoder.encode(memberCommand.getMemberPw()));

		dto.setMemberPost(memberCommand.getMemberPost());
		String encodePw = passwordEncoder.encode(memberCommand.getMemberPw());
		dto.setMemberPw(encodePw);
		
		memberMapper.memberInsert(dto);
	}
}

