package com.at.webcliente;

import org.springframework.boot.SpringApplication;

public class TestWebclienteApplication {

	public static void main(String[] args) {
		SpringApplication.from(WebclienteApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
