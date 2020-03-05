package io.demo.jony.jony.enums;

public enum TaskLogic {
    offerTask("OfferTask"), getTasks("GetTasks");

    private String description;

    TaskLogic(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
