package com.company.model;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Magazine extends Resource {
    @Serial
    private static final long serialVersionUID = 123456789L;

    public Magazine(String title, List<String> authors, Optional<String> currentReader, LocalDate releaseDate, Review review) {
        super(title, authors, currentReader, releaseDate, review);
    }

    @Override
    public void printResourceType() {
        System.out.println("magazine");
    }
}
