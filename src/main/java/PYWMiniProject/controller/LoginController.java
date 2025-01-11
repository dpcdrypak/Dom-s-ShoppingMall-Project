package PYWMiniProject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import PYWMiniProject.command.LoginCommand;
import PYWMiniProject.service.CookiesService;
import PYWMiniProject.service.IdCheckService;
import PYWMiniProject.service.login.UserLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller

public class LoginController {
	@Autowired
	IdCheckService idCheckService;
	@Autowired
	UserLoginService userLoginService;
	@Autowired
	CookiesService cookiesService;
	
	@GetMapping("/login") // 로그인창 이동
	public String login(HttpSession session, LoginCommand loginCommand,HttpServletRequest request, Model model) {
		if(session.getAttribute("auth") != null) {
			return "redirect:/myPage/memberMyPage"; //로그인상태라면 마이페이지로 리다이렉트
		}
		cookiesService.execute(request, model, loginCommand);
		return "thymeleaf/login";
	}
	
	@PostMapping("/login/userIdCheck")
	public @ResponseBody Integer userIdCheck(String userId) {
		return idCheckService.execute(userId);
	}
	//여기가좀달라짐
	@PostMapping("/login/login")
	public String loginlogin(@Validated LoginCommand loginCommand, BindingResult result,HttpSession session
			,HttpServletResponse response) {
		userLoginService.execute(loginCommand, session, result, response);
		if(result.hasErrors()) {
			return "thymeleaf/login";
		}
		return "redirect:/";
	}
	@GetMapping("/logout") //인덱스에서 로그아웃.
	public String logout(HttpSession session) {
		session.invalidate();
	    return "thymeleaf/initial";
	}
	
	
	
	@GetMapping("item.login")
	public String item() {
		return "thymeleaf/login";
	}
	@PostMapping("item.login")
	public void item(LoginCommand loginCommand,BindingResult result
			,HttpSession session, HttpServletResponse response) {
		userLoginService.execute(loginCommand, session, result, response);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = "<script language='javascript'>";
			   str+= " opener.location.reload();";
			   str+= " window.self.close();";
		       str+= " </script>"; 
		out.print(str);
		out.close();
	}
	
}
