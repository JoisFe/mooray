package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.domain.Member;
import com.nhnacademy.gateway.domain.ProjectMember;
import com.nhnacademy.gateway.service.MemberService;
import com.nhnacademy.gateway.service.ProjectMemberService;
import com.nhnacademy.gateway.vo.SecurityUser;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;
    private final MemberService memberService;

    @ModelAttribute("member")
    public SecurityUser getSessionMember(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        return (SecurityUser) session.getAttribute("member");
    }

    @GetMapping("/projectList")
    public String getProjectList(
        @RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model,
        @ModelAttribute("member") SecurityUser member, @RequestParam(value = "message", required = false) String message) {
            List<ProjectMember> projectMembers =
            projectMemberService.findProjectList(member.getMemberNum(), page);

        model.addAttribute("projectMemberList", projectMembers);
        model.addAttribute("message", message);
        model.addAttribute("maxPage", 5);

        System.out.println(member.getPassword());

        return "projectList";
    }

    @GetMapping("/project/{projectNum}/member/register")
    public String registerProjectMember(@PathVariable(value = "projectNum") Long projectNum,
                                        @ModelAttribute("member") SecurityUser member,
                                        Model model) {

        List<Member> memberList = memberService.findAllMember();

        model.addAttribute("projectNum", projectNum);
        model.addAttribute("memberList", memberList);

        return "projectMemberRegister";
    }

    @PostMapping("/project/{projectNum}/member/register")
    public String registerProjectMember(String memberNum, @PathVariable(value = "projectNum") Long projectNum, Model model) {
        String message = projectMemberService.registerProjectMember(Long.valueOf(memberNum), projectNum);

        model.addAttribute("message", message);

        return "redirect:/projectList";
    }
}
