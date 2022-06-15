package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.domain.Tag;
import java.util.List;

public interface TagService {
    String createTag(String tagTitle, Long projectNum);

    List<Tag> getTaskAll(Long projectNum);

    String update(String tagTitle, Long projectNum, Long tagNum);

    String delete(Long projectNum, Long tagNum);

    List<Tag> getTagInProject(Long projectNum, Long taskNum);

}
