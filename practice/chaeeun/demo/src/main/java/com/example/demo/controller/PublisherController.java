package com.example.demo.controller;

import com.example.demo.domain.Publisher;
import com.example.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

   @PostMapping
    public Publisher createAuthor(@RequestBody Publisher publisher) {
       return publisherService.savePublisher(publisher);
   }

   @GetMapping("/{id}")
    public Optional<Publisher> getPublisher(@PathVariable("id") Long id) {
        return publisherService.findById(id);
   }

   @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.findAll();
   }

   @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable("id") Long id) {
        publisherService.deleteById(id);
   }
}
