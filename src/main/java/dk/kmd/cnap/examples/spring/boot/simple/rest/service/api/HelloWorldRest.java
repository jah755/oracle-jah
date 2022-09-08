package dk.kmd.cnap.examples.spring.boot.simple.rest.service.api;

import dk.kmd.cnap.examples.spring.boot.simple.rest.service.service.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*In my HelloWorld Rest Application I'm trying to read a property from application.properties and return that value.*/
@RestController
public class
HelloWorldRest {

    Logger logger = LoggerFactory.getLogger(HelloWorldRest.class);

    /*Using @Autowired annotation to inject the value of the HelloWorldService class properties. When the bean is loaded
     the value is assigned automatically.*/
    @Autowired
    HelloWorldService helloWorldService;

    /*ResponseEntity represents the whole http response which includes status code(200 - if success),
    headers and body.
    In my current example using ResponseEntity to send status as success(HTTP Status OK) along with the string message as
    response back to client.*/
    @GetMapping("/helloNemkonto")
    public ResponseEntity executeHello() {
        logger.info("Inside executeHello...");
        String hello = helloWorldService.returnHello();
        return ResponseEntity.ok(hello);
    }
}
