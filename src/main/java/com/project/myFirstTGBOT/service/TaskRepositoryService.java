package com.project.myFirstTGBOT.service;

import com.project.myFirstTGBOT.entity.Task;
import com.project.myFirstTGBOT.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskRepositoryService {

    private final ReminderRepository taskRepository;

    public void save(Task task){
        taskRepository.save(task);
    }

    public List<Task> getAllTaskByChatId(long chatId){
      return taskRepository.findAllByChatId(chatId);
    }
}
