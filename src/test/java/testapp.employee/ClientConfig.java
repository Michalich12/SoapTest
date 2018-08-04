package testapp.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class ClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(ClientConfig.class);

    @Autowired
    private BeanFactory beanFactory;

    @Bean
    @Scope(value = "prototype")
    @Lazy
    public Jaxb2Marshaller marshaller(String namespace) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(namespace);
        return marshaller;
    }

    @Bean
    public EmployeeClient employeeSoapConnector() {
        EmployeeClient client = new EmployeeClient();
        client.setDefaultUri("http://localhost:8080/ws/employee.wsdl");

        Jaxb2Marshaller marshaller = beanFactory.getBean(Jaxb2Marshaller.class, "testapp.employee.webservice.model.employee");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
