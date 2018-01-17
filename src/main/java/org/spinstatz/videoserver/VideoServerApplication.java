package org.spinstatz.videoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="org.spinstatz.*")
public class VideoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoServerApplication.class, args);
	}
}
