package connect.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 2023-05-03 / domain-messenger / BaseTime 적용하기 위함
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class );
    }
}
