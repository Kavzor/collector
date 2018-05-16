package nu.rolandsson.collector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import nu.rolandsson.collector.mock.WeatherProvider;
import org.hibernate.action.internal.CollectionAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
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
		URI dbUri = new URI(mDbUrl);
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath() + "?sslmode=require";
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(dbUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		
		return new HikariDataSource(hikariConfig);
	}
}
