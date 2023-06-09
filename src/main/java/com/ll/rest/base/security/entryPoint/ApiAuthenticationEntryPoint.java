package com.ll.rest.base.security.entryPoint;

import com.ll.rest.base.rsData.RsData;
import com.ll.rest.util.Ut;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//시큐리티의 예외를 핸들링 하기 위한 클래스
@Component
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        RsData rs = RsData.of("F-AccessDenied", "인증실패", null);

        response.setCharacterEncoding("UTF-8");
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(403);
        response.getWriter().append(Ut.json.toStr(rs));
    }
}
