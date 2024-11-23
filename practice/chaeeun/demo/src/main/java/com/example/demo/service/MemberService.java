package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberDTO;
import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;



@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDTO registerMember(MemberDTO memberDTO) {
        if(memberRepository.existsByEmail(memberDTO.getEmail())) {
            log.error("이미 등록된 회원입니다. email={}", memberDTO.getEmail());
            throw new DuplicateEntityException("이미 등록된 회원입니다.");

        }
        Member member = new Member(memberDTO.getName(), memberDTO.getEmail());
        Member savedMember = memberRepository.save(member);
        log.info("회원 등록에 성공하였습니다. id:{}, name:{}, email:{}", savedMember.getId(), savedMember.getName(), memberDTO.getEmail());
        return new MemberDTO(savedMember.getId(), savedMember.getName(), savedMember.getEmail());
    }

    // 주어진 id로 Member 엔티티를 데이터베이스에서 찾아오고
    // 찾은 Member 엔티티를 AuthorDTO로 변환한다.
    public Optional<MemberDTO> findById(Long id) {
        return memberRepository.findById(id)
                .map(member -> new MemberDTO(member.getId(), member.getName(), member.getEmail()));
    }

    // 회원 삭제
    public void deleteMember(Long id) {
        log.info("회원 삭제에 성공하였습니다. id={}", id);
        memberRepository.deleteById(id);
    }

    // 회원이 대여 중인 책 목록 조회
}
