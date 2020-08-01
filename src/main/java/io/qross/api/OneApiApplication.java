package io.qross.api;

import io.qross.app.VoyagerResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OneApiApplication {
	public static void main(String[] args) {

		io.qross.app.Setting.handleArguments(args);

		SpringApplication.run(OneApiApplication.class, args);
	}

	//使用 Voyager 模板引擎
	@Bean
	public VoyagerResolver initVoyagerResolver(){
		return new VoyagerResolver();
	}
}
