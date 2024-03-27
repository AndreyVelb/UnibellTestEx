package ru.unibell.unibelltestex.controller;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.unibell.unibelltestex.controller.response.ExceptionResponse;
import ru.unibell.unibelltestex.exception.ClientAlreadyExistedException;
import ru.unibell.unibelltestex.exception.ClientNotFoundException;
import ru.unibell.unibelltestex.exception.ContactTypeNotValidException;

@RestControllerAdvice
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandlerController {


    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleConstraintViolationEx(ConstraintViolationException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        StringBuilder stringBuilder = new StringBuilder();
        ex.getConstraintViolations().forEach(constraintViolation -> stringBuilder.append(constraintViolation.getMessage()));
        return new ResponseEntity<>(new ExceptionResponse(stringBuilder.toString()), headers, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleMethodArgumentNotValidEx(MethodArgumentNotValidException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        StringBuilder stringBuilder = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(s -> stringBuilder.append(s.getDefaultMessage()));
        return new ResponseEntity<>(new ExceptionResponse(stringBuilder.toString()), headers, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ClientAlreadyExistedException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleClientAlreadyExistedEx(ClientAlreadyExistedException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ClientNotFoundException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleClientNotFoundEx(ClientNotFoundException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ContactTypeNotValidException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleContactTypeNotValidEx(ContactTypeNotValidException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }


}
