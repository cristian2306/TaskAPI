package edu.eci.escuelaing.ieti.taskapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.escuelaing.ieti.taskapi.entities.Task;

public interface TaskService{

    /**
     * Crea(Ingresa) una tarea con los atributos dados en el objeto
     * @param task Tarea a crear
     * @return Tarea creada
     */
    Task create( Task task );

    /**
     * Busca la tarea dado su id
     * @param id Id de la tarea a buscar
     * @return Tarea con el id dado
     */
    Task findById( String id );
    
    /**
     * Busca todas las tareas creadas
     * @return Lista con todas las tareas creadas
     */
    List<Task> getAll();

    /**
     * Elimina una tarea dado su id
     * @param id id de la tarea a eliminar
     * @return "true" si la tarea se elimina "false" de lo contrario
     */
    boolean deleteById( String id );

    /**
     * Actualiza la tarea indicada con el id, con la informacion dada en el objeto tarea
     * @param task Informacion a actualizar en la tarea
     * @param id Id de la tarea a actualizar
     * @return Tarea actualizada
     */
    Task update( Task task, String id );
}
