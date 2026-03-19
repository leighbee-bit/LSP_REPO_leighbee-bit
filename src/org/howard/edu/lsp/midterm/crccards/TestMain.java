package org.howard.edu.lsp.midterm.crccards;

public class TestMain {
    public static void main(String[] args) {
    
        Task taskone = new Task("T1", "Write report");

        System.out.println(taskone.toString());

        taskone.setStatus("complete");

        System.out.print(taskone.toString());
}
}
