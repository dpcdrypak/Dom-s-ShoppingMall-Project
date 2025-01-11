package PYWMiniProject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import PYWMiniProject.command.EmployeeCommand;
import PYWMiniProject.command.MemberCommand;
import PYWMiniProject.service.myPage.EmployeeInfoService;
import PYWMiniProject.service.myPage.EmployeeModifyService;
import PYWMiniProject.service.myPage.EmployeePwUpdateService;
import PYWMiniProject.service.myPage.MemberDropService;
import PYWMiniProject.service.myPage.MemberMyInfoService;
import PYWMiniProject.service.myPage.MemberMyUpdateService;
import PYWMiniProject.service.myPage.MemberPwUpdateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("myPage")
public class MyPageController {
    @Autowired
    MemberMyInfoService memberMyInfoService;
    @Autowired
    MemberMyUpdateService memberMyUpdateService;
    @Autowired
    MemberPwUpdateService memberPwUpdateService;
    @Autowired
    MemberDropService memberDropService;
    @Autowired
    EmployeeInfoService employeeInfoService;
    @Autowired
    EmployeeModifyService employeeModifyService;
    @Autowired
    EmployeePwUpdateService employeePwUpdateService;

    @GetMapping("memberMyPage")
    public String memMyPage(HttpSession session, Model model) {
        System.out.println("[DEBUG] memberMyPage - Session ID: " + session.getId());
        memberMyInfoService.execute(session, model);
        return "thymeleaf/myPage/memberMyPage";
    }

    @GetMapping("memberUpdate")
    public String memberUpdate(HttpSession session, Model model) {
        System.out.println("[DEBUG] memberUpdate - Session ID: " + session.getId());
        memberMyInfoService.execute(session, model);
        return "thymeleaf/myPage/myModify";
    }

    @PostMapping("memberModify")
    public String memberModify(MemberCommand memberCommand, HttpSession session) {
        System.out.println("[DEBUG] memberModify - MemberCommand: " + memberCommand);
        memberMyUpdateService.execute(memberCommand, session);
        return "redirect:memberMyPage";
    }

    @RequestMapping(value = "memberPwModify", method = RequestMethod.GET)
    public String memberPwModify() {
        System.out.println("[DEBUG] memberPwModify - Accessed");
        return "thymeleaf/myPage/myNewPw";
    }

    @RequestMapping(value = "memberPwPro", method = RequestMethod.POST)
    public String newPw(String oldPw, String newPw, HttpSession session) {
        System.out.println("[DEBUG] memberPwPro - OldPw: " + oldPw + ", NewPw: " + newPw);
        memberPwUpdateService.execute(oldPw, newPw, session);
        return "redirect:memberMyPage";
    }

    @GetMapping("memberDrop")
    public String memberDrop() {
        System.out.println("[DEBUG] memberDrop - Accessed");
        return "thymeleaf/myPage/memberDrop";
    }

    @PostMapping("memberDropOk")
    public String memberDropOk(String memberPw, HttpSession session) {
        System.out.println("[DEBUG] memberDropOk - MemberPw: " + memberPw);
        memberDropService.execute(memberPw, session);
        return "redirect:/logout";
    }

    @GetMapping("empModify")
    public @ResponseBody Map<String, Object> empPage(HttpSession session, Model model) {
        System.out.println("[DEBUG] empModify - Session ID: " + session.getId());
        Map<String, Object> map = employeeInfoService.execute(session, model);
        System.out.println("[DEBUG] empModify - Result: " + map);
        return map;
    }

    @PostMapping("empModify")
    public String empModify(EmployeeCommand employeeCommand, HttpSession session) {
        System.out.println("[DEBUG] empModify - EmployeeCommand: " + employeeCommand);
        employeeModifyService.execute(employeeCommand, session);
        return "redirect:employeeMyPage";
    }

    @GetMapping("employeeMyPage")
    public String empMyPage() {
        System.out.println("[DEBUG] employeeMyPage - Accessed");
        return "thymeleaf/myPage/employeeInfo";
    }

    @PostMapping("empMyPage")
    public ModelAndView empMyPage(HttpSession session, Model model) {
        System.out.println("[DEBUG] empMyPage - Session ID: " + session.getId());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsonView");
        employeeInfoService.execute(session, model);
        System.out.println("[DEBUG] empMyPage - Model: " + model);
        return mav;
    }

    @PostMapping("empPwPro")
    public String empPwPro(@RequestParam("oldPw") String oldPw, String newPw, HttpSession session) {
        System.out.println("[DEBUG] empPwPro - OldPw: " + oldPw + ", NewPw: " + newPw);
        employeePwUpdateService.execute(oldPw, newPw, session);
        return "redirect:employeeMyPage";
    }
}
