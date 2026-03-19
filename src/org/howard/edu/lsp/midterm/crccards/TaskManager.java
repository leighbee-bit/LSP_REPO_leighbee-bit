package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects, supporting add, find,
 * and status-based retrieval operations.
 *
 * @author Your Name
 */
public class TaskManager {

    /** Internal store: taskId -> Task. Prevents duplicates by key uniqueness. */
    private final Map<String, Task> tasks = new HashMap<>();

    /**
     * Adds a task to the manager.
     * 
     * @param task the Task to add
     * @throws IllegalArgumentException if a task with the same taskId already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException(
                "Task with ID " + task.getTaskId() + " already exists."
            );
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds a task by its taskId.
     *
     * @param taskId the ID to search for
     * @return the matching Task, or null if not found
     */
    public Task findTask(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Returns all tasks whose status matches the given value.
     *
     * @param status the status string to filter by (e.g. "OPEN", "IN_PROGRESS")
     * @return a List of Task objects with the matching status; empty list if none found
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks.values()) {
            if (t.getStatus().equals(status)) {
                result.add(t);
            }
        }
        return result;
    }
}