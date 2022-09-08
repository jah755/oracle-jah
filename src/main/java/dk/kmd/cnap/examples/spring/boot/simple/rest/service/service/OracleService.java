package dk.kmd.cnap.examples.spring.boot.simple.rest.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OracleService {

    Logger logger = LoggerFactory.getLogger(HelloWorldService.class);
    String oracleMessage = null;

    public String returnConcatenatedMessage(Path file) throws IOException {
        logger.info("Inside returnConcatenatedMessage...");
        if(file.toFile().exists()) {
            oracleMessage = Files.readString(file);
        } else {
            logger.warn("File doesn't exist.");
        }
        logger.info(oracleMessage);
        return oracleMessage;
    }
}
