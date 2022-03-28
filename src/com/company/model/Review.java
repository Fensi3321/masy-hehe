package com.company.model;

import java.io.Serial;
import java.io.Serializable;

public class Review implements Serializable {
    @Serial
    private static final long serialVersionUID = 123456789L;

    public String reviewText;
    public String reviewer;

    public Review(String reviewText, String reviewer) {
        this.reviewText = reviewText;
        this.reviewer = reviewer;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewText='" + reviewText + '\'' +
                ", reviewer='" + reviewer + '\'' +
                '}';
    }
}
