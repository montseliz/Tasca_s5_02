package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@OpenAPIDefinition(info = @Info(title = "Dice Game API", version = "1.0.0", description = "Documentation Dice Game RESTful API"))
public class S05T02N01LizMontseApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(S05T02N01LizMontseApplication.class, args);
	}

}
