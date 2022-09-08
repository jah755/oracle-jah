package dk.kmd.cnap.examples.spring.boot.simple.rest.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** The Rest API controller of the SpringBootSimpleRestApplication */
/*
* RestController annotation alone does the job of what @Controller and @ResponseBody annotation would do.
* The data returned by each method will be written into the response body. In this controller class we
* have 3 rest end points which are performing GET operation using @getMapping annotation.
*
* Response Entity - represents the whole http response which includes status code(200 - if success), headers and body.
* */
@RestController
public class Controller {

/*Logger is used to provide log messages of different levels like (Info, Error, Debug, Warning) at class level.
Logger instance is created here by using the logger factory class and its getLogger method by providing the name of the class
for which loggers are required (usually current class name).*/
  private Logger logger = LoggerFactory.getLogger(getClass());

  /*applicationName is an instance variable which will be visible only in controller.java class as it's
  declared private. We are using this in our example because applicationName value will
  be referenced by all method in current class.*/
  private String applicationName = "spring-boot-simple-rest-service";

  /*noPath() runs at default path i.e /. Since there are no variables also accepted this rest end point will print a default message with
  * application Name*/
  @GetMapping(value = "/")
  public ResponseEntity<String> noPath() {
    String msg = "This is the default message from "+ applicationName;
    logger.info(msg);
    return ResponseEntity.ok(msg);
  }

  @GetMapping(value = "/api/health")
  public ResponseEntity<String> health() {
    String msg = "The health of "+ applicationName+" is fine";
    logger.info(msg);
    return ResponseEntity.ok(msg);
  }

  @GetMapping(value = "/api/echo")
  public ResponseEntity<String> echo(@RequestParam String message) {
    String msg =
       applicationName+ " echoes the provided message : \""
            + message
            + "\"";
    logger.info(msg);
    return ResponseEntity.ok(msg);
  }
}
