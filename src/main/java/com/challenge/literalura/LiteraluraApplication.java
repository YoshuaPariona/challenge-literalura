package com.challenge.literalura;

import com.challenge.literalura.main.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiteraluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);

		Main main =new Main();
		main.showMenu();

	}

}
