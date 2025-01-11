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

import PYWMiniProject.command.MemberCommand;
import PYWMiniProject.service.AutoNumService;
import PYWMiniProject.service.member.MemberDeleteService;
import PYWMiniProject.service.member.MemberDetailService;
import PYWMiniProject.service.member.MemberListService;
import PYWMiniProject.service.member.MemberUpdateService;
import PYWMiniProject.service.member.MemberWriteService;
import PYWMiniProject.service.member.MembersDeleteService;


@Controller
@RequestMapping("memberJoin")
public class MemberJoinController {
	@Autowired
	MemberWriteService memberWriteService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MembersDeleteService membersDeleteService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@GetMapping("memberList")
	public String list(@RequestParam(value="page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value="searchWord", required = false) String searchWord
			, Model model) {
		memberListService.execute(searchWord, page, model);
		return "thymeleaf/memberJoin/memberList";
	}
	
	@GetMapping("userForm")
	public String userForm(Model model) {
		String autoNum = autoNumService.execute("mem_", "member_num", 5, "members");
		
		MemberCommand memberCommand = new MemberCommand();
		memberCommand.setMemberNum(autoNum);
		model.addAttribute("memberCommand", memberCommand);
		return "thymeleaf/memberJoin/userForm";
	}
	@PostMapping("userWrite")
	public String userWrite(@Validated MemberCommand memberCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/memberJoin/userForm";
		}
		if(!memberCommand.isMemberPwEqualMemberPwCon()) {
			//model.addAttribute("errPw", "비밀번호가 일치하지 않습니다.");
			result.rejectValue("memberPwCon", "memberCommand.memberPwCon"
					, "비밀번호가 일치하지 않습니다.");
			return "thymeleaf/memberJoin/userForm";
		}
		memberWriteService.execute(memberCommand);
		return "thymeleaf/memberJoin/joincomplete";
	}
	
	@RequestMapping(value = "membersDelete")
	public String membersDelete(@RequestParam("nums") String memberNums[]) {
		membersDeleteService.execute(memberNums);
		return "redirect:memberList";
	}
	
	@GetMapping("memberDetail/{memberNum}")
	public String memberDetail(@PathVariable("memberNum") String memberNum
								, Model model) {
		memberDetailService.execute(model, memberNum);
		return "thymeleaf/memberJoin/memberInfo";
	}
	
	@GetMapping("memberUpdate")
	public String memberUpdate(String memberNum, Model model) {
		memberDetailService.execute(model, memberNum);
		return "thymeleaf/memberJoin/memberModify";
	}
	
	@PostMapping("memberUpdate")
	public String memberUpdate(@Validated MemberCommand memberCommand
			,BindingResult result) {
		if(result.hasErrors()) {		
			return "thymeleaf/memberJoin/memberModify";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:memberDetail/"+memberCommand.getMemberNum();
	}
	
	@GetMapping("memberDelete/{memberNum}")
	public String memberDelete(@PathVariable("memberNum") String memberNum) {
		memberDeleteService.execute(memberNum);
		return "redirect:../memberList";
	}
}
