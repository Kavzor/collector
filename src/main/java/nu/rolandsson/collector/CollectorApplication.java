package nu.rolandsson.collector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import nu.rolandsson.collector.mock.WeatherProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@ComponentScan
public class CollectorApplication {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	public static final boolean IS_DEVELOPMENT_MODE = true;

	public static void main(String[] args) {
		//SpringApplication.run(CollectorApplication.class, args);
		var context = new AnnotationConfigApplicationContext(MockConfig.class);
		var provider = context.getBean(WeatherProvider.class);
		System.out.println(provider.getWeather());
	}
	
	@Bean
	public DataSource dataSource() throws SQLException {
		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			return new HikariDataSource(config);
		}
	}
}
