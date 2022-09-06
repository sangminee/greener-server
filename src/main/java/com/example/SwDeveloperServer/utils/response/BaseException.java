package com.example.SwDeveloperServer.utils.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception{
    private ErrorStatus errorStatus;
}
