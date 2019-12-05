package net.atos.springcachingexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringCachingExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCachingExampleApplication.class, args);
	}

}
