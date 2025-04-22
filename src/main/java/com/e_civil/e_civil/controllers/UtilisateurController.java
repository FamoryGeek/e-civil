package com.e_civil.e_civil.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/users")
@CrossOrigin("*")
public class UtilisateurController {

    public String getString(){
        return "Hello World";
    }
}
