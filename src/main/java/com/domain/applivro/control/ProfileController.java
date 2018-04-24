package com.domain.applivro.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.applivro.model.Profile;
import com.domain.applivro.service.ProfileService;

@Controller
public class ProfileController {
     
    @Autowired
    private ProfileService service;
     
    @GetMapping("/")
    public ModelAndView findAll() {
         
        ModelAndView mv = new ModelAndView("profile");
        mv.addObject("profiles", service.findAll());
        
        service.teste();
         
        return mv;
    }
    
    @GetMapping("/add")
    public ModelAndView add(Profile profile) {
        ModelAndView mv = new ModelAndView("/profileAdd");
        mv.addObject("profile", profile);
        return mv;
    }
     
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
         
        return add(service.findOne(id));
    }
     
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Profile profile) {
         
        service.delete(profile);
         
        return findAll();
    }
 
    @PostMapping("/save")
    public ModelAndView save(@Valid Profile profile, BindingResult result) {
         
        if(result.hasErrors()) {
            return add(profile);
        }
         
        service.save(profile);
         
        return findAll();
    }
     
}
