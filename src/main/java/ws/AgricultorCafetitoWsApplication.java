package ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ws.jwt.RsaKeyProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class AgricultorCafetitoWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgricultorCafetitoWsApplication.class, args);
	}

}
