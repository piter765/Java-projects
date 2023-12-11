package com.example.demo.Rating;

import com.example.demo.classEmployee.ClassEmployee;
import com.example.demo.classEmployee.ClassEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Rating> getRatings() {
        return ratingService.getRatings();
    }

    @PostMapping
    public void createRating(@RequestBody Rating rating, @RequestParam("groupId") Integer classEmployeeId) {
        ratingService.createRating(rating, classEmployeeId);
    }

    @DeleteMapping(path="{ratingId}")
    public void deleteRating(@PathVariable("ratingId") Integer id) {
        ratingService.deleteRating(id);
    }

}
