package dk.kmd.cnap.examples.spring.boot.simple.rest.service.api;

import dk.kmd.cnap.examples.spring.boot.simple.rest.service.service.OracleService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@RestController
public class OracleController {

    Logger logger = LoggerFactory.getLogger(getClass());
    Path file = Path.of("/var/run/oracleMessages.txt");
    @Autowired
    OracleService oracleService;

    @GetMapping(value = "/oracle")
    public ResponseEntity<String> greet(@RequestParam String message) {
        logger.info("Executing greet().");
        String oracleMessage = null;
        try {
            if (message != null && Strings.isNotBlank(message)) {
                message = message + " ";
                file.toFile().setWritable(true);
                file.toFile().setReadable(true);
                /*Using Files built in class of java to write the message into file. The write() of Files class accepts
                 * file path, bytes and Options like how file is created. This method also ensures that the file is opened and
                 * closed when all bytes have been written to avoid an I/O error or other runtime exception.*/
                Files.write(file, message.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
                oracleMessage = oracleService.returnConcatenatedMessage(file);
            }
        } catch (Exception e) {
            logger.error("Error occured while executing greet()" +e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } finally {
            /*close file if you are using any other libraries like BufferedWriter, InputStreamReader or any other
            to perform file operations.*/
        }
        logger.info("Completed executing greet().");
        return ResponseEntity.ok().body(oracleMessage);
    }
}
