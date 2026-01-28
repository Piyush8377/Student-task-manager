package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("src/main/resources/tasks.json");

    public List<Task> getAllTasks() {
        try {
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, new TypeReference<List<Task>>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Task addTask(Task task) {
        try {
            List<Task> tasks = getAllTasks();
            task.setId(tasks.size() + 1);
            tasks.add(task);
            mapper.writeValue(file, tasks);
            return task;
        } catch (Exception e) {
            return null;
        }
    }
}
