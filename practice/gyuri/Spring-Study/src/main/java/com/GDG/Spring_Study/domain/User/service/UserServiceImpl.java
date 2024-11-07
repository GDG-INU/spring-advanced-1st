package com.GDG.Spring_Study.domain.User.service;

import com.GDG.Spring_Study.domain.User.UserRepository;
import com.GDG.Spring_Study.domain.User.dto.UserRequestDTO;
import com.GDG.Spring_Study.entitiy.Users;
import com.GDG.Spring_Study.global.response.ApiResponse;
import com.GDG.Spring_Study.global.response.resEnum.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    /**
     * 회원가입
     * @param joinUserDTO
     * @return ApiResponse<?> 회원가입 성공 String
     */
    @Override
    @Transactional
    public ApiResponse<?> joinUser(UserRequestDTO.joinUserDTO joinUserDTO) {
        Users users = Users.builder()
                .name(joinUserDTO.getName())
                .email(joinUserDTO.getEmail())
                .passwd(joinUserDTO.getPasswd())
                .major(joinUserDTO.getMajor())
                .build();
        userRepository.save(users);
        return ApiResponse.SUCCESS(SuccessCode.CREATE_USER);
    }
}