package com.nhnacademy.task.repository;

import com.nhnacademy.task.dto.respond.TaskTagRespondDto;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.entity.TaskTag;
import com.nhnacademy.task.entity.pk.TaskTagPk;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTagPk> {
    List<TaskTagRespondDto> findByTask(Task task);
}
