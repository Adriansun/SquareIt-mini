package square.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SquareItApplication extends SpringBootServletInitializer {

  /**
   * App starter, a JAR file.
   * @param args args
   */
  public static void main(String[] args) {
    SpringApplication.run(SquareItApplication.class, args);
  }

  /**
   * Used when making a WAR file.
   * @param builder builder
   * @return the war
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(SquareItApplication.class);
  }
}
