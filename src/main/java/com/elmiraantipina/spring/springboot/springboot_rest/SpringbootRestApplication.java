package com.elmiraantipina.spring.springboot.springboot_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApplication.class, args);
		//при запуске автоматически создается Spring-контейнер, происходит сканирование и поиск бинов,
		// и регистрация их в Spring-контейнер, после чего поднимается Tomcat-сервер, на котором запускается приложение


	}

}
