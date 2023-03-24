package com.project.myFirstTGBOT.repository;

import com.project.myFirstTGBOT.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByTaskDateTime(LocalDateTime localDateTime);

    List<Task> findAllByTaskDateTimeAndChatId(LocalDateTime localDateTime, long chatId);
}