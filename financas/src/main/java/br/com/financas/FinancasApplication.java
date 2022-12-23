package br.com.financas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class FinancasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancasApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("teste"));
	}

}
