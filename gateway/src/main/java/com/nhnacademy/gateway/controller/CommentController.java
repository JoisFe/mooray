package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.request.CommentRequestDto;
import com.nhnacademy.gateway.service.CommentService;
import com.nhnacademy.gateway.vo.SecurityUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;

    @ModelAttribute("member")
    public SecurityUser getSessionMember(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        return (SecurityUser) session.getAttribute("member");
    }

    @PostMapping("/project/{projectNum}/task/{taskNum}/comment")
    public String registerComment(CommentRequestDto commentRequestDto,
                                  @PathVariable(value = "projectNum") Long projectNum,
                                  @PathVariable(value = "taskNum") Long taskNum,
                                  @ModelAttribute("member") SecurityUser member, Model model) {
        String message = commentService.registerComment(commentRequestDto, projectNum, taskNum, member.getUsername());

        model.addAttribute("message", message);

        return "redirect:/project/" + projectNum + "/task/detail/" + taskNum;
    }

    @GetMapping("/project/{projectNum}/task/{taskNum}/comment/{commentNum}/update")
    public String updateCommentPage(@PathVariable(value = "projectNum") Long projectNum,
                                @PathVariable(value = "taskNum") Long taskNum,
                                @PathVariable(value = "commentNum") Long commentNum, Model model) {
        model.addAttribute("projectNum", projectNum);
        model.addAttribute("taskNum", taskNum);
        model.addAttribute("commentNum", commentNum);

        return "commentUpdate";
    }

    @PostMapping("/project/{projectNum}/task/{taskNum}/comment/{commentNum}/update")
    public String updateComment(String commentContent,
                            @PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "taskNum") Long taskNum,
                            @PathVariable(value = "commentNum") Long commentNum, Model model) {
        String message = commentService.update(commentContent, projectNum, taskNum, commentNum);

        model.addAttribute("message", message);

        return "redirect:/project/" + projectNum + "/task/detail/" + taskNum;
    }

    @GetMapping("/project/{projectNum}/task/{taskNum}/comment/{commentNum}/delete")
    public String deleteComment(@PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "taskNum") Long taskNum,
                            @PathVariable(value = "commentNum") Long commentNum, Model model) {
        String message = commentService.delete(projectNum, taskNum, commentNum);
        model.addAttribute("message", message);

        return "redirect:/project/" + projectNum + "/task/detail/" + taskNum;
    }
}
