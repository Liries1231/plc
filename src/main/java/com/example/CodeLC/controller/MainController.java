package com.example.CodeLC.controller;

import com.example.CodeLC.domain.Message;
import com.example.CodeLC.domain.User;
import com.example.CodeLC.repos.MessageRepository;
import com.example.CodeLC.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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
    @PostMapping("/message/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("newText") String newText) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText(newText);
            messageRepository.save(message);
        }
        return "redirect:/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        int messId = Integer.parseInt(id);
        messageRepository.deleteById(messId);
        return "redirect:/main";

    }




}
