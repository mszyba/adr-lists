package eu.michalszyba.adrlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AdrListApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(AdrListApplication.class, args);
			}
}
