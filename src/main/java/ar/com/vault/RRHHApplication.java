package ar.com.vault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by alejandro on 16/11/18.
 */
@SpringBootApplication
@EnableJpaRepositories("ar.com.vault.repository")
public class RRHHApplication {

	public static void main(String[] args) {
		SpringApplication.run(RRHHApplication.class, args);
	}
}
