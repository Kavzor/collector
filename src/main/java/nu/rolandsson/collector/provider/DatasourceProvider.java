package nu.rolandsson.collector.provider;

import com.zaxxer.hikari.HikariConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class DatasourceProvider {
  
  public static final int USER_PART_USERNAME = 0;
  public static final int USER_PART_PASSWORD = 1;
  public static final int DEFAULT_POOL_SIZE = 5;
  
  private static final Logger logger = Logger.getLogger(DatasourceProvider.class.getName());
  
  private HikariConfig mConfig;
  private URI mDatasourcePath;
  
  public DatasourceProvider(String path) {
    try {
      mDatasourcePath = new URI(path);
      mConfig = new HikariConfig();
    } catch (URISyntaxException e) {
      logger.severe("Failed to understand path " + path);
      e.printStackTrace();
    }
  }
  
  public URI getDatasourcePath() {
    return mDatasourcePath;
  }
  
  public String getJdbcPath() {
    return extractJdbcUrl(mDatasourcePath);
  }
  
  public String getUserInfo() {
    return getDatasourcePath().getUserInfo();
  }
  
  public String[] getUserParts() {
    return getUserInfo().split(":");
  }
  
  private static String extractJdbcUrl(URI pathURI) {
    return new StringBuilder()
            .append("jdbc:postgresql://")
            .append(pathURI.getHost())
            .append(pathURI.getPath())
            .toString();
  }
  
  public Configuration getConfig() {
    return new Configuration();
  }
  
  public class Configuration {
    private boolean mIsSslmode;
    private int mPoolSize = DEFAULT_POOL_SIZE;
    
    public Configuration username(String username) {
      mConfig.setUsername(username);
      return this;
    }
    
    public Configuration password(String password) {
      mConfig.setPassword(password);
      return this;
    }
    
    public Configuration sslmode(boolean isSsslmode) {
      mIsSslmode = isSsslmode;
      return this;
    }
    
    public Configuration poolSize(int size) {
      mPoolSize = size;
      return this;
    }
    
    public HikariConfig getHikariConfig() {
      mConfig.setJdbcUrl(mIsSslmode ? getJdbcPath() + "?sslmode=require" : getJdbcPath());
      mConfig.setMaximumPoolSize(mPoolSize);
      return mConfig;
    }
  }
}
