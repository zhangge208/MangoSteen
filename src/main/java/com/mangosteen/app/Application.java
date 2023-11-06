package com.mangosteen.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "MangoSteen Service", version = "v1", description = "Backend APIs for MangoSteen App"))
public class Application {
    // 入口
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
