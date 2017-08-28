package com.persistencia.csv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

}
