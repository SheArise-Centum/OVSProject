package com.votingsystem;

//import com.eduonix.votingsystem.entity.Citizen;
//import com.eduonix.votingsystem.entity.Citizen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@ComponentScan
//@EntityScan(basePackageClasses=Citizen.class)
public class VotingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingsystemApplication.class, args);
	}
}
