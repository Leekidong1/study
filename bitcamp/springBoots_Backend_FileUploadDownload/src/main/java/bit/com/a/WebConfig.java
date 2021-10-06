package bit.com.a;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		// 접속 클라이언트를 허가
		registry.addMapping("/**").allowedOrigins("http://localhost:8090");  		//모든 경로 허가
	}

}
