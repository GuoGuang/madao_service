package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @description 前台gateway服务网关
 * @author LGG
 * @create 2018-09-26 14:34
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
