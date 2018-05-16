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
	
	private String mDbUrl;
	
	/*
	@Autowired
	public CollectorApplication(
					@Value("${spring.datasource.url}") String dbUrl) {
		logger.info("Autowiring db url " + dbUrl);
		mDbUrl = "postgres://mfulifashqqiez:5c3e59be9c7b94da556ae9e323e4cb3cfe32807cb042232900f19385c23d0baf@ec2-54-225-200-15.compute-1.amazonaws.com:5432/dblg5c2kt8bu54";
	}
	*/
	
	public static void main(String[] args) {
		logger.info("---Booting application---");
		
		SpringApplication.run(CollectorApplication.class, args);
		
		logger.info("---Application booted---");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("mock-service").allowedOrigins("*");
	}
	
	
	
	/*
	@Bean
	public DataSource dataSource() throws URISyntaxException {
		//String db = System.getenv("DATABASE_URL");
		//logger.info("DATABASE_URL: " + db);
		URI dbUri = new URI("postgres://xquiqydqgjcopu:15749197bb123847de32bbdb345c223f912dd1b7aff3f192e23466ee6d11c77e@ec2-54-225-96-191.compute-1.amazonaws.com:5432/dds89j3afiv713");
		//URI dbUri = new URI(db);
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		
		logger.info("Connecting to " + dbUrl);
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(dbUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		
		return new HikariDataSource(hikariConfig);
	}*/
	
	/*@Bean
	public DataSource dataSource() throws SQLException {
		logger.info("Setting up datasource on " + mDbUrl);
		if (mDbUrl == null || mDbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(mDbUrl);
			return new HikariDataSource(config);
		}
	}*/
}
