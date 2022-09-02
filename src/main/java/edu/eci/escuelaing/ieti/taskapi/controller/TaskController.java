package edu.eci.escuelaing.ieti.taskapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.escuelaing.ieti.taskapi.dto.TaskDto;
import edu.eci.escuelaing.ieti.taskapi.entities.Task;
import edu.eci.escuelaing.ieti.taskapi.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService;
    private ModelMapper modelMapper = new ModelMapper(); 

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        ArrayList<TaskDto> tasksDto = new ArrayList<>();
        List<Task> tasks = taskService.getAll();
        for(Task task:tasks){
            tasksDto.add(modelMapper.map(task,TaskDto.class));
        }
        return new ResponseEntity<>(tasksDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findByid(@PathVariable String id){
        TaskDto task = modelMapper.map(taskService.findById(id),TaskDto.class);
        return new ResponseEntity<>(task,HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        System.out.println("Task a crear: "+ taskDto.getId());
        taskService.create(modelMapper.map(taskDto,Task.class));
        TaskDto taskDto2 = modelMapper.map(taskService.findById(taskDto.getId()),TaskDto.class);
        return new ResponseEntity<>(taskDto2,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable String id){
        Task newTask = modelMapper.map(taskDto,Task.class);
        TaskDto updTaskDto = modelMapper.map(taskService.update(newTask, id),TaskDto.class);
        return new ResponseEntity<>(updTaskDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable String id){
        return new ResponseEntity<>(taskService.deleteById(id),HttpStatus.ACCEPTED);
    }
}
