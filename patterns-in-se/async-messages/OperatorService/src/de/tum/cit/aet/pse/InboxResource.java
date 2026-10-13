package de.tum.cit.aet.pse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/messages/", consumes = "application/json")
public class InboxResource {

    @PostMapping("r2")
    public ResponseEntity<String> inboxR2(@RequestBody String message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("3po")
    public ResponseEntity<String> inbox3PO(@RequestBody String message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
