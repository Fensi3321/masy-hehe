package com.company.model;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class Resource implements Serializable {
    @Serial
    private static final long serialVersionUID = 123456789L;

    private static List<Resource> extent;

    private LocalDate releaseDate;
    private String title;
    private Optional<String> currentReader;
    private List<String> authors;

    public Resource(String title, List<String> authors, Optional<String> currentReader, LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        this.title = title;
        this.currentReader = currentReader;
        this.authors = authors;

        extent.add(this);
    }

    public static void saveExtent(String path) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));

        objectOutputStream.writeObject(extent);
    }

    public static void loadExtent(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));

        extent = (List<Resource>) objectInputStream.readObject();
    }

    public abstract void printResourceType();

    public static int getNumberOfResourcesThatAreCurrentlyUsed() {
        int count = 0;
        for (Resource r : extent) {
            if (r.currentReader.isPresent()) {
                count++;
            }
        }

        return count;
    }

    public long getDaysSinceResourceWasReleased() {
        return Duration.between(LocalDate.now().atStartOfDay(), this.releaseDate.atStartOfDay()).toDays();
    }

    public static List<Resource> getExtent() {
        return extent;
    }

    public static void setExtent(List<Resource> extent) {
        Resource.extent = extent;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<String> getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(Optional<String> currentReader) {
        this.currentReader = currentReader;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
