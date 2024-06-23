package com.example.runnerz;

import com.example.runnerz.run.Location;
import com.example.runnerz.run.Run;

import com.example.runnerz.user.UserHttpClient;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.LocalDateTime;


@SpringBootApplication
public class Application {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
	/*
	@Bean
	CommandLineRunner runner() {
		return args -> {
			Run run = new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR);
			log.info("Run: {}", run);
		};
	}
	*/
	@Bean
	UserHttpClient userHttpClient() {
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}
	@Bean
	CommandLineRunner runner(UserHttpClient userHttpClient) {
		return args -> {
			log.info("Users: {}", userHttpClient.findAll());
			log.info("User 1: {}", userHttpClient.findById(1));
		};
	}
	// test git branch and merge

}
