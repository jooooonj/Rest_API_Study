package com.ll.rest.boundedContext.member;

import com.ll.rest.base.rsData.RsData;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
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
}
