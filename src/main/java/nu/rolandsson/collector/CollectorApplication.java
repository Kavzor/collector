package nu.rolandsson.collector;

import nu.rolandsson.collector.mock.WeatherProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class CollectorApplication {
	
	//@Value("${spring.datasource.url}")
	private String dbUrl;
	
	public static final boolean IS_DEVELOPMENT_MODE = true;

	public static void main(String[] args) {
		SpringApplication.run(CollectorApplication.class, args);
		//var context = new AnnotationConfigApplicationContext(MockConfig.class);
		//var provider = context.getBean(WeatherProvider.class);
		//System.out.println(provider.getWeather());
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
