package com.dilly3.multipurposedrive;

import com.dilly3.multipurposedrive.mapper.UsersMapper;
import com.dilly3.multipurposedrive.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MultipurposeDriveApplication {
@Autowired  private IUserService iUserService;



	public static void main(String[] args) {
		SpringApplication.run(MultipurposeDriveApplication.class, args);
	}


	@Bean
	CommandLineRunner runner() {

		return args -> {
//			iUserService.deleteUserByUsername("aniks23");


		};
	}
	@Bean
	public Logger logger(){
		return LoggerFactory.getLogger(this.getClass());
	}

}
