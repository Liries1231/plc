package com.example.CodeLC.service;

import com.example.CodeLC.domain.Message;
import com.example.CodeLC.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    public Message getBookById(int id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
    }


    public Message updateBook(int id, Message updatedBook) {
        Message existingBook = getBookById(id);

        existingBook.setText(updatedBook.getText());
        existingBook.setTag(updatedBook.getTag());
        // Обновление других полей книги

        return messageRepository.save(existingBook);

    }
}
