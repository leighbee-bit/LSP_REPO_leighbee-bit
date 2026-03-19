package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a single task with an ID, description, and status.
 *
 * @author Leighla-Marie Dantes
 */

public class Task {

    private String taskId;
    private String description;
    private String status = "OPEN";

    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of this task. Valid values are OPEN, IN_PROGRESS, and
     * COMPLETE.
     * Any other value results in UNKNOWN.
     *
     * @param status the new status string
     */

    public void setStatus(String status) {
        switch (status) {
            case "OPEN":
                this.status = status;
                break;
            case "IN_PROGRESS":
                this.status = status;
                break;
            case "COMPLETE":
                this.status = status;
                break;
            default:
                this.status = "UNKNOWN";
        }
    }

    public String getStatus() {
        return this.status;
    }

    public String toString() {
        String final_status = this.taskId + " " + this.description + " " +
                "[" + this.status + "]";
        return final_status;
    }

}