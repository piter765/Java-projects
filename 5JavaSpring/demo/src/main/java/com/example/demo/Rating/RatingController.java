package com.example.demo.Rating;

import com.example.demo.classEmployee.ClassEmployee;
import com.example.demo.classEmployee.ClassEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<?> getRatings() {
        try {
            List<Rating> ratings = ratingService.getRatings();

            return ResponseEntity.status(200).body(ratings);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody Rating rating, @RequestParam("groupId") Integer classEmployeeId) {
        try {
            ratingService.createRating(rating, classEmployeeId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path="{ratingId}")
    public ResponseEntity<?> deleteRating(@PathVariable("ratingId") Integer id) {
        try {
            ratingService.deleteRating(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
