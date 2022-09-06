package com.example.SwDeveloperServer.utils.response;

import com.example.SwDeveloperServer.utils.response.BaseResponse;
import com.example.SwDeveloperServer.utils.response.ErrorStatus;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class ResponseService {

    private final HttpHeaders headers = new HttpHeaders();

    public HttpHeaders setHeaders(){
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return headers;
    }

    public ResponseEntity<BaseResponse> errorResult(ErrorStatus errorStatus){
        BaseResponse message = new BaseResponse(errorStatus);
        return new ResponseEntity(message, setHeaders(), HttpStatus.OK);
    }

    public <T> ResponseEntity<BaseResponse> successResult(T result, SuccessStatus successCode) {
        BaseResponse message = new BaseResponse(result, successCode);
        return new ResponseEntity(message, setHeaders(), HttpStatus.OK);
    }

}
