package tech.surfer.fresh;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan("tech.surfer.fresh")
@OpenAPIDefinition(info = @Info(title = "FreshFood REST API", version="1.0", description = "FreshFood REST API"),
		servers = @Server(url = "${server.servlet.context-path}/", description = "base URL"))
public class FreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreshApplication.class, args);
	}

}
