package com.company.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Book extends Resource {

    public Book(String title, List<String> authors, Optional<String> currentReader, LocalDate releaseDate) {
        super(title, authors, currentReader, releaseDate);
    }

    @Override
    public void printResourceType() {
        System.out.println("book");
    }
}
