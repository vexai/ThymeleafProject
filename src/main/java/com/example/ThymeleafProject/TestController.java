package com.example.ThymeleafProject;

import com.example.ThymeleafProject.model.User;
import com.example.ThymeleafProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    public class NotFoundException extends RuntimeException {

    }

    @Autowired
    UserService userService;

    @GetMapping("/listUsers")
    public String test(Model model) {
//        User user = new User(13, "Dominik", "prz", 1234);
//        List<User> users = new ArrayList<>();
//        users.add(new User(121, "SASASAS", "Prz", 324));
//        users.add(new User(123, "Wsafaf", "Asf", 1987));
//        users.add(new User(136, "ASsfsaf", "Faasf", 1643));
//        users.add(new User(130, "Klkajsd", "Zasd", 876));
        model.addAttribute("users", userService.listUsers());
        return "list-user-view";
    }

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable int id, Model model) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NotFoundException();
        }
        model.addAttribute("user", user);
        return "user-details";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/addUser")
    public String createUser(@Valid @ModelAttribute User user,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user",user);
            return "add-user";
        }
        userService.createUser(user.getImie(), user.getNazwisko(), user.getWiek());
        return "redirect:/listUsers";
    }



    @ExceptionHandler(NotFoundException.class)
    public String notFound() {
        return "404";
    }

}