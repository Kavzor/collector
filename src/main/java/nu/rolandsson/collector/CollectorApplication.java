package nu.rolandsson.collector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import nu.rolandsson.collector.provider.DatasourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Configuration
@EnableScheduling
public class CollectorApplication implements WebMvcConfigurer {
	
	private final static Logger logger = Logger.getLogger(CollectorApplication.class.getName());
	public static final boolean IS_DEVELOPMENT_MODE = true;
	
	/*@Value(value = "${spring.datasource.url}")
	private String mDbUrl;
	*/
	
	
	private DatasourceProvider mDatasourceProvider;
	
	@Autowired
	public void context(@Value("${spring.datasource.url}") String url) {
		mDatasourceProvider = new DatasourceProvider(url);
	}
	
	
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
	public DataSource dataSource() {
		String[] userInfoParts = mDatasourceProvider.getUserParts();
		
		return new HikariDataSource(mDatasourceProvider.getConfig()
						.username(userInfoParts[DatasourceProvider.USER_PART_USERNAME])
						.password(userInfoParts[DatasourceProvider.USER_PART_PASSWORD])
						.sslmode(true)
						.getHikariConfig());
	}
}
