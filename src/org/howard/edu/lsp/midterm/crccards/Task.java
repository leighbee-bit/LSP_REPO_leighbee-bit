package org.howard.edu.lsp.midterm.crccards;

public class Task {

    String taskId;
    String description;
    String status = "OPEN";

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
                this.status="UNKNOWN";
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