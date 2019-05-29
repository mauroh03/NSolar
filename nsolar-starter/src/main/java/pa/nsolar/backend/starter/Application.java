package pa.nsolar.backend.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Mauro Herrera on 31/03/2019.
 */

@SpringBootApplication(scanBasePackages  = {
                "pa.nsolar.backend.*"
        		})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        TimeZone.setDefault(TimeZone.getTimeZone("America/Panama"));
        Locale.setDefault(Locale.forLanguageTag("es_PA"));
    }
}
