package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.domain.Milestone;
import com.nhnacademy.gateway.domain.Project;
import com.nhnacademy.gateway.domain.Tag;
import com.nhnacademy.gateway.domain.Task;
import com.nhnacademy.gateway.dto.request.ProjectRequestDto;
import com.nhnacademy.gateway.service.MilestoneService;
import com.nhnacademy.gateway.service.ProjectService;
import com.nhnacademy.gateway.service.TagService;
import com.nhnacademy.gateway.service.TaskService;
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
public class ProjectController {
    private final ProjectService projectService;
    private final TaskService taskService;
    private final TagService tagService;
    private final MilestoneService milestoneService;
//    private final RedisTemplate<String, String> redisTemplate;

    @ModelAttribute("member")
    public SecurityUser getSessionMember(HttpServletRequest httpServletRequest) {
//        SecurityUser user = (SecurityUser)redisTemplate.opsForHash().get(httpServletRequest.getSession().getId(),"member");

//        System.out.println(user.getPassword());

        HttpSession session = httpServletRequest.getSession(true);

        return (SecurityUser) session.getAttribute("member");
    }

    @GetMapping("/project/create")
    public String createProjectPage() {
        return "projectCreate";
    }

    @PostMapping("/project/create")
    public String project(ProjectRequestDto projectRequestDto,
                          @ModelAttribute("member") SecurityUser member, Model model) {
//        model.addAttribute("projectNum", member.getMemberNum());
        projectService.createProject(projectRequestDto, member.getMemberNum());

        return "redirect:/projectList";
    }


    @GetMapping("/project/detail/{projectNum}")
    public String projectDetail(@PathVariable(value = "projectNum") Long projectNum,
                                @ModelAttribute("member") SecurityUser member, Model model,
                                @RequestParam(value = "message", required = false) String message) {
        Project project = projectService.findProject(projectNum);

        List<Task> tasks = taskService.getTaskAll(projectNum);

        List<Tag> tags =
            tagService.getTaskAll(projectNum);

        List<Milestone> mileStones =
            milestoneService.getMilestoneAll(projectNum);

        model.addAttribute("memberNum", member.getMemberNum());
        model.addAttribute("project", project);
        model.addAttribute("taskList", tasks);
        model.addAttribute("tagList", tags);
        model.addAttribute("milestoneList", mileStones);
        model.addAttribute("message", message);


        return "projectDetail";
    }
}
