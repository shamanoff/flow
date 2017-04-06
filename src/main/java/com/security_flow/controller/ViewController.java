package com.security_flow.controller;


import com.security_flow.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @Autowired
    private UserRep userRep;




 /*   @GetMapping({"/","/main"})
    public ModelAndView main(){

        ModelAndView mv = new ModelAndView("main");

        return mv;
    }*/

    @GetMapping({"/", "/index"})
    public ModelAndView index() {

      


        ModelAndView mv = new ModelAndView("index");


        return mv;

    }



}
