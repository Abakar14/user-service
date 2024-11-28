package com.bytmasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class Application {

public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
}

}
