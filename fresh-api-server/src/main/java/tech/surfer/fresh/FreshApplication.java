package tech.surfer.fresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("tech.surfer.fresh")
public class FreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreshApplication.class, args);
	}

}
