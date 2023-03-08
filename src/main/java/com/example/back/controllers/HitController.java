package com.example.back.controllers;

import com.example.back.entity.Hit;
import com.example.back.services.HitServ;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class HitController {

    private final HitServ hitServ;

    public HitController(HitServ hitServ) {
        this.hitServ = hitServ;
    }

    @PostMapping("/hit")
    public ResponseEntity<?> hit(@RequestBody Hit hit) {
        if (hit.getX() == null || hit.getY() == null || hit.getR() == null) {
            return ResponseEntity.badRequest().build();
        }

        return hitServ.service(hit.getX(), hit.getY(), hit.getR());
    }

    @GetMapping("/hits")
    public ResponseEntity<List<Hit>> getAllHits() {
        return hitServ.getHits();
    }

    @DeleteMapping("/hits")
    public ResponseEntity<?> delete() {
        return hitServ.delete();
    }
}
