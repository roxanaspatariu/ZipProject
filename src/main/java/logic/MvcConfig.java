package logic;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by P3700665 on 6/1/2016.
 */

    @Configuration
    public class MvcConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/home").setViewName("home");
            registry.addViewController("/").setViewName("home");
            registry.addViewController("/index").setViewName("index");
            registry.addViewController("/login").setViewName("login");
            registry.addViewController("/upload").setViewName("upload");
        }


}
