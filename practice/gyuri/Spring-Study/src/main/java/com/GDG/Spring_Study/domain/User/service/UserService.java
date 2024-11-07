package com.GDG.Spring_Study.domain.User.service;

import com.GDG.Spring_Study.domain.User.dto.UserRequestDTO;
import com.GDG.Spring_Study.global.response.ApiResponse;

public interface UserService {
    ApiResponse<?> joinUser(UserRequestDTO.joinUserDTO joinUserDTO); // 회원가입
}
