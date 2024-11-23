package com.example.demo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    @NotBlank(message = "회원 이름은 비워둘 수 없습니다.")
    private String name;
    @NotBlank(message = "이메일은 비워둘 수 없습니다.")
    @Email
    private String email;

}
