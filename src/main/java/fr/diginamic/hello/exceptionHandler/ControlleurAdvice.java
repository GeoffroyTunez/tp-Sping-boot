package fr.diginamic.hello.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlleurAdvice {

    @ExceptionHandler({FunctionalException.class})
    public ResponseEntity<String> traiterErreur(FunctionalException c){
        return ResponseEntity.badRequest().body(c.getMessage());
    }
}
