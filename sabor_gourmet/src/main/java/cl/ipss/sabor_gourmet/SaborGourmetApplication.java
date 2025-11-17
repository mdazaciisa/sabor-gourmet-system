package cl.ipss.sabor_gourmet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SaborGourmetApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SaborGourmetApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// Redirige la raíz "/" a "/admin" para evitar 404 de recurso estático
		registry.addRedirectViewController("/", "/admin");
	}

}
