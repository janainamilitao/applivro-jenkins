package com.domain.applivro.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domain.applivro.model.Profile;
import com.domain.applivro.service.ProfileService;
import com.domain.applivro.util.MessageKey;
import com.domain.applivro.util.WebMessage;
import com.domain.applivro.validators.ProfileValidator;

@Controller
public class ProfileController {
     
    @Autowired
    private ProfileService service;
    
	private static final String PATH = "pages/";
	
    private final ProfileValidator validator;
	private final WebMessage webMessage;
	
	public ProfileController(ProfileValidator validator, WebMessage webMessage) {
		this.validator = validator;
		this.webMessage = webMessage;
	}
     
    @GetMapping("/")
    public ModelAndView findAll() {
         
        ModelAndView mv = new ModelAndView(PATH+"profile");
        mv.addObject("profiles", service.findAll());         
        return mv;
    }
    
    @GetMapping("/add")
    public ModelAndView addProfile(Profile profile) {
          ModelAndView mv = add(profile);
          mv.addObject("title", "Create Profile");
          return mv;
    }
     
   
    public ModelAndView add(Profile profile) {
        ModelAndView mv = new ModelAndView(PATH+"profileAdd");
        mv.addObject("profile", profile);
        return mv;
    }
     
     
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
          ModelAndView mv = add(service.findOne(id));
          mv.addObject("title", "Update Profile");
          return mv;
    }
     
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Profile profile) {
         
        service.delete(profile);
         
        return findAll();
    }
 
    @PostMapping("/save")
    public ModelAndView save(final Profile profile, final BindingResult result, Model model) throws Exception {
    	
    	this.validator.validate( profile, result );
         //Mensagem de erro
        if(result.hasErrors()) {
        	//this.webMessage.addError(model, messageKey, params);
            return add(profile);
        }
         
        service.save(profile);         
        this.webMessage.addSuccess( model, MessageKey.Success.SUCCESS_OPERATION.getKey() );      
        return findAll();
    }
     
}
