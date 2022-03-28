package com.company.model;

import java.io.Serial;
import java.io.Serializable;

public class Review implements Serializable {
    @Serial
    private static final long serialVersionUID = 123456789L;

    public String reviewText;
    public String reviewer;
    public static int reviewNumber = 0;

    public Review(String reviewText, String reviewer) {
        this.reviewText = reviewText;
        this.reviewer = reviewer;

        Review.reviewNumber++;
    }

    public static int getNumberOfReviews() {
        return Review.reviewNumber;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewText='" + reviewText + '\'' +
                ", reviewer='" + reviewer + '\'' +
                '}';
    }
}
