package tech.surfer.fresh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tech.surfer.fresh.codegen.TestApi;

@RestController
public class TestController implements TestApi {
    @Override
    public ResponseEntity<String> testGet() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

}
