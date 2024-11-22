package fr.diginamic.hello.controleurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
@RequestMapping("/hello")
public class HelloControleur {

    HelloService helloService = new HelloService()  ;

    @GetMapping
    public String direHello(){
        return helloService.salutation();
    }
}
