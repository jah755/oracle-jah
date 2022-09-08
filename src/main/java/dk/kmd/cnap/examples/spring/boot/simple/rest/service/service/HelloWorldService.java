package dk.kmd.cnap.examples.spring.boot.simple.rest.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*Using separate class file to maintain some logic in a different layer separated from @RestController class file.
* @Service annotation enables to have a service component, and it will autodetect this component through
* classpath scanning.*/
@Service
public class HelloWorldService {

    Logger logger = LoggerFactory.getLogger(HelloWorldService.class);
    String cnapExercise2;

    /*Marking my constructor to be autowired by Springs dependency injection facility.
    * @Value("${message.return}") - In my constructor I'm injecting the value from property file to
    *  cnapExercise2 field using @Value annotation.*/
    @Autowired
    public HelloWorldService(@Value("${message.return}") String cnapExercise2) {
        this.cnapExercise2 = cnapExercise2;
    }

    public String returnHello() {
        logger.info("Inside returnHello...");
        return cnapExercise2;
    }

}
