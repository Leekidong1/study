package bit.com.a;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "bit.com.a")
public class SprBootSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprBootSampleApplication.class, args);
	}

}
