package pa.nsolar.backend.starter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Value("${nsolar.base.endpoint}")
    private String apiBaseUrl;
	
    private static final String HTTPURL= "https://github.com/mauroh03";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex(apiBaseUrl + ".*"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {

        Contact contact = new Contact("Mauro Herrera", HTTPURL, "mauro-507@hotmail.com");

        return new ApiInfo(
                "API Rest de N-Solar",
                "Aqui viven los APIs de N-Solar",
                "0.0.1", HTTPURL,
                contact,
                "Copyright", HTTPURL);
    }
}
