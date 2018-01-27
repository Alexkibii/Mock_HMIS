package lct.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

/**
 * Created by CLLSDJACKT013 on 26/01/2018.
 */
@Configuration
public class ClientConfig {
        @Value("http://localhost:9000/codenotfound/ws/lct")
        String uri;
    @Bean
    public WebServiceTemplate webServiceTemplate(){
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(jaxb2Marshaller());
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
        webServiceTemplate.setDefaultUri(uri);
        return webServiceTemplate;
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("hellios.wsdl");
        return jaxb2Marshaller;
    }
}
