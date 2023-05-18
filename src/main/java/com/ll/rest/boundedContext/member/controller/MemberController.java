package com.ll.rest.boundedContext.member.controller;

import com.ll.rest.base.rsData.RsData;
import com.ll.rest.boundedContext.member.dto.MemberDto;
import com.ll.rest.boundedContext.member.entity.Member;
import com.ll.rest.boundedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/member", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class MemberController {
    private final MemberService memberService;

    @Data
    public static class LoginRequest {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class LoginResponse{
        private final String accessToken;
    }

    @PostMapping("/login")
    public RsData<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse res) {
        String accessToken = memberService.genAccessToken(loginRequest.username, loginRequest.password);

        return RsData.of("S-1", "엑세스 토큰이 발급되었습니다.", new LoginResponse(accessToken));
    }

    @Getter
    @AllArgsConstructor
    public static class MeResponse{
        private final MemberDto member;
    }

    @GetMapping(value ="/me", consumes=ALL_VALUE)
    public RsData<MeResponse> me() {
        Member member = memberService.findByUsername("user1").get();

        return RsData.of("S-1",
                "성공",
                new MeResponse(new MemberDto(member)));
    }
}