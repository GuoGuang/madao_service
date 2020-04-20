package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 前台gateway服务网关
 **/

@SpringBootApplication
public class PlatFormApplication {


	public static void main(String[] args) {
		SpringApplication.run(PlatFormApplication.class, args);
	}


	/*@Bean
	public JWTAuthentication jwtUtil() {
		return new JWTAuthentication();
	}*/
}
