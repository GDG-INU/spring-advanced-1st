package com.example.demo.controller;

import com.example.demo.dto.PublisherDTO;
import com.example.demo.service.PublisherService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @PostMapping
    public ResponseEntity<PublisherDTO> createPublisher(@Valid @RequestBody PublisherDTO publisherDTO) {
        PublisherDTO savedPublisher = publisherService.savePublisher(publisherDTO);
        log.info("새로운 출판사가 등록되었습니다: {}", savedPublisher.getName());
        return ResponseEntity.ok(savedPublisher);
    }


   @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisher(@PathVariable("id") Long id) {
        return publisherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
   }

   @GetMapping
    public List<PublisherDTO> getAllPublishers() {
        List<PublisherDTO> publishers = publisherService.findAll();
        return publishers;

   }

   // 204 not content
    // -> 요청이 성공적으로 처리되었으나 응답 본문에는 아무런 내용이 없다.
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable("id") Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 not content
   }
}