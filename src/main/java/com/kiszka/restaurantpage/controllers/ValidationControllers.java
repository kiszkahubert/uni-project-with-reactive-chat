package com.kiszka.restaurantpage.controllers;

import com.kiszka.restaurantpage.entity.database.UserDto;
import com.kiszka.restaurantpage.entity.database.UserInfo;
import com.kiszka.restaurantpage.entity.database.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ValidationControllers {
    private final UserService userService;
    public ValidationControllers(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login(){
        return "/validation/login";
    }
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "/validation/register";
    }
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
        UserInfo existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser!=null && existingUser.getEmail()!=null &&!existingUser.getEmail().isEmpty()){
            result.rejectValue("email","Same email exists","There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "/validation/register";
        }
        userDto.setRole("ADMIN");
        userService.saveUser(userDto);
        return "redirect:/login";
    }
}