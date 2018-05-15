package nu.rolandsson.collector;

import nu.rolandsson.collector.mock.WeatherProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Logger;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class CollectorApplication implements WebMvcConfigurer {
	
	private final static Logger logger = Logger.getLogger(CollectorApplication.class.getName());
	
	//@Value("${spring.datasource.url}")
	private String dbUrl;
	
	public static final boolean IS_DEVELOPMENT_MODE = true;

	public static void main(String[] args) {
		logger.info("---Booting application---");
		
		SpringApplication.run(CollectorApplication.class, args);
		
		logger.info("---Application booted---");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("mock-service").allowedOrigins("*");
	}
	
	/*@Bean
	public DataSource dataSource() throws URISyntaxException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(dbUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		
		return new HikariDataSource(hikariConfig);
	}
	
	/*@Bean
	public DataSource dataSource() throws SQLException {
		System.out.println("Database adress: " + dbUrl);
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	}*/
}
