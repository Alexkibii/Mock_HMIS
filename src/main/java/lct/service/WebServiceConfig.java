package lct.service;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */

/*
-@EnableWs defines this bean as a potential source of WebService
-@Configuration annotation identifies this class as a potential source of bean definitions
-two ethods to be implemented in this class;MessageDispatcherServlet and Wsdl11Definition
 */
@EnableWs
@Configuration
public class WebServiceConfig {

    /*
    -@Bean annotation identifies this method as a bean resource and adds it the Application container
    -MessageDispatcherServlet adds support for http. It handles the http protocol
    -It is important to pass in current ApplicationContext so that other beans within the application can be accessed
     */
    @Bean
    public ServletRegistrationBean messageDispactherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet dispatcherServlet = new MessageDispatcherServlet();
        dispatcherServlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(dispatcherServlet);
    }

    /*
    -this method exposes default WSDL 1.1 using the specified wsdl file
    -@Bean attribute name is the name of the wsdl file

     */
    @Bean(name = "lct")
    public Wsdl11Definition wsdl11Definition(){
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/lct.wsdl"));
        return wsdl11Definition;
    }
}
