package com.example.department_service.advisor;

import com.example.department_service.util.response.StandardResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(ChangeSetPersister.NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        404,
                        "Error occurred!",
                        e.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }
}
