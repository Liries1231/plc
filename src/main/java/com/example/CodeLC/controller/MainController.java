package com.example.CodeLC.controller;

import com.example.CodeLC.domain.Message;
import com.example.CodeLC.domain.User;
import com.example.CodeLC.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;
    @GetMapping()
    public String greeting(
            Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }


    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      Model model, @RequestParam String text, @RequestParam String tag) {
        Message message = new Message(text, tag, user);

        messageRepository.save(message);


        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }


    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model) {

        Iterable<Message> messages = messageRepository.findByTag(filter);
        model.addAttribute("messages", messages);
        return "main";

    }


}
