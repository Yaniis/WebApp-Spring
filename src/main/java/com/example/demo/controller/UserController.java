package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.PlaygroundService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {
    private final UserService userService;
    private final PlaygroundService playgroundService;

    @Autowired
    public UserController(UserService userService, PlaygroundService playgroundService) {
        this.userService = userService;
        this.playgroundService = playgroundService;
    }

    @PostMapping("/api/timer/run")
    public String runHelloWorldJob(){
        playgroundService.runDeleteJob();
        return "redirect:/users";
    }

    @GetMapping(value = "/users", name = "listing")
    public String userList(Model model){

        List<User> users = userService.listAll();


        model.addAttribute("listUsers", users);
        model.addAttribute("dateDelete");
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("title", "Création");
        return "user_new_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("success", "Compte créé avec succès");
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){

        try {
           User user = userService.get(id);
           model.addAttribute("user", user);
           model.addAttribute("title", "Edition");
           return "user_new_form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Element deleted");
            return "redirect:/users";
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("error", "Element with id " + id + " does not exist");
            return "redirect:/users";
        }
    }

}
