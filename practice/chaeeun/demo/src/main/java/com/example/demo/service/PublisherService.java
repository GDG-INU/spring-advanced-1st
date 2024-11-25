package com.example.demo.service;

import com.example.demo.domain.Publisher;
import com.example.demo.dto.PublisherDTO;
import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    // 중복 확인
    private void validateDuplicateAuthor(String name){
        if(publisherRepository.existsByName(name)){
            throw new DuplicateEntityException("이미 등록된 출판사입니다.");
        }
    }
    // DTO로 받아서 엔티티로 변환 후 저장하고, 저장된 엔티티를 다시 DTO로 변환해서 반환
    @Transactional
    public PublisherDTO savePublisher(PublisherDTO publisherDTO) {
        validateDuplicateAuthor(publisherDTO.getName());
        Publisher publisher = new Publisher(publisherDTO.getName());
        Publisher savedPublisher = publisherRepository.save(publisher);
        return new PublisherDTO(savedPublisher.getId(), publisherDTO.getName());
    }

    // 주어진 id로 Publisher 엔티티를 데이터베이스에서 찾아오고
    // 찾은 Publisher 엔티티를 PublisherDTO로 변환한다.
    @Transactional(readOnly = true)
    public Optional<PublisherDTO> findById(Long id) {
        return publisherRepository.findById(id)
                .map(publisher -> new PublisherDTO(publisher.getId(), publisher.getName()));
    }

    // 데이터베이스에서 모든 Publisher 엔티티를 가져와서
    // PublisherDTO로 변환하여 반환한다.
    @Transactional(readOnly = true)
    public List<PublisherDTO> findAll() {
        return publisherRepository.findAll().stream()
                .map(publisher -> new PublisherDTO(publisher.getId(), publisher.getName()))
                .toList();
    }

    @Transactional
    public void deleteById(Long id){
        publisherRepository.deleteById(id);
    }
}
