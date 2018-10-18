package pl.swztz.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
/**
 * Glowna klasa uruchomieniowa aplikacji
 * @author SWZTZ Team
 */
@SpringBootApplication
public class PortalApplication extends SpringBootServletInitializer {

	/**
	 * Glowna metoda uruchomieniowa aplikacji
	 * @param args Parametry startowe
	 */
	private static ConfigurableApplicationContext context;
	public static void main(String[] args) {
		context = SpringApplication.run(PortalApplication.class,args);
	}
	
	private static Class<PortalApplication> 
	applicationClass = PortalApplication.class;
		
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
	
	public static ConfigurableApplicationContext getApplicationContext() {
		return context;
	}
}