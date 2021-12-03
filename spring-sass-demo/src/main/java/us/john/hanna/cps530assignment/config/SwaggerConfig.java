package us.john.hanna.cps530assignment.config;

import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.Annotation;
import java.lang.module.ModuleDescriptor;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("us.john.hanna.cps530assignment"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .ignoredParameterTypes(
                        Annotation.class,
                        ApplicationContext.class,
                        AutowireCapableBeanFactory.class,
                        BeanFactory.class,
                        ClassLoader.class,
                        Environment.class,
                        Module.class,
                        ModuleDescriptor.class,
                        ModuleLayer.class,
                        Package.class,
                        RedirectView.class)
                .pathMapping("/");

    }

    private ApiInfo metaData(){

        return new ApiInfoBuilder()
                .title("Spring and Sass Demo")
                .description("API and UI for Spring and Sass Demo")
                .version("1.0")
                .build();

    }

}
