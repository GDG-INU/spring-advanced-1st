package com.example.demo.service;

import com.example.demo.domain.Publisher;
import com.example.demo.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }
    public void deleteById(Long id){
        publisherRepository.deleteById(id);
    }
}
