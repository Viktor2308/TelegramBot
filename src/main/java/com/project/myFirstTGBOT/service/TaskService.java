package com.project.myFirstTGBOT.service;

import com.project.myFirstTGBOT.entity.Task;
import com.project.myFirstTGBOT.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public void save(Task task){
        taskRepository.save(task);
    }

}
