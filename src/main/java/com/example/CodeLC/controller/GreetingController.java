package com.example.CodeLC.controller;

import com.example.CodeLC.domain.Message;
import com.example.CodeLC.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping()
    public String main(Model model) {
        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }


    @PostMapping()
    public String add(Model model, @RequestParam String text, @RequestParam String tag) {
        Message message = new Message(text, tag);
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
