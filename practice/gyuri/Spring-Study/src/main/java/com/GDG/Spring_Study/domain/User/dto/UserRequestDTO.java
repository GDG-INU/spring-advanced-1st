package com.GDG.Spring_Study.domain.User.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDTO {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class joinUserDTO {
        private String name;
        private String email;
        private String passwd;
        private String major;
    }
}
