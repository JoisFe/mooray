package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.adaptor.TagAdaptor;
import com.nhnacademy.gateway.domain.Tag;
import com.nhnacademy.gateway.service.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagAdaptor tagAdaptor;

    @Override
    public String createTag(String tagTitle, Long projectNum) {
        return tagAdaptor.createTag(tagTitle, projectNum);
    }

    @Override
    public List<Tag> getTaskAll(Long projectNum) {
        return tagAdaptor.findAllTag(projectNum);
    }

    @Override
    public String update(String tagTitle, Long projectNum, Long tagNum) {
        return tagAdaptor.update(tagTitle, projectNum, tagNum);
    }

    @Override
    public String delete(Long projectNum, Long tagNum) {
        return tagAdaptor.delete(projectNum, tagNum);
    }

    @Override
    public List<Tag> getTagInProject(Long projectNum, Long taskNum) {
        return tagAdaptor.findTagByProjectNum(projectNum, taskNum);
    }

}
