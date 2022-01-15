package expertostech.autenticacao.jwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class CorsConfiguration /*implements WebMvcConfigurer*/ {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
//    }

    //@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
                        .allowedHeaders("Access-Control-Allow-Origin", "*")
                        .allowedHeaders("Access-Control-Allow-Headers", "content-type")
                        .allowedHeaders("Access-Control-Allow-Headers", "access-control-allow-methods")
                        .allowedHeaders("Access-Control-Allow-Headers", "access-control-allow-origin")
                        //.allowedHeaders("mode", "no-cors")
                        .allowedHeaders("Access-Control-Allow-Headers", "access-control-allow-headers")
                        .allowedHeaders("Access-Control-Allow-Meaders", "Content-Type, Accept, X-Requested-With, remember-me")

                ;
            }
        };
    }
}
