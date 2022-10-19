package com.example.SwDeveloperServer.domain.toDoList.enums;

public enum ETodo {
    e1("메일함 정리하기"),
    e2("노트북 전원 끄기"),
    e3("사진 용량 줄이기");

    private final String value;

    ETodo(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }

}
