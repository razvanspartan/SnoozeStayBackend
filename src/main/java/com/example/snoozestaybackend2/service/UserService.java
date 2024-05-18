package com.example.snoozestaybackend2.service;

import com.example.snoozestaybackend2.Repos.ReviewRepository;
import com.example.snoozestaybackend2.Repos.UserRepository;
import com.example.snoozestaybackend2.api.model.Review;
import com.example.snoozestaybackend2.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    @Autowired
    public UserService(UserRepository userRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User getUserByReviewId(int reviewId) {
        Review review = reviewRepository.findById(reviewId);
        return review != null ? review.getUser() : null;
    }
}
