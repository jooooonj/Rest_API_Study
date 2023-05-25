package com.ll.rest.base.springDoc;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

//swagger 설정
@Configuration
@OpenAPIDefinition(info = @Info(title = "REST API", version = "v1")) //큰 제목 커스터마이징
@SecurityScheme( // 로그인 권한이 필요한 API의 경우에 swagger에서 토큰을 통해 권한을 받을 수 있게끔 설정
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringDocConfig {

}
