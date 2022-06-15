package com.nhnacademy.gateway.adaptor;

import com.nhnacademy.gateway.domain.Tag;
import java.util.List;

public interface TagAdaptor {
    String createTag(String tagTitle, Long projectNum);

    List<Tag> findAllTag(Long projectNum);

    String update(String tagTitle, Long projectNum, Long tagNum);

    String delete(Long projectNum, Long tagNum);

    List<Tag> findTagByProjectNum(Long projectNum, Long taskNum);
}
