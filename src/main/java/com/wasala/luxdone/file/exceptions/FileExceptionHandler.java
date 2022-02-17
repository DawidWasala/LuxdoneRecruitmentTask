package com.wasala.luxdone.file.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(value = {FileNotFoundException.class})
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

    @ExceptionHandler(value = {FileTypeNotSupportedException.class})
    public ResponseEntity<String> handleFileTypeNotSupportedException(FileTypeNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File type extension not supported");
    }
}
