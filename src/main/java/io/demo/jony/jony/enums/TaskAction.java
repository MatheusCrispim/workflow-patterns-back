package io.demo.jony.jony.enums;

public enum  TaskAction {

    offerTaskToUser("offerTaskToUser"), getUserTasks("getUserTasks");

    private String description;

    TaskAction(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
