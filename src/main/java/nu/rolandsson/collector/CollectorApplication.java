package nu.rolandsson.collector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Configuration
public class CollectorApplication implements WebMvcConfigurer {
	
	private final static Logger logger = Logger.getLogger(CollectorApplication.class.getName());
	public static final boolean IS_DEVELOPMENT_MODE = true;
	
	@Value(value = "${spring.datasource.url}")
	private String mDbUrl;
	
	
	public static void main(String[] args) {
		logger.info("---Booting application---");
		
		SpringApplication.run(CollectorApplication.class, args);
		
		logger.info("---Application booted---");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("mock-service").allowedOrigins("*");
	}
	
	@Bean
	public DataSource dataSource() throws URISyntaxException {
		StringBuilder pathBuilder = new StringBuilder();
		URI dbUri = new URI(mDbUrl);
		
		String[] userInfoParts = dbUri.getUserInfo().split(":");
		String dbPath = pathBuilder
						.append("jdbc:postgresql://")
						.append(dbUri.getHost())
						.append(dbUri.getPath())
						.append("?sslmode=require")
						.toString();
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(dbPath);
		hikariConfig.setUsername(userInfoParts[0]);
		hikariConfig.setPassword(userInfoParts[1]);
		hikariConfig.setMaximumPoolSize(5);
		
		return new HikariDataSource(hikariConfig);
	}
}
