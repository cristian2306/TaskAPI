package edu.eci.escuelaing.ieti.taskapi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.eci.escuelaing.ieti.taskapi.entities.Task;

@Service
public class TaskServiceImp implements TaskService {

    private HashMap<String,Task> tasks;

    public TaskServiceImp(){
        tasks = new HashMap<String,Task>();
    }

    public Task create(Task task) {
        if(tasks.containsKey(task.getId())){
            return tasks.get(task.getId());
        }else{
            tasks.put(task.getId(),task);
            System.out.println("Tarea Creada: "+ tasks.get(task.getId()).getName());
            return tasks.get(task.getId());
        }
    }

    @Override
    public Task findById(String id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> getAll() {
        List<Task> findTasks = new ArrayList<>(tasks.values());
        return findTasks;
    }

    @Override
    public boolean deleteById(String id) {
        if(tasks.remove(id) != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Task update(Task task, String id) {
        Task old = tasks.get(id);
        if(tasks.replace(id, old, task)){
            return task;
        }else{
            return old;
        }
    }
    
}
