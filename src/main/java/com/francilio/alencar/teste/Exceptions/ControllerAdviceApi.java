package com.francilio.alencar.teste.Exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerAdviceApi {



    
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exceptio) {
        String mensagem  = "Erro inesperdo no sistema";
        return new ResponseEntity<>(mensagem, HttpStatus.valueOf(500));
    }
    
    
    
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exceptio) {
        String mensagem  = "Não foram encotrados registro para os parametros informados";
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException exceptio) {
        String mensagem  = "Destino não existe, verifique o EndPont ou o tipo de requisição!";
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
    

}
