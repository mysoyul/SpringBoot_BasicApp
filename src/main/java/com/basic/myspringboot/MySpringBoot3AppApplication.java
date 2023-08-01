package com.basic.myspringboot;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.entity.UserInfo;
import com.basic.myspringboot.repoository.UserInfoRepository;
import com.basic.myspringboot.repoository.UserRepository;
import com.basic.myspringboot.service.UserInfoUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
//@SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
public class MySpringBoot3AppApplication {

	public static void main(String[] args) {

		//SpringApplication.run(MySpringBoot3AppApplication.class, args);
		SpringApplication application = new SpringApplication(MySpringBoot3AppApplication.class);
		application.setWebApplicationType(WebApplicationType.SERVLET); //웹어플리케이션 타입 지정
		application.run(args);
	}

	@Bean
	public String hello() {
		return "Hello SpringBoot!!";
	}

	@Bean
	public CommandLineRunner test(UserRepository userRepository,
								  UserInfoUserDetailsService userDetailsService) {
		return args -> {
			userRepository.deleteAll();
			List<User> userList = IntStream.range(0, 5)
					.mapToObj(i -> User.builder()
							.name("spring" + i)
							.email("aa@aa.com" + i)
							.build())
					.collect(Collectors.toList());

			userRepository.saveAll(userList);

			UserInfo userInfo = UserInfo.builder()
					.name("adminboot")
					.password("pwd1")
					.email("admin@aa.com")
					.roles("ROLE_ADMIN").build();

			userDetailsService.addUser(userInfo);

			UserInfo userInfo2 = UserInfo.builder()
					.name("userboot")
					.password("pwd2")
					.email("user@aa.com")
					.roles("ROLE_USER").build();

			userDetailsService.addUser(userInfo2);

		};
	}
}
