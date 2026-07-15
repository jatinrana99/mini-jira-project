package com.dummy.jira.Config;
package com.dummy.jira.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:3000",
                                "https://YOUR-VERCEL-URL.vercel.app"
                        )
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
