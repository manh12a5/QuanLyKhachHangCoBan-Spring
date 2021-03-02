package config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import service.customer.CustomerService;
import service.customer.ICustomerService;

@Configuration
@EnableWebMvc
@ComponentScan("controller")
public class AppConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setApplicationContext(applicationContext);
        viewResolver.setPrefix("/WEB-INFO/customers/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public ICustomerService customerService() {
        return new CustomerService();
    }

}
