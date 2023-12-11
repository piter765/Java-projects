package com.example.demo.Rating;

import com.example.demo.classEmployee.ClassEmployee;
import com.example.demo.classEmployee.ClassEmployeeRepository;
import com.example.demo.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    private final ClassEmployeeRepository classEmployeeRepository;


    @Autowired
    public RatingService(RatingRepository ratingRepository, ClassEmployeeRepository classEmployeeRepository) {
        this.ratingRepository = ratingRepository;
        this.classEmployeeRepository = classEmployeeRepository;
    }

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    public void createRating(Rating rating, Integer classEmployeeId) {
        ClassEmployee classEmployee = classEmployeeRepository.findById(classEmployeeId)
                .orElseThrow(() -> new IllegalStateException("group with id " + classEmployeeId + "that you want add rating to does not exist"));

        rating.setClassEmployee(classEmployee);

        ratingRepository.save(rating);
    }

    public void deleteRating(Integer ratingId) {
        boolean exists = ratingRepository.existsById(ratingId);

        if(!exists) {
            throw new IllegalStateException("rating with id " + ratingId + "does not exist");
        }

        ratingRepository.deleteById(ratingId);
    }
}
