package us.john.hanna.cps530assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class Cps530AssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cps530AssignmentApplication.class, args);
    }

    @Bean("notImplemented")
    public ResponseEntity<Void> notImplemented(){

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .build();

    }

}
