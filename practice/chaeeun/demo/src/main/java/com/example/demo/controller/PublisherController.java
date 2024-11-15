package com.example.demo.controller;

import com.example.demo.domain.Publisher;
import com.example.demo.dto.PublisherDTO;
import com.example.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

   @PostMapping
    public PublisherDTO createAuthor(@RequestBody PublisherDTO publisherDTO) {
       return publisherService.savePublisher(publisherDTO);
   }

   @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisher(@PathVariable("id") Long id) {
        return publisherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
   }

   @GetMapping
    public List<PublisherDTO> getAllPublishers() {
        return publisherService.findAll();
   }

   // 204 not content
    // -> 요청이 성공적으로 처리되었으나 응답 본문에는 아무런 내용이 없다.
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable("id") Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 not content
   }
}
