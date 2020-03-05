package io.demo.jony.jony.enums;

public enum  TaskAction {

    assignTaskToUser("AssignTaskToUser"), getUserTasks("GetUserTasks");

    private String description;

    TaskAction(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
