package com.ums.umsbackend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Naveen on 15/07/2017.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){

        return "index";
    }

}
