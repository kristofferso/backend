package no.solberg.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("health")
public class HealthController {
    @GetMapping("up")
    public ResponseEntity<String> up() {
        return ResponseEntity.ok("up");
    }
}
