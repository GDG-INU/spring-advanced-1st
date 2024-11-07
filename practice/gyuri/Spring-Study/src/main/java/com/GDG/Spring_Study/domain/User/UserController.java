package com.GDG.Spring_Study.domain.User;

import com.GDG.Spring_Study.domain.User.dto.UserRequestDTO;
import com.GDG.Spring_Study.domain.User.service.UserService;
import com.GDG.Spring_Study.domain.User.service.UserServiceImpl;
import com.GDG.Spring_Study.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImpl userService;

    /**
     * 회원가입
     * @param joinUserDTO
     * @return ResponseEntity<?>
     */
    @PostMapping("/joinUser")
    public ResponseEntity<?> joinUser(@RequestBody UserRequestDTO.joinUserDTO joinUserDTO) {
        return ResponseEntity.ok().body(userService.joinUser(joinUserDTO));
    }
}